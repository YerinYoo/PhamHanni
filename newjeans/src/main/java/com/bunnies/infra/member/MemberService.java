package com.bunnies.infra.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
	
	@Autowired
	MemberDao dao;
	
	//list
	//페이지네이션 처리 된 리스트 반환 
	public List <MemberDto> selectListWithPaging(MemberVo vo) {
		return dao.selectListWithPaging(vo);
	}
	
	//페이지네이션 처리되지 않은 리스트 반환
	public List <MemberDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}
	
	//다른 페이지에서 멤버 리스트 호출 쿼리
	public List <MemberDto> selectMemberListOnAnotherPage() {
		return dao.selectMemberListOnAnotherPage();
	}
	
	//전체 개수 반환 쿼리
	public Integer selectCountMember(MemberVo memberVo) throws Exception {
		return dao.selectCountMember(memberVo);
	}

	//method
	//insert
	public Integer insert (MemberDto dto) {
		return dao.insert(dto);
	}
	
	//update
	public Integer updateBasicInfo (MemberDto dto) {
		return dao.updateBasicInfo(dto);
	}
	
	public Integer updatePWD (MemberDto dto) {
		return dao.updatePWD(dto);
	}
	
	public Integer updateNickName (MemberDto dto) {
		return dao.updateNickName(dto);
	}
	
	public Integer uelete (MemberDto dto) {
		return dao.uelete(dto);
	}
	
	//delete
	public Integer delete (MemberDto dto) {
		return dao.delete(dto);
	}
}
