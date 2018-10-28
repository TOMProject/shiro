package com.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.entity.Permission;
import com.shiro.mapper.PermissionMapper;
import com.shiro.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionMapper permissionMapper;
	
	@Override
	public List<Permission> selectPermissionByUserName(String userName) {
		// TODO Auto-generated method stub
		return permissionMapper.selectPermissionByUserName(userName);
	}

	
	
}
