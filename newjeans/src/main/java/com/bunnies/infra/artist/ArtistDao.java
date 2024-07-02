package com.bunnies.infra.artist;

import java.util.List;

public interface ArtistDao {

	//전체 개수 반환 쿼리
	public int selectCount(ArtistVo artistVo);
	
	//리스트 반환 쿼리
	//페이지네이션 처리되지 않은 리스트
	public List<ArtistDto> selectListWithoutPaging();
	
	//페이지네이션 처리된 리스트 
	public List<ArtistDto> selectListWithPaging(ArtistVo artistVo);
	
	//selectOne
	//앨범 정보 함께 조인한 셀렉원
	public ArtistDto selectOneWithAlbum(ArtistDto dto);
	
	//곡 정보 함께 조인한 셀렉원 
	public ArtistDto selectOneWithSong(ArtistDto dto);
	
	//method
	public int insert(ArtistDto dto);
	
	public int update(ArtistDto dto);
	
	public int uelete(ArtistDto dto);
	
	public int delete(ArtistDto dto);
}
