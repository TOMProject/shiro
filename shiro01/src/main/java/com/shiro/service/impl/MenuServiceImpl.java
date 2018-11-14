package com.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.entity.Menu;
import com.shiro.mapper.MenuMapper;
import com.shiro.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> selectMenuPermissionByUserName(String userName) {
		
		return menuMapper.selectMenuPermissionByUserName(userName);
	}

}
