package com.bunnies.infra.album;

import java.util.Date;

public class AlbumDto {

	private String albumSeq;
	private String albumName;
	private Date albumReleaseDate;
	private Integer albumCategoryCD;
	private String albumCoverPath;
	private Integer artistSeqF;
	private Date albumRegDate;
	private Date albumModDate;
	private Integer albumDelNY;
	
	//getter, setter
	public String getAlbumSeq() {
		return albumSeq;
	}
	public void setAlbumSeq(String albumSeq) {
		this.albumSeq = albumSeq;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public Date getAlbumReleaseDate() {
		return albumReleaseDate;
	}
	public void setAlbumReleaseDate(Date albumReleaseDate) {
		this.albumReleaseDate = albumReleaseDate;
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
	public Integer getArtistSeqF() {
		return artistSeqF;
	}
	public void setArtistSeqF(Integer artistSeqF) {
		this.artistSeqF = artistSeqF;
	}
	public Date getAlbumRegDate() {
		return albumRegDate;
	}
	public void setAlbumRegDate(Date albumRegDate) {
		this.albumRegDate = albumRegDate;
	}
	public Date getAlbumModDate() {
		return albumModDate;
	}
	public void setAlbumModDate(Date albumModDate) {
		this.albumModDate = albumModDate;
	}
	public Integer getAlbumDelNY() {
		return albumDelNY;
	}
	public void setAlbumDelNY(Integer albumDelNY) {
		this.albumDelNY = albumDelNY;
	}
	
	//toString()
	@Override
	public String toString() {
		return "AlbumDto [albumSeq=" + albumSeq + ", albumName=" + albumName + ", albumReleaseDate=" + albumReleaseDate
				+ ", albumCategoryCD=" + albumCategoryCD + ", albumCoverPath=" + albumCoverPath + ", artistSeqF="
				+ artistSeqF + ", albumRegDate=" + albumRegDate + ", albumModDate=" + albumModDate + ", albumDelNY="
				+ albumDelNY + "]";
	}
	
	
}
