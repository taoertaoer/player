package com.tao.player.pojo;

import java.io.Serializable;

import com.tao.player.util.jdbc.JdbcColumeName;
import com.tao.player.util.jdbc.JdbcTableName;

@JdbcTableName(name = "menu")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	@JdbcColumeName(name = "id")
	private Integer id;
	@JdbcColumeName(name = "menu_name")
	private String menuName;
	@JdbcColumeName(name = "menu_url")
	private String menuUrl;
	@JdbcColumeName(name = "father_id")
	private Integer fatherId;
	@JdbcColumeName(name = "is_valid")
	private String isValid;

	public Menu() {
		super();
	}

	public Menu(String menuName, String menuUrl, Integer fatherId, String isValid) {
		super();
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.fatherId = fatherId;
		this.isValid = isValid;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuName=" + menuName + ", menuUrl=" + menuUrl + ", fatherId=" + fatherId
				+ ", isValid=" + isValid + "]";
	}
	
}