package com.shiro.service;

import java.util.List;

import com.shiro.common.Tree;
import com.shiro.entity.Menu;

public interface MenuService {
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
	Tree<Menu> selectMenu();
	/**
	 * 新增菜单
	 * @param menu
	 */
	void insertSelective(Menu menu);
	
}
