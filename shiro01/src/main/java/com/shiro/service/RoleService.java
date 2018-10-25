package com.shiro.service;

import java.util.List;

import com.shiro.entity.Role;

public interface RoleService {
	/**
	 * 根据条件查询角色
	 * @param role
	 * @return
	 */
	List<Role> selectListSelective(Role role);
}
