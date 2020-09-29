package com.tao.player.pojo;

import java.io.Serializable;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "music_album_relation")
public class MusicAlbumRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private int id;
	@JdbcColumeName(name = "music_id")
	private int musicId;
	@JdbcColumeName(name = "album_id")
	private int albumId;

	public MusicAlbumRelation() {
		super();
	}

	public MusicAlbumRelation(int musicId, int albumId) {
		super();
		this.musicId = musicId;
		this.albumId = albumId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	@Override
	public String toString() {
		return "MusicAlbumRelation [id=" + id + ", musicId=" + musicId + ", albumId=" + albumId + "]";
	}
	
}