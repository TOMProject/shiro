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
	Users selectListSelective(Users user);
	/**
	 * 
	 * @return
	 */
	List<Users> selectListSelectivePaging(PageEntity pageEntity);
	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(Users user);
	/**
	 * 修改用户
	 * @param user
	 */
	void updateSelectiveById(Users user);
}
