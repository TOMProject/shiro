package com.shiro.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.entity.ShowPage;
import com.shiro.entity.Users;
import com.shiro.service.UsersService;
import com.shiro.utils.AjaxResponse;
import com.shiro.utils.Constant;

@Controller 
@RequestMapping(value = "/user")
public class ShiroController {

	@Autowired
	UsersService UsersSer;
	
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<Users> doLogin(@RequestBody Users users) {
		AjaxResponse<Users> ajaxResponse = new AjaxResponse<Users>(Constant.RS_CODE_ERROR,"登录失败！");
		// 手动设置用户名和密码，这里的用户名和密码和shiro.ini 配置文件里面的用户名和密码进行比较，
		UsernamePasswordToken token = new UsernamePasswordToken(users.getUserName(), users.getPassWord());
		//token.setRememberMe(true);//是否记住登录
		Subject user = SecurityUtils.getSubject();
		try {
			user.login(token);
			System.out.println("登录成功！");
		} catch (UnknownAccountException ex) {
			ajaxResponse.setMsg("用户名不存在！");
			return ajaxResponse;
		} catch (IncorrectCredentialsException ex) {
			ajaxResponse.setMsg("用户不存在或者密码错误！");
			return ajaxResponse;
		} catch (Exception ex) {
			ex.printStackTrace();
			ajaxResponse.setMsg("内部错误，请重试！");
			return ajaxResponse;
		}
		ajaxResponse.setCode(Constant.RS_CODE_SUCCESS);
		ajaxResponse.setMsg("登录成功！");
		ajaxResponse.setData(users);
		return ajaxResponse;
		
	}
	@RequestMapping(value="/listPaging",method=RequestMethod.POST)
	@RequiresPermissions("user:listPaging")
	@ResponseBody
	public AjaxResponse<ShowPage<Users>> showUsersPaging(@RequestBody Users user){
		AjaxResponse<ShowPage<Users>> ajaxResponse = new AjaxResponse<ShowPage<Users>>(Constant.RS_CODE_ERROR,"获取用户列表失败！");
		try {
			List<Users> u = UsersSer.selectListSelectivePaging(user);
			ShowPage<Users> page = new ShowPage<>(user, u);
			ajaxResponse.setData(page);
			ajaxResponse.setCode(Constant.RS_CODE_SUCCESS);
			ajaxResponse.setMsg("获取用户列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxResponse;
		}
		return ajaxResponse;
	}
	@RequestMapping(value="addUser",method=RequestMethod.POST)
	@RequiresPermissions("user:addUser")
	@ResponseBody
	public AjaxResponse<Object> addUser(@RequestBody Users user){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>(Constant.RS_CODE_ERROR,"添加用户失败!");
		try {
			UsersSer.addUser(user);
			ajaxResponse.setCode(Constant.RS_CODE_SUCCESS);
			ajaxResponse.setMsg("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResponse.setMsg("添加用户失败："+e.getMessage());
			return ajaxResponse;
		}	
		return ajaxResponse;
		
	}
	@RequestMapping(value="updataUser",method=RequestMethod.POST)
	@RequiresPermissions("user:updateUser")
	@ResponseBody
	public AjaxResponse<Object> updateUser(@RequestBody Users user){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>(Constant.RS_CODE_ERROR,"修改用户失败！");
		try {
			UsersSer.updateSelectiveById(user);
			ajaxResponse.setCode(Constant.RS_CODE_SUCCESS);
			ajaxResponse.setMsg("修改用户成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxResponse;
		}
		return ajaxResponse;
				
		
		
		
	}
	
	
	
}
