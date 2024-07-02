package com.bunnies.infra.ticket;

import java.util.List;

public interface TicketDao {

	//티켓 개수 반환
	public Integer selectCount (TicketVo vo);
	
	//페이지네이션 처리된 리스트 반환
	public List<TicketDto> selectListWithPaging(TicketVo vo);
	
	//페이지네이션 처리되지 않은 리스트 반환
	public List<TicketDto> selectListWithoutPaging();
	
	//selectOne
	public TicketDto selectOne(TicketDto dto);
}
