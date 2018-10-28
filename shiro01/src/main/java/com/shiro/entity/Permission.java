package com.shiro.entity;

/**
 * 权限类
 * @author 
 *
 */
public class Permission extends PageEntity{

	private Integer id;
	
	private String permissionName;
	//权限标识
	private String perms;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	
	
	
}
