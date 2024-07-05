package com.bunnies.infra.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketController {

	@Autowired
	TicketService service;
	
	//이용권 리스트 표출 페이지
	@RequestMapping (value = "/Ticket-Plans")
	public String TicketList (TicketDto dto, Model model) throws Exception {
		
		model.addAttribute("tickets", service.selectListWithoutPaging());
		
		return "ditto/plan";
	}
	
	//이용권 결제 관련 로직 
}
