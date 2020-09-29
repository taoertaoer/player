package com.tao.player.pojo;

import java.io.Serializable;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "role_menu_relation")
public class RoleMenuRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private Integer id;
	@JdbcColumeName(name = "menu_id")
	private Integer menuId;
	@JdbcColumeName(name = "role_id")
	private Integer roleId;

	public RoleMenuRelation() {
		super();
	}

	public RoleMenuRelation(Integer menuId, Integer roleId) {
		super();
		this.menuId = menuId;
		this.roleId = roleId;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "RoleMenuRelation [id=" + id + ", menuId=" + menuId + ", roleId=" + roleId + "]";
	}

}