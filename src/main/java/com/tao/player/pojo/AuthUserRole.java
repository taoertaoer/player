package com.tao.player.pojo;

import java.io.Serializable;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "auth_user_role")
public class AuthUserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private int id;
	@JdbcColumeName(name = "role_id")
	private int roleId;
	@JdbcColumeName(name = "user_id")
	private int userId;

	public AuthUserRole() {
		super();
	}

	public AuthUserRole(int roleId, int userId) {
		super();
		this.roleId = roleId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AuthUserRole [id=" + id + ", roleId=" + roleId + ", userId=" + userId + "]";
	}
}