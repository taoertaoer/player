package com.tao.player.util.page;

public class Page {

	private Integer pageSize;
	private Integer pageIndex;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", pageIndex=" + pageIndex + "]";
	}
}
