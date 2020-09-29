package com.tao.player.pojo;

import java.io.Serializable;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "favorites")
public class Favorites implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private int id;
	@JdbcColumeName(name = "user_id")
	private int userId;
	@JdbcColumeName(name = "music_id")
	private int musicId;

	public Favorites() {
		super();
	}

	public Favorites(int userId, int musicId) {
		super();
		this.userId = userId;
		this.musicId = musicId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	@Override
	public String toString() {
		return "Favorites [id=" + id + ", userId=" + userId + ", musicId=" + musicId + "]";
	}
	
}