package com.bunnies.infra.mixed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MixController {

	@RequestMapping (value = "/Ditto")
	public String Ditto () throws Exception {
		return "ditto/home";
	}
}
