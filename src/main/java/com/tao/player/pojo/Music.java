package com.tao.player.pojo;

import java.io.Serializable;
import java.util.Date;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "music")
public class Music implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private int id;
	@JdbcColumeName(name = "music_name")
	private String musicName;
	@JdbcColumeName(name = "music_visitors")
	private int musicVisitors;
	@JdbcColumeName(name = "music_type")
	private String musicType;
	@JdbcColumeName(name = "create_time")
	private Date createTime;
	@JdbcColumeName(name = "is_valid")
	private String isValid;
	@JdbcColumeName(name = "music_path")
	private String musicPath;
	@JdbcColumeName(name = "lyric_path")
	private String lyricPath;
	@JdbcColumeName(name = "upload_username")
	private String uploadUsername;

	public Music() {
		super();
	}

	public Music(String musicName, int musicVisitors, String musicType, Date createTime, String isValid,
			String musicPath, String lyricPath, String uploadUsername) {
		super();
		this.musicName = musicName;
		this.musicVisitors = musicVisitors;
		this.musicType = musicType;
		this.createTime = createTime;
		this.isValid = isValid;
		this.musicPath = musicPath;
		this.lyricPath = lyricPath;
		this.uploadUsername = uploadUsername;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public int getMusicVisitors() {
		return musicVisitors;
	}

	public void setMusicVisitors(int musicVisitors) {
		this.musicVisitors = musicVisitors;
	}

	public String getMusicType() {
		return musicType;
	}

	public void setMusicType(String musicType) {
		this.musicType = musicType;
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

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public String getLyricPath() {
		return lyricPath;
	}

	public void setLyricPath(String lyricPath) {
		this.lyricPath = lyricPath;
	}

	public String getUploadUsername() {
		return uploadUsername;
	}

	public void setUploadUsername(String uploadUsername) {
		this.uploadUsername = uploadUsername;
	}

	@Override
	public String toString() {
		return "Music [id=" + id + ", musicName=" + musicName + ", musicVisitors=" + musicVisitors + ", musicType="
				+ musicType + ", createTime=" + createTime + ", isValid=" + isValid + ", musicPath=" + musicPath
				+ ", lyricPath=" + lyricPath + ", uploadUsername=" + uploadUsername + "]";
	}

}