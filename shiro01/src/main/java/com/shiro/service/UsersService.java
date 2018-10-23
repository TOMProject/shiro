package com.shiro.service;

import java.util.List;

import com.shiro.entity.PageEntity;
import com.shiro.entity.Users;

public interface UsersService {
	/**
	 * 查询用户
	 * @param user
	 * @return
	 */
	Users selctListSelictive(Users user);
	/**
	 * 
	 * @return
	 */
	List<Users> selctListSelictivePaging(PageEntity pageEntity);
}
