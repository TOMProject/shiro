package com.shiro;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;

import com.shiro.utils.ShiroUitls;

public class ShirDemo03 {

	public static void main(String[] args) {
		Subject user = ShiroUitls.login("classpath:shiro_role_permission.ini", "bigbird", "123456");
		boolean role1 = user.hasRole("role1");
		System.out.println("角色1--role1--"+role1);
		user.hasRole("role2");
		List<String>  roles= new ArrayList<>();
		roles.add("role2");
		roles.add("role1");
		boolean hasAllRoles = user.hasAllRoles(roles);
		System.out.println("haseAllRoles -- " + hasAllRoles);
		
		boolean flag = user.isPermitted("InRoom:insert");
		System.out.println("xiaofei-->"+flag);
		 //判断某个用户是否同时具有多个权限
        boolean[] flags = user.isPermitted("InRoom:xiaoFei", "InRoom:update");
        System.out.println("flags="+ Arrays.toString(flags));

        try {
            user.checkPermission("InRoom:insert");
            System.out.println("bigbird有消费记录权限");
        } catch (AuthorizationException e) {
            System.out.println("bigbird没有消费记录权限");
        }
		
		
	}
	
}
