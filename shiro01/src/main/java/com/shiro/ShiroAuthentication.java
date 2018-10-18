package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * shiro 认证
 * @author xyc
 *
 */
public class ShiroAuthentication {

	public static void main(String[] args) {
		/**
		 * shiro 的核心是SecurityManagement 和 subject 安全管理和主题
		 */
		//获取SecurityManagement 之前要获取 factory
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//通过factory 获取SecurityManagemer
		SecurityManager securityManagemer = factory.getInstance();
		//将SecurityManage 注入到SerurityMangemer 当中
		SecurityUtils.setSecurityManager(securityManagemer);
		//获取当前用户
		Subject user = SecurityUtils.getSubject();
		//测试使用session
		Session session = user.getSession();
		session.setAttribute("sessionkey", "sessionValue");
		String value = (String)session.getAttribute("sessionkey");
		if(value.equals("sessionValue")) {
			System.out.println("Retrieved the correct value! 检索正确的值");	
		}	
		//手动设置用户名和密码，这里的用户名和密码和shiro.ini 配置文件里面的用户名和密码进行比较，
		UsernamePasswordToken tokey = new UsernamePasswordToken("admin", "123456");

		try {
			user.login(tokey);
			System.out.println("登录成功！");
			//测试当前用户是否被认证，即是否被登录
			if(user.isAuthenticated()) {
				System.out.println("被认证登录");
			}
		} catch (Exception e) {
			System.out.println("登录失败！");
		}
	
		user.logout();//用户退出登录
	
	}
}
