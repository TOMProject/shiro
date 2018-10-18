package com.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class ShiroUitls {

	public static Subject login(String classPath,String username,String password) {
		/**
		 * shiro 的核心是SecurityManagement 和 subject 安全管理和主题
		 */
		// 获取SecurityManagement 之前要获取 factory
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(classPath);
		// 通过factory 获取SecurityManagemer
		SecurityManager securityManagemer = factory.getInstance();
		// 将SecurityManage 注入到SerurityMangemer 当中
		SecurityUtils.setSecurityManager(securityManagemer);
		// 获取当前用户
		Subject user = SecurityUtils.getSubject();
		
		return user;

	}

}
