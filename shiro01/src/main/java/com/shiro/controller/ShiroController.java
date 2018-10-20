package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.entity.Users;
import com.shiro.utils.AjaxResponse;
import com.shiro.utils.Constant;

@Controller 
@RequestMapping(value = "/user")
public class ShiroController {

	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<Users> doLogin(@RequestParam("username") String username,@RequestParam("password") String password) {
		AjaxResponse<Users> ajaxResponse = new AjaxResponse<Users>(Constant.RS_CODE_ERROR,"登录失败！");
		// 手动设置用户名和密码，这里的用户名和密码和shiro.ini 配置文件里面的用户名和密码进行比较，
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
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
		ajaxResponse.setData((Users)user);
		return ajaxResponse;
		
	}
	
}
