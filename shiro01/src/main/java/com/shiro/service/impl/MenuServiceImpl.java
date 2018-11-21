package com.shiro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.common.Tree;
import com.shiro.entity.Menu;
import com.shiro.mapper.MenuMapper;
import com.shiro.service.MenuService;
import com.shiro.utils.TreeUtils;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> selectMenuPermissionByUserName(String userName) {
		
		return menuMapper.selectMenuPermissionByUserName(userName);
	}

	@Override
	public Tree<Menu> selectMenu() {
		List<Tree<Menu>> trees = new ArrayList<>();
		List<Menu> menus = menuMapper.selectMenu();
		for (Menu menu : menus) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(menu.getMenuId().toString());
			tree.setParentId(menu.getParentId().toString());
			tree.setText(menu.getMenuName());
			trees.add(tree);
		}
		Tree<Menu> build = TreeUtils.build(trees);
		return build;
	}

	
	@Override
	public void insertSelective(Menu menu) {
		menu.setCreateTime(new Date());
		if(menu.getParentId() == null) {
			menu.setParentId(0);
		}
		menuMapper.insertSelective(menu);
	}

	@Override
	public List<Menu> selectMenuSelective(Menu menu) {
		
		return menuMapper.selectMenuSelective(menu);
	}

}
