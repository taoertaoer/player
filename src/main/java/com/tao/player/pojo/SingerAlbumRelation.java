package com.tao.player.pojo;

import java.io.Serializable;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "singer_album_relation")
public class SingerAlbumRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private int id;
	@JdbcColumeName(name = "singer_id")
	private int singerId;
	@JdbcColumeName(name = "album_id")
	private int albumId;

	public SingerAlbumRelation() {
		super();
	}

	public SingerAlbumRelation(int singerId, int albumId) {
		super();
		this.singerId = singerId;
		this.albumId = albumId;
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

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	@Override
	public String toString() {
		return "SingerAlbumRelation [id=" + id + ", singerId=" + singerId + ", albumId=" + albumId + "]";
	}
	
}