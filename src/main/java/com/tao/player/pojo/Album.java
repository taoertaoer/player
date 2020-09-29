package com.tao.player.pojo;

import java.io.Serializable;
import java.util.Date;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "album")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private int id;
	@JdbcColumeName(name = "album_name")
	private String albumName;
	@JdbcColumeName(name = "album_visitors")
	private int albumVisitors;
	@JdbcColumeName(name = "album_description")
	private String albumDescription;
	@JdbcColumeName(name = "create_time")
	private Date createTime;
	@JdbcColumeName(name = "is_valid")
	private String isValid;
	@JdbcColumeName(name = "create_username")
	private String createUsername;

	public Album() {
		super();
	}
	
	public Album(String albumName, int albumVisitors, String albumDescription, Date createTime, String isValid,
			String createUsername) {
		super();
		this.albumName = albumName;
		this.albumVisitors = albumVisitors;
		this.albumDescription = albumDescription;
		this.createTime = createTime;
		this.isValid = isValid;
		this.createUsername = createUsername;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public int getAlbumVisitors() {
		return albumVisitors;
	}

	public void setAlbumVisitors(int albumVisitors) {
		this.albumVisitors = albumVisitors;
	}

	public String getAlbumDescription() {
		return albumDescription;
	}

	public void setAlbumDescription(String albumDescription) {
		this.albumDescription = albumDescription;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", albumName=" + albumName + ", albumVisitors=" + albumVisitors
				+ ", albumDescription=" + albumDescription + ", createTime=" + createTime + ", isValid=" + isValid
				+ ", createUsername=" + createUsername + "]";
	}
	
}