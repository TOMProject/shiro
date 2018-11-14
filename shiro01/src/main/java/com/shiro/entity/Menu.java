package com.shiro.entity;

import java.sql.Date;

/**
 * 菜单管理
 * @author 
 *
 */
public class Menu extends PageEntity{
	/**
	 * 菜单/按钮id
	 */
	private Integer menuId; 
	/**
	 * 父级id
	 */
	private Integer parentId;
	/**
	 * 菜单或者按钮名称
	 */
	private String menuName;
	/**
	 * 菜单类型， 1菜单，2按扭
	 */
	private Integer menuType;
	/**
	 * 权限
	 */
	private String permission;
	/**
	 * 创建时间
	 */
	private Date createTime;
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
