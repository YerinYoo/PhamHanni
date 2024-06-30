package com.bunnies.infra.code;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class CodeService {
	
	@Autowired
	CodeDao dao;
	
	//전체 개수 반환 
	public Integer selectCount(CodeVo vo) {
		return dao.selectCount(vo);
	}
	
	//리스트
	//페이지네이션 처리되지 않은 리스트 반환
	public List<CodeDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}
	
	//페이지네이션 처리된 리스트 반환
	public List<CodeDto> selectListWithPaging(CodeVo vo) {
		return dao.selectListWithPaging(vo);
	}
	
	//selectOne
	public CodeDto selectOne(CodeDto dto) {
		return dao.selectOne(dto);
	}
	
	//메서드 정의 
	public int insert(CodeDto dto) {
		return dao.insert(dto);
	}

	public int update(CodeDto dto) {
		return dao.update(dto);
	}

	public int uelete(CodeDto dto) {
		return dao.uelete(dto);
	}

	public int delete(CodeDto dto) {
		return dao.delete(dto);
	}
	
	/* 공통 코드를 캐시화하여 사용할 수 있는 쿼리 */
	@PostConstruct
	public void selectListCachedCodeArrayList() throws Exception {
		List<CodeDto> codeListFromDb = (ArrayList<CodeDto>) dao.selectListCachedCodeArrayList();
//			codeListFromDb = (ArrayList<Code>) dao.selectListCachedCodeArrayList();
		CodeDto.cachedCodeArrayList.clear();
		CodeDto.cachedCodeArrayList.addAll(codeListFromDb);
		System.out.println("cachedCodeArrayList: " + CodeDto.cachedCodeArrayList.size() + " chached !");
	}

	// 코드 한 가지만 받아올 때 사용하는 param 코드 번호
	public static String selectOneCachedCode(int code) throws Exception {
		String rt = "";
		for (CodeDto codeRow : CodeDto.cachedCodeArrayList) {
			if (codeRow.getCodeSeq().equals(Integer.toString(code))) {
				rt = codeRow.getCodeName();
			} else {
				// by pass
			}
		}
		return rt;
	}

	// 코드를 list로 받아와야 할 때 사용하는 param 코드그룹 번호
	public static List<CodeDto> selectListCachedCode(String codegroupSeq) throws Exception {
		List<CodeDto> rt = new ArrayList<CodeDto>();

		for (CodeDto codeRow : CodeDto.cachedCodeArrayList) {

			if (codeRow.getCodegroupSeqF().equals(codegroupSeq)) {
				rt.add(codeRow);
			} else {
				// by pass
			}
		}
		return rt;
	}


}
