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

@Controller 
public class ShiroController {

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String doLogin(@RequestParam("username") String username,@RequestParam("password") String password) {

		// 手动设置用户名和密码，这里的用户名和密码和shiro.ini 配置文件里面的用户名和密码进行比较，
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);//是否记住登录
		Subject user = SecurityUtils.getSubject();
		try {
			user.login(token);
			System.out.println("登录成功！");
		} catch (UnknownAccountException ex) {
			return "用户不存在或者密码错误！";
		} catch (IncorrectCredentialsException ex) {
			return "用户不存在或者密码错误！";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "内部错误，请重试！";
		}
		return "success";
		
	}
	
}
