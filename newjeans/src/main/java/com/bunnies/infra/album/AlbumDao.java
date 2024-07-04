package com.bunnies.infra.album;

import java.util.List;

public interface AlbumDao {

	//전체 개수 반환 쿼리
	public int selectCount (AlbumVo Vo);
	
	//리스트 반환 쿼리
	//페이지네이션 적용 X
	public List<AlbumDto> selectListWithoutPaging();
	
	//페이지네이션 적용 O
	public List<AlbumDto> selectListWithPaging(AlbumVo vo);
	
	//selectOne
	public AlbumDto selectOne(AlbumDto dto);
	
	//method
	public int insert(AlbumDto dto);
	
	public int update(AlbumDto dto);
	
	public int uelete(AlbumDto dto);
	
	public int delete(AlbumDto dto);
}
