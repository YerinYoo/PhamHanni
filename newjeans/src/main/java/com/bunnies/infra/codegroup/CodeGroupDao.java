package com.bunnies.infra.codegroup;

import java.util.List;

public interface CodeGroupDao {
	
	// 개수 리턴
	public Integer selectCount(CodeGroupVo vo);
	
	// 페이지네이션 처리되지 않은 코드 그룹 리스트 리턴
	public List<CodeGroupDto> selectListWithoutPaging();

	// 페이지네이션 처리된 리스트
	public List<CodeGroupDto> selectListWithPaging(CodeGroupVo vo);
	
	// DTO 중 하나를 리턴
	public CodeGroupDto selectOne(CodeGroupDto dto);

	// 등록 버튼
	public int insert(CodeGroupDto dto);

	// 수정 버튼
	public int update(CodeGroupDto dto);

	// 삭제 버튼 (delNY 값 1로 업데이트하는 방식)
	public int uelete(CodeGroupDto dto);

	// 삭제 버튼(완전 삭제)
	public int delete(CodeGroupDto dto);

}
