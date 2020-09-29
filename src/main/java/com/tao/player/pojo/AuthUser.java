package com.tao.player.pojo;

import java.io.Serializable;
import java.util.Date;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;


@JdbcTableName(name = "auth_user")
public class AuthUser implements Serializable{

	private static final long serialVersionUID = 1L;
	@JdbcColumeName
	private Integer id;
	@JdbcColumeName
	private String username;
	@JdbcColumeName
	private String nickname;
	@JdbcColumeName
	private String password;
	@JdbcColumeName
	private Integer age;
	@JdbcColumeName(name="location")
	private String location;
	@JdbcColumeName(name="profile_file_path")
	private String profileFilePath;
	@JdbcColumeName(name = "is_valid")
	private String isValid;
	@JdbcColumeName(name = "create_time")
	private Date createTime;
	
	public AuthUser() {
		super();
	}
	
	public AuthUser(String username, String nickname, String password, Integer age, String location, String profileFilePath, String isValid, Date createTime) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.age = age;
		this.location = location;
		this.profileFilePath = profileFilePath;
		this.isValid = isValid;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProfileFilePath() {
		return profileFilePath;
	}

	public void setProfileFilePath(String profileFilePath) {
		this.profileFilePath = profileFilePath;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "AuthUser [id=" + id + ", username=" + username + ", nickname=" + nickname + ", password=" + password
				+ ", age=" + age + ", location=" + location + ", profileFilePath=" + profileFilePath + ", isValid="
				+ isValid + ", createTime=" + createTime + "]";
	}

}
