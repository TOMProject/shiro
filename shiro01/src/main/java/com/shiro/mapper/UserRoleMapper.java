package com.shiro.mapper;

import com.shiro.entity.UserRole;

public interface UserRoleMapper {
	/**
	 * 添加用户和角色关系
	 * @param ur
	 */
	void insertSelective(UserRole ur);
	
}
