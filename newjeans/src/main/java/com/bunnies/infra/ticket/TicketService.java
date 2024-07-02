package com.bunnies.infra.ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

	@Autowired
	TicketDao dao;
	
	//전체 개수 리턴
	public Integer selectCount(TicketVo vo) {
		return dao.selectCount(vo);
	}
	
	//페이지네이션 처리한 리스트 반환
	public List<TicketDto> selectListWithPaging(TicketVo vo) {
		return dao.selectListWithPaging(vo);
	}
	
	//페이지네이션 처리되지 않은 리스트 반환
	public List<TicketDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}
	
	//selectOne
	public TicketDto selectOne(TicketDto dto) {
		return dao.selectOne(dto);
	}
}
