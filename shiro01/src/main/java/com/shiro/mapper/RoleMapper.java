package com.shiro.mapper;

import java.util.List;

import com.shiro.entity.Role;

public interface RoleMapper {
	/**
	 * 根据条件查询角色
	 * @param role
	 * @return
	 */
	List<Role> selectListSelective(Role role);
}
