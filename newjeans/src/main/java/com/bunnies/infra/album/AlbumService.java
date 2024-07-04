package com.bunnies.infra.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

	@Autowired
	AlbumDao dao;
	
	//전체 개수 카운트
	public Integer selectCount(AlbumVo vo) {
		return dao.selectCount(vo);
	}
	
	//list
	//페이지네이션 처리 X
	public List<AlbumDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}
	
	//페이지네이션 처리 O
	public List<AlbumDto> selectListWithPaging(AlbumVo albumVo) {
		return dao.selectListWithPaging(albumVo);
	}
	
	//selectOne
	public AlbumDto selectOne (AlbumDto dto) {
		return dao.selectOne(dto);
	}
	
	//method
	public Integer insert (AlbumDto dto) {
		return dao.insert(dto);
	}
	
	public Integer update (AlbumDto dto) {
		return dao.update(dto);
	}
	
	public Integer uelete (AlbumDto dto) {
		return dao.uelete(dto);
	}
	
	public Integer delete (AlbumDto dto) {
		return dao.delete(dto);
	}
}
