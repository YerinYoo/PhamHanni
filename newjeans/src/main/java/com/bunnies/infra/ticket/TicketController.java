package com.bunnies.infra.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

	@Autowired
	TicketService service;
}
