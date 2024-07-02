package com.bunnies.infra.artist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {

	@Autowired
	ArtistDao dao;
	
	//list
	//총 개수 반환
	public Integer selectCount(ArtistVo vo) {
		return dao.selectCount(vo);
	}
	
	//페이지네이션 처리되지 않은 리스트 반환
	public List<ArtistDto> selectListWithoutPaging() {
		return dao.selectListWithoutPaging();
	}
	
	//페이지네이션 처리된 리스트 반환
	public List<ArtistDto> selectListWithPaging(ArtistVo vo) {
		return dao.selectListWithPaging(vo);
	}
	
	//selectOne
	//해당 아티스트 정보와 일치하는 앨범과 함께 반환하는 쿼리 
	public ArtistDto selectOneWithAlbum(ArtistDto dto) {
		return dao.selectOneWithAlbum(dto);
	}
	
	//해당 아티스트 정보와 일치하는 곡과 함께 반환하는 쿼리 
	public ArtistDto selectOneWithSong(ArtistDto dto) {
		return dao.selectOneWithSong(dto);
	}
}
