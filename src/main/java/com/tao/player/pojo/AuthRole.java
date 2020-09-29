package com.tao.player.pojo;

import java.io.Serializable;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "auth_role")
public class AuthRole implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private int id;
	@JdbcColumeName(name = "role_name")
	private String roleName;
	@JdbcColumeName(name = "is_valid")
	private String isValid;
	@JdbcColumeName(name = "role_code")
	private String roleCode;

	public AuthRole() {
		super();
	}

	public AuthRole(String roleName, String isValid, String roleCode) {
		super();
		this.roleName = roleName;
		this.isValid = isValid;
		this.roleCode = roleCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Override
	public String toString() {
		return "AuthRole [id=" + id + ", roleName=" + roleName + ", isValid=" + isValid + ", roleCode=" + roleCode
				+ "]";
	}

}