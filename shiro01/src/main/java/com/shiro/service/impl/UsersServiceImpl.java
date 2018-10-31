package com.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiro.entity.PageEntity;
import com.shiro.entity.UserRole;
import com.shiro.entity.Users;
import com.shiro.mapper.RoleMapper;
import com.shiro.mapper.UserRoleMapper;
import com.shiro.mapper.UsersMapper;
import com.shiro.service.UsersService;
import com.shiro.utils.ShiroUitls;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersMapper usersMapper;
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	UserRoleMapper userRoleMapper;
	
	@Override
	public Users selectListSelective(Users user) {
		// TODO Auto-generated method stub
		return usersMapper.selectListSelective(user);
	}

	@Override
	public List<Users> selectListSelectivePaging(PageEntity pageEntity) {
		PageHelper.startPage(pageEntity.getPageNo(), pageEntity.getPageSize());
		List<Users> result = usersMapper.selectListSelectivePaging(pageEntity);
		PageInfo<Users> p =new PageInfo<Users>(result);
		pageEntity.setPageCount((int)p.getTotal());

		return result;
	}

	@Override
	public void addUser(Users user) {
		Users addUser = new Users();
		addUser.setUserName(user.getUserName());
		Users isExist = usersMapper.selectListSelective(addUser);
		if(isExist != null) {
			throw new RuntimeException("账号名重复！");
		}
		
		addUser.setReallyName(user.getReallyName());
		addUser.setPassWord(ShiroUitls.passwordMD5(user.getUserName(), user.getPassWord()));
		addUser.setPhone(user.getPhone());	
		usersMapper.insertSelective(addUser);
		
		Users u = usersMapper.selectListSelective(addUser);
		UserRole ur = new UserRole();
		ur.setRoleId(user.getRoleId());
		ur.setUserId(u.getId());
		userRoleMapper.insertSelective(ur);
	
	}

}
