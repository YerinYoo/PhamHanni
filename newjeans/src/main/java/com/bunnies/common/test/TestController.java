package com.bunnies.common.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bunnies.infra.code.CodeDto;
import com.bunnies.infra.code.CodeService;
import com.bunnies.infra.codegroup.CodeGroupDto;
import com.bunnies.infra.codegroup.CodeGroupService;
import com.bunnies.infra.member.MemberDto;
import com.bunnies.infra.member.MemberService;

@Controller
public class TestController {

	@Autowired
	CodeGroupService cgService;
	
	@Autowired
	CodeService cService;
	
	@Autowired
	MemberService mService;
	
	@RequestMapping(value = "/Test")
	public String test(CodeGroupDto dtoCg, CodeDto dtoC, MemberDto dtoM, Model model) throws Exception {
		//예외처리를 명시하고자 throws Exception 사용
		//IOException, SQLException 등의 명시적인 예외처리가 있지만, 전반적인 부분에 대해 예외처리를 진행하고자 Exception 사용


			model.addAttribute("codegroup", cgService.selectListWithoutPaging() );
			model.addAttribute("code", cService.selectListWithoutPaging());
			model.addAttribute("member", mService.selectListWithoutPaging());

		return "ditto/test";

	}
	
}
