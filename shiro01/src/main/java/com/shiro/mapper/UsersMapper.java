package com.shiro.mapper;

import java.util.List;

import com.shiro.entity.PageEntity;
import com.shiro.entity.Users;

public interface UsersMapper {

	/**
	 * 查询用户
	 * @param user
	 * @return
	 */
	Users selctListSelictive(Users user);
	/**
	 * 分页
	 * @param user
	 * @return
	 */
	List<Users> selctListSelictivePaging(PageEntity pageEntity);
}
