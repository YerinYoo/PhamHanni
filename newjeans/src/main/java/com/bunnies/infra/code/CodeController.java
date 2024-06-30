package com.bunnies.infra.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CodeController {
	
	@Autowired
	private CodeService service;
	

}
