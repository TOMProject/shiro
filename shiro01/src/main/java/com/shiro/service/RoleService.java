package com.shiro.service;

import java.util.List;
import java.util.Map;

import com.shiro.entity.Role;

public interface RoleService {
	/**
	 * 根据条件查询角色
	 * @param role
	 * @return
	 */
	List<Role> selectListSelective(Role role);
	/**
	 * 通过用户名称查询出角色
	 * @param map
	 * @return
	 */
	List<Role> selectRoleByUser(Map map);
	
	
}
