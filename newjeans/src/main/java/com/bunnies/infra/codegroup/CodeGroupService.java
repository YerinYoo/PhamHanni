package com.bunnies.infra.codegroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeGroupService {

	@Autowired
	CodeGroupDao dao;
	
	//전체 개수 리턴
	public Integer selectCount(CodeGroupVo vo) {
		return dao.selectCount(vo);
	}
	
	//리스트 반환 관련
	//페이징없는 코드그룹 리스트
	public List<CodeGroupDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}

	// 페이징 처리 리스트
	public List<CodeGroupDto> selectListWithPaging(CodeGroupVo vo) {
		return dao.selectListWithPaging(vo);
	}
	
	public CodeGroupDto selectOne(CodeGroupDto dto) {
		return dao.selectOne(dto);
	}
	
	//메서드 관련
	public int insert(CodeGroupDto dto) {
		return dao.insert(dto);
	}

	public int update(CodeGroupDto dto) {
		return dao.update(dto);
	}

	public int uelete(CodeGroupDto dto) {
		return dao.uelete(dto);
	}

	public int delete(CodeGroupDto dto) {
		return dao.delete(dto);
	}

}