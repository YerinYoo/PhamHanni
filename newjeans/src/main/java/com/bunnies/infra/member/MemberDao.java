package com.bunnies.infra.member;

import java.util.List;

public interface MemberDao {

	//전체 개수 반환 쿼리 
	public int selectCountMember(MemberVo memberVo);
	
	//리스트 반환 쿼리
	public List<MemberDto> selectListWithPaging(MemberVo memberVo);

	public List<MemberDto> selectListWithoutPaging ();
	
	//다른 페이지에서 멤버 리스트 반환하기
	public List<MemberDto> selectMemberListOnAnotherPage();
	
	//select One
	public MemberDto selectOne(MemberDto dto);

	//method
	public int insert(MemberDto dto);
	
	public int updateBasicInfo(MemberDto dto);
	
	public int updatePWD(MemberDto dto);
	
	public int updateNickName(MemberDto dto);
	
	public int uelete(MemberDto dto);
	
	public int delete(MemberDto dto);
	
}
