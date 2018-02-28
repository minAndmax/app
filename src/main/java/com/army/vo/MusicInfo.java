package com.army.vo;

public class MusicInfo extends BaseInfo{
	
	private Long musicId;

	private String musicName;
	
	private String musicSinger;
	
	private String musicAuthor;
	
	private String musicSrc;

	private String musicArrangement;
	
	public Long getMusicId() {
		return musicId;
	}

	public void setMusicId(Long musicId) {
		this.musicId = musicId;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getMusicSinger() {
		return musicSinger;
	}

	public void setMusicSinger(String musicSinger) {
		this.musicSinger = musicSinger;
	}

	public String getMusicAuthor() {
		return musicAuthor;
	}

	public void setMusicAuthor(String musicAuthor) {
		this.musicAuthor = musicAuthor;
	}

	public String getMusicSrc() {
		return musicSrc;
	}

	public void setMusicSrc(String musicSrc) {
		this.musicSrc = musicSrc;
	}

	public String getMusicArrangement() {
		return musicArrangement;
	}

	public void setMusicArrangement(String musicArrangement) {
		this.musicArrangement = musicArrangement;
	}
	
	
}
