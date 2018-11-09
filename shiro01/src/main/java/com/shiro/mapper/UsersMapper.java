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
	Users selectListSelective(Users user);
	/**
	 * 分页
	 * @param user
	 * @return
	 */
	List<Users> selectListSelectivePaging(PageEntity pageEntity);
	/**
	 * 添加用户
	 * @param user
	 */
	void insertSelective(Users user);
	/**
	 * 修改用户
	 * @param user
	 */
	void updateSelectiveById(Users user);
}
