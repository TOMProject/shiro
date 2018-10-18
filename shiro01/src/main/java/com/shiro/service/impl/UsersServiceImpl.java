package com.shiro.service.impl;

import com.shiro.entity.Users;
import com.shiro.mapper.UsersMapper;
import com.shiro.service.UsersService;

//@Service
public class UsersServiceImpl implements UsersService {

	//@Autowired
	UsersMapper usersMapper;
	
	@Override
	public Users selctListSelictive(Users user) {
		// TODO Auto-generated method stub
		return usersMapper.selctListSelictive(user);
	}

}
