package com.tao.player.pojo;

import java.io.Serializable;
import java.util.Date;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "singer")
public class Singer implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private int id;
	@JdbcColumeName(name = "singer_name")
	private String singerName;
	@JdbcColumeName(name = "singer_sex")
	private String singerSex;
	@JdbcColumeName(name = "singer_birthday")
	private String singerBirthday;
	@JdbcColumeName(name = "singer_visitors")
	private int singerVisitors;
	@JdbcColumeName(name = "hobby")
	private String hobby;
	@JdbcColumeName(name = "is_valid")
	private String isValid;
	@JdbcColumeName(name = "create_time")
	private Date createTime;
	@JdbcColumeName(name = "create_username")
	private String createUsername;

	public Singer() {
		super();
	}
	
	public Singer(String singerName, String singerSex, String singerBirthday, int singerVisitors, String hobby,
			String isValid, Date createTime, String createUsername) {
		super();
		this.singerName = singerName;
		this.singerSex = singerSex;
		this.singerBirthday = singerBirthday;
		this.singerVisitors = singerVisitors;
		this.hobby = hobby;
		this.isValid = isValid;
		this.createTime = createTime;
		this.createUsername = createUsername;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getSingerSex() {
		return singerSex;
	}

	public void setSingerSex(String singerSex) {
		this.singerSex = singerSex;
	}

	public String getSingerBirthday() {
		return singerBirthday;
	}

	public void setSingerBirthday(String singerBirthday) {
		this.singerBirthday = singerBirthday;
	}

	public int getSingerVisitors() {
		return singerVisitors;
	}

	public void setSingerVisitors(int singerVisitors) {
		this.singerVisitors = singerVisitors;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	@Override
	public String toString() {
		return "Singer [id=" + id + ", singerName=" + singerName + ", singerSex=" + singerSex + ", singerBirthday="
				+ singerBirthday + ", singerVisitors=" + singerVisitors + ", hobby=" + hobby + ", isValid=" + isValid
				+ ", createTime=" + createTime + ", createUsername=" + createUsername + "]";
	}
	
}