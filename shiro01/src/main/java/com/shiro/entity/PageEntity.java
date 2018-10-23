package com.shiro.entity;

public class PageEntity {
	/**
	 * 当前页
	 */
	private int pageNo =1;
	/**
	 * 每页显示数据
	 */
	private int pageSize = 10;
	/**
	 * 总条数
	 */
	private int pageCount;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
	
	
	
}
