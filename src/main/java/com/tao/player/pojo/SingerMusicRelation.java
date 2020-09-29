package com.tao.player.pojo;

import java.io.Serializable;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "singer_music_relation")
public class SingerMusicRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private int id;
	@JdbcColumeName(name = "singer_id")
	private int singerId;
	@JdbcColumeName(name = "music_id")
	private int musicId;
	
	public SingerMusicRelation() {
		super();
	}

	public SingerMusicRelation(int singerId, int musicId) {
		super();
		this.singerId = singerId;
		this.musicId = musicId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSingerId() {
		return singerId;
	}

	public void setSingerId(int singerId) {
		this.singerId = singerId;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	@Override
	public String toString() {
		return "SingerMusicRelation [id=" + id + ", singerId=" + singerId + ", musicId=" + musicId + "]";
	}
	
}