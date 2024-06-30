package com.bunnies.infra.code;

import java.util.List;

public interface CodeDao {

	//Code 테이블에 속하는 데이터 개수 반환
	public Integer selectCount(CodeVo vo);
	
	//리스트 반환
	//페이지네이션 처리 되지 않은 리스트 반환
	public List<CodeDto> selectListWithoutPaging();
	
	//페이지네이션 처리한 리스트 반환
	public List<CodeDto> selectListWithPaging(CodeVo vo);
	
	//selectOne
	public CodeDto selectOne(CodeDto dto);
	
	//메서드 정의 
	public int insert(CodeDto dto);

	public int update(CodeDto dto);
	
	public int uelete(CodeDto dto);
	
	public int delete(CodeDto dto);
	
	//공통 코드 캐시화하여 사용
	public List<CodeDto> selectListCachedCodeArrayList();
}
