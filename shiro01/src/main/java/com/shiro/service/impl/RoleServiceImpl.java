package com.shiro.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiro.entity.Role;
import com.shiro.mapper.RoleMapper;
import com.shiro.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public List<Role> selectListSelective(Role role) {
		return roleMapper.selectListSelective(role);
	}

	@Override
	public List<Role> selectRoleByUser(Map map) {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleByUser(map);
	}

}
