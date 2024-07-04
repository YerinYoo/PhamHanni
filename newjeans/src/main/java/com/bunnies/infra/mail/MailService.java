package com.bunnies.infra.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.bunnies.infra.member.MemberDto;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
    public void sendMailSimple(MemberDto dto) {
    	SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    	
    	simpleMailMessage.setTo(dto.getMemberEmail());
    	//메일 제목
    	simpleMailMessage.setSubject("Welcome to Ditto 🐇");
    	//메일 내용
    	String firstName = dto.getMemberFirstName(); //회원의 이름을 가져와서 객체로 지정 
    	simpleMailMessage.setText("Dear " + firstName + ",\n\n" + "I hope you have a great time at Ditto 🐇 \n" + "Enjoy exploring and discovering new music!\n\n" + "Best regards,\n" + "From. The Ditto Team & Yerin Yoo");    

    	javaMailSender.send(simpleMailMessage);

    }
    
}
