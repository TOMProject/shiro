package com.shiro.entity;

import java.io.Serializable;
import java.util.List;

public class ShowPage<T> implements Serializable{

	private Integer pageNo;
	private Integer pageSize;
	private Integer pageCount;
	private List<T> list;
	
	public ShowPage(PageEntity pageEntity,List<T> list) {
		this.pageNo=pageEntity.getPageNo();
		this.pageSize=pageEntity.getPageSize();
		this.pageCount=pageEntity.getPageCount();
		this.list=list;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	
	
	
	
}
