package com.shiro.mapper;

import java.util.List;

import com.shiro.entity.Menu;

public interface MenuMapper {
	/**
	 * 通过用户名查询该用户拥有哪些权限
	 * @param userName
	 * @return
	 */
	List<Menu> selectMenuPermissionByUserName(String userName);
	/**
	 * 查询所有的菜单
	 * @return
	 */
	List<Menu> selectMenu();
	/**
	 * 添加用户
	 * @param menu
	 */
	void insertSelective(Menu menu);
	
}

