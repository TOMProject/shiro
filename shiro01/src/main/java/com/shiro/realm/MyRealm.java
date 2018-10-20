package com.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.entity.Users;
import com.shiro.service.UsersService;
/**
 *  自定义Realm
 * @author 
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Autowired
	UsersService usersSer;
	
	
	public  static final String SESSION_KEY	= "session_key";
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		
		return null;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		String password = String.valueOf(token.getPassword());
		Users user = new Users();
		user.setPassWord(password);
		user.setUserName(username);
		Users u = usersSer.selctListSelictive(user);
		if(u == null) {
			return null;
		}
		
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(SESSION_KEY, session);
		String name = this.getName();
		//Object principal = authenticationToken.getPrincipal(); 
		SimpleAuthenticationInfo sim = new SimpleAuthenticationInfo(u,u.getPassWord(), name);
		return sim;
	}
	
}
