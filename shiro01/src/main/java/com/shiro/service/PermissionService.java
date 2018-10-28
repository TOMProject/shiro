package com.shiro.service;

import java.util.List;

import com.shiro.entity.Permission;

public interface PermissionService {
	/**
	 * 通过用户名称查询权限
	 * @param userName
	 * @return
	 */
	List<Permission> selectPermissionByUserName(String userName);
	
}
