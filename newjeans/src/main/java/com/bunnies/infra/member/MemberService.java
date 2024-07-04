package com.bunnies.infra.member;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bunnies.infra.mail.MailService;

@Service
public class MemberService {
	
	@Autowired
	MemberDao dao;
	
	@Autowired
	MailService serviceM;
	
	//list
	//페이지네이션 처리 된 리스트 반환 
	public List <MemberDto> selectListWithPaging(MemberVo vo) {
		return dao.selectListWithPaging(vo);
	}
	
	//페이지네이션 처리되지 않은 리스트 반환
	public List <MemberDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}
	
	//다른 페이지에서 멤버 리스트 호출 쿼리
	public List <MemberDto> selectMemberListOnAnotherPage() {
		return dao.selectMemberListOnAnotherPage();
	}
	
	//전체 개수 반환 쿼리
	public Integer selectCount(MemberVo memberVo) {
		return dao.selectCountMember(memberVo);
	}

	//method
	//insert
	public Integer insert (MemberDto dto) {
		return dao.insert(dto);
	}
	
	//비밀번호 암호화 처리 - 회원 가입 및 관리자 단 insert 공통 사용 
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	// 로그인 처리 (사용자의 입력 값 & 암호화된 비밀번호 비교)
	public MemberDto authenticate(String ID, String pwd) {
		MemberDto member = dao.selectOneByID(ID);

		if (member != null && passwordEncoder.matches(pwd, member.getMemberPWD())) {
			return member;
		} else {
			return null;
		}

	}
	
	//비밀번호 찾기 관련 메서드 정의 
	public String generateTemporaryPassword() {
        int length = 10; //새로 발급하는 비밀번호의 문자열 길이를 10자로 제한 
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&"; //사용할 문자 집합 정의 (영문 대소문자, 숫자, 일부 특수문자) > 조금 더 깔끔하게 적을 수 있는 방법이 있을지 고민 필요...
        Random rnd = new Random(); //랜덤 객체 생성 
        StringBuilder sb = new StringBuilder(length); //StringBuilder를 사용하여 임시 비밀번호를 생성할 문자열 만듦 
        for (int i = 0; i < length; i++) { //for문을 통해 임시 비밀번호 길이만큼 반복하여 랜덤 문자를 추가
            sb.append(chars.charAt(rnd.nextInt(chars.length()))); //
        }
        return sb.toString(); //생성된 임시 비밀번호를 문자열로 변환 
    }
 
	//비밀번호 재설정 메서드 (update)
	public boolean resetPassword(String memberID, String memberEmail) {
        MemberDto member = dao.selectOneByIDAndEmail(memberID, memberEmail); //ID와 이메일 값을 이용해 일치하는 회원 정보 조회 
        if (member != null) { //if문을 이용해 회원의 존재 여부 판단 
            String temporaryPassword = generateTemporaryPassword(); //임시 비밀번호 생성 
            String encryptedPassword = passwordEncoder.encode(temporaryPassword); //생성된 임시 비밀번호 암호화 

            dao.updateMemberPassword(member.getMemberSeq(), encryptedPassword); //비밀번호 업데이트 쿼리 호출하여 비밀번호 업데이트 
            serviceM.sendMail(memberEmail, "임시 비밀번호", "새 비밀번호는: " + temporaryPassword); //임시 비밀번호 이메일로 발송 

            return true; //비밀번호 재설정 성공 시 반환 
        } else {
            return false; //비밀번호 재설정 실패 시 반환 
        }
    }
 
	//update
	public Integer updateBasicInfo (MemberDto dto) {
		return dao.updateBasicInfo(dto);
	}
	
	public Integer updatePWD (MemberDto dto) {
		return dao.updatePWD(dto);
	}
	
	public Integer updateNickName (MemberDto dto) {
		return dao.updateNickName(dto);
	}
	
	public Integer uelete (MemberDto dto) {
		return dao.uelete(dto);
	}
	
	//delete
	public Integer delete (MemberDto dto) {
		return dao.delete(dto);
	}
}
