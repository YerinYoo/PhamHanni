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

	//로그인 처리
	public MemberDto selectOneByID(String memberID);
	
	//method
	public int insert(MemberDto dto);
	
	public int updateBasicInfo(MemberDto dto);
	
	public int updatePWD(MemberDto dto);
	
	public int updateNickName(MemberDto dto);
	
	public int uelete(MemberDto dto);
	
	public int delete(MemberDto dto);
	
	//비밀번호 찾기 관련
    // 아이디와 이메일로 회원 정보 조회
    public MemberDto selectOneByIDAndEmail(String memberID, String memberEmail);
    
    // 비밀번호 업데이트
    public int updateMemberPassword(String memberSeq, String encryptedPassword);
	
}
