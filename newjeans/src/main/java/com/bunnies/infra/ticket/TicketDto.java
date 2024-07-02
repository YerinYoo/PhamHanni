package com.bunnies.infra.ticket;

import java.util.Date;

public class TicketDto {
	
	private String ticketSeq;
	private String ticketName;
	private Integer ticketPrice;
	private Integer ticketDiscountedPrice;
	private String ticketDescription;
	private Date ticketRegDate;
	private Date ticketModDate;
	private Integer ticketDelNY;
	
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
	public Integer getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(Integer ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public Integer getTicketDiscountedPrice() {
		return ticketDiscountedPrice;
	}
	public void setTicketDiscountedPrice(Integer ticketDiscountedPrice) {
		this.ticketDiscountedPrice = ticketDiscountedPrice;
	}
	public String getTicketDescription() {
		return ticketDescription;
	}
	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}
	public Date getTicketRegDate() {
		return ticketRegDate;
	}
	public void setTicketRegDate(Date ticketRegDate) {
		this.ticketRegDate = ticketRegDate;
	}
	public Date getTicketModDate() {
		return ticketModDate;
	}
	public void setTicketModDate(Date ticketModDate) {
		this.ticketModDate = ticketModDate;
	}
	public Integer getTicketDelNY() {
		return ticketDelNY;
	}
	public void setTicketDelNY(Integer ticketDelNY) {
		this.ticketDelNY = ticketDelNY;
	}
	
	//toString()
	@Override
	public String toString() {
		return "TicketDto [ticketSeq=" + ticketSeq + ", ticketName=" + ticketName + ", ticketPrice=" + ticketPrice
				+ ", ticketDiscountedPrice=" + ticketDiscountedPrice + ", ticketDescription=" + ticketDescription
				+ ", ticketRegDate=" + ticketRegDate + ", ticketModDate=" + ticketModDate + ", ticketDelNY="
				+ ticketDelNY + "]";
	}

	
}
