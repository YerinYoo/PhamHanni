package com.bunnies.infra.ticket;

import com.bunnies.common.base.BaseVo;

public class TicketVo extends BaseVo {

	private String ticketSeq;
	private String ticketName;
	
	//getter, setter
	public String getTicketSeq() {
		return ticketSeq;
	}
	public void setTicketSeq(String ticketSeq) {
		this.ticketSeq = ticketSeq;
	}
	public String getTicketName() {
		return ticketName;
	}
	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	
	
}
