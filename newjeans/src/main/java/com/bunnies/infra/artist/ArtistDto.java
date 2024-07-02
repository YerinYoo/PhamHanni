package com.bunnies.infra.artist;

import java.util.Date;

public class ArtistDto {

	//artist table's columns
	private String artistSeq;
	private String artistName;
	private String artistEntertainment;
	private Integer artistCategoryCD;
	private Date artistRegDate;
	private Date artistModDate;
	private Integer artistDelNY;
	private String memberSeqF;
	
	//member table's columns
	//외국인 여부, 성별, 이메일 주소 필
	private Integer memberGenderCD;
	private Integer registerCD;
	
	//album table's columns
	private String albumName;
	private Integer albumCategoryCD;
	private String albumCoverPath;
	
	//song table's columns
	private String songTitle;
	private String youtubeID;
	
	//getter, setter
	public String getArtistSeq() {
		return artistSeq;
	}
	public void setArtistSeq(String artistSeq) {
		this.artistSeq = artistSeq;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getArtistEntertainment() {
		return artistEntertainment;
	}
	public void setArtistEntertainment(String artistEntertainment) {
		this.artistEntertainment = artistEntertainment;
	}
	public Integer getArtistCategoryCD() {
		return artistCategoryCD;
	}
	public void setArtistCategoryCD(Integer artistCategoryCD) {
		this.artistCategoryCD = artistCategoryCD;
	}
	public Date getArtistRegDate() {
		return artistRegDate;
	}
	public void setArtistRegDate(Date artistRegDate) {
		this.artistRegDate = artistRegDate;
	}
	public Date getArtistModDate() {
		return artistModDate;
	}
	public void setArtistModDate(Date artistModDate) {
		this.artistModDate = artistModDate;
	}
	public Integer getArtistDelNY() {
		return artistDelNY;
	}
	public void setArtistDelNY(Integer artistDelNY) {
		this.artistDelNY = artistDelNY;
	}
	public String getMemberSeqF() {
		return memberSeqF;
	}
	public void setMemberSeqF(String memberSeqF) {
		this.memberSeqF = memberSeqF;
	}
	public Integer getMemberGenderCD() {
		return memberGenderCD;
	}
	public void setMemberGenderCD(Integer memberGenderCD) {
		this.memberGenderCD = memberGenderCD;
	}
	public Integer getRegisterCD() {
		return registerCD;
	}
	public void setRegisterCD(Integer registerCD) {
		this.registerCD = registerCD;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public Integer getAlbumCategoryCD() {
		return albumCategoryCD;
	}
	public void setAlbumCategoryCD(Integer albumCategoryCD) {
		this.albumCategoryCD = albumCategoryCD;
	}
	public String getAlbumCoverPath() {
		return albumCoverPath;
	}
	public void setAlbumCoverPath(String albumCoverPath) {
		this.albumCoverPath = albumCoverPath;
	}
	public String getSongTitle() {
		return songTitle;
	}
	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}
	public String getYoutubeID() {
		return youtubeID;
	}
	public void setYoutubeID(String youtubeID) {
		this.youtubeID = youtubeID;
	}
	
	//toString()
	@Override
	public String toString() {
		return "ArtistDto [artistSeq=" + artistSeq + ", artistName=" + artistName + ", artistEntertainment="
				+ artistEntertainment + ", artistCategoryCD=" + artistCategoryCD + ", artistRegDate=" + artistRegDate
				+ ", artistModDate=" + artistModDate + ", artistDelNY=" + artistDelNY + ", memberSeqF=" + memberSeqF
				+ ", memberGenderCD=" + memberGenderCD + ", registerCD=" + registerCD + ", albumName=" + albumName
				+ ", albumCategoryCD=" + albumCategoryCD + ", albumCoverPath=" + albumCoverPath + ", songTitle="
				+ songTitle + ", youtubeID=" + youtubeID + "]";
	}
	
	
}
