package com.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.entity.PageEntity;
import com.shiro.entity.Users;
import com.shiro.mapper.UsersMapper;
import com.shiro.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersMapper usersMapper;
	
	@Override
	public Users selctListSelictive(Users user) {
		// TODO Auto-generated method stub
		return usersMapper.selctListSelictive(user);
	}

	@Override
	public List<Users> selctListSelictivePaging(PageEntity pageEntity) {
		PageHelper.startPage(pageEntity.getPageNo(), pageEntity.getPageSize());
		List<Users> result = usersMapper.selctListSelictivePaging(pageEntity);
		PageInfo<Users> p =new PageInfo<Users>(result);
		pageEntity.setPageCount((int)p.getTotal());

		return result;
	}

}
