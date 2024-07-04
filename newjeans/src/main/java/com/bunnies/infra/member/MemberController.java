package com.bunnies.infra.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bunnies.common.base.BaseController;
import com.bunnies.infra.mail.MailService;
import com.bunnies.common.constants.Constants;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController extends BaseController {

	@Autowired
	MemberService service;
	@Autowired
	MailService serviceM;
	
	//회원가입 관련 s
	//회원가입 페이지
	@RequestMapping (value = "/JoinIn")
	public String JoinIn (MemberDto dto) throws Exception {
		return "ditto/register";
	}
	
	//회원가입 로직
	@PostMapping (value = "/insert")
	//HTTP POST 요청을 처리하는 메소드를 지정하고자 PostMapping 사용 > "/insert" 엔드포인트에 POST 요청이 들어오면 이 메소드가 실행됨 

	public String insert (MemberDto dto) throws Exception {
		String originalPwd = dto.getMemberPWD(); //사용자가 입력한 비밀번호 저장 (암호화 처리 X)
		
		//사용자가 입력한 비밀번호를 암호화하여 DTO 객체에 설정
		dto.setMemberPWD(encodeBcrypt(dto.getMemberPWD(), 9));
		/*
		 * MemberDto 객체의 비밀번호를 BCrypt 알고리즘을 사용하여 암호화
		 * +) encodeBcrypt 메소드는 비밀번호를 암호화하는 메소드
		 * 인자로는 사용자가 입력한 비밀번호와 솔트(rounds) 값인 9를 전달
		 */
		
		//입력 값이 없으면 delNY 값 0으로 설정
		if (dto.getMemberDelNY() ==  null) {
			dto.setMemberDelNY(0);
		}
		
		//DB 상에 데이터 삽입하기 전에 암호화된 비밀번호를 확인하고 출력 > 디버깅 목적 
		System.out.println("Encrypted PWD : " + dto.getMemberPWD());
		
		//사용자가 입력한 비밀번호와 암호화된 비밀번호를 비교하여 출력
		if (matchesBcrypt(originalPwd, dto.getMemberPWD(), 10)) { //matchesBcrypt 메소드는 암호화된 비밀번호와 비교할 비밀번호를 받아서 검증하는 메소드, 솔트(rounds) 값으로 10을 전달
			System.out.println("PWD matches!");
		} else {
			System.out.println("PWD doesn't match!");
		}
		
		//나머지 insert 로직은 그대로 유지
		System.out.println(dto.toString());
		
		//insert와 관련된 service 로직 호출
		service.insert(dto);
		
		//insert 로직이 호출되고 작동되면, googleSMTP를 이용하여 해당 회원에게 메일 발송
		serviceM.sendMailSimple(dto);
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				serviceM.sendMailSimple(dto);
			}
		});
		
		thread.start();
		
		return "redirect:/Login";
	}
    
	//회원가입 관련 e
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//로그인 관련 s
	@RequestMapping (value = "/Login")
	public String login (MemberDto dto) throws Exception {
		return "ditto/login";
	}
	
	//로그인 로직
	 // 로그인 처리
    @ResponseBody
    @RequestMapping(value = "/loginMethod")
    public Map<String, Object> loginMethod(MemberDto dto, HttpSession httpSession) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();

        // 입력된 아이디와 비밀번호를 서비스 레이어로 전달하여 인증 수행
        MemberDto authenticatedMember = service.authenticate(dto.getMemberID(), dto.getMemberPWD());

        // 인증된 회원이 존재하면 세션에 회원 정보 저장하고 성공 메시지 반환
        if (authenticatedMember != null) {
            // 로그인 성공 시 세션에 회원 정보 저장
            httpSession.setAttribute("authenticatedMember", authenticatedMember);
            httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60초 * 30분 = 30분
            httpSession.setAttribute("sessMemberSeq", authenticatedMember.getMemberSeq());
            httpSession.setAttribute("sessMemberID", authenticatedMember.getMemberID());
            httpSession.setAttribute("sessMemberName", authenticatedMember.getMemberFirstName());
            
			System.out.println("---------------------");
			System.out.println("httpSession.getAttribute(\"sessMemberName\"): " + httpSession.getAttribute("sessMemberName"));
			System.out.println("---------------------");

            returnMap.put("rt", "success");
        } else {
            // 인증 실패 시 처리
            returnMap.put("rt", "fail");
        }

        return returnMap;
    }
	
	//로그인 관련 e
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//비밀번호 찾기 관련 s
	//비밀번호 찾기 페이지
	@RequestMapping (value = "/forgotPWD")
	public String forgotPWD (MemberDto dto) throws Exception {
		return "ditto/forgot-pwd";
	}
	
	//비밀번호 재발급 메서드 
    // 비밀번호 재설정
	@ResponseBody
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public Map<String, Object> resetPassword(@RequestParam("memberID") String memberID, @RequestParam("memberEmail") String memberEmail) {
	    Map<String, Object> returnMap = new HashMap<>();

	    boolean success = service.resetPassword(memberID, memberEmail);

	    if (success) {
	        returnMap.put("rt", "success");
	    } else {
	        returnMap.put("rt", "fail");
	    }

	    return returnMap;
	}

    
	//비밀번호 찾기 관련 e
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
