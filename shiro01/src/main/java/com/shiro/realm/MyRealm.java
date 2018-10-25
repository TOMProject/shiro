package com.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
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
		//user.setPassWord(password);
		user.setUserName(username);
		Users u = usersSer.selectListSelective(user);
		if(u == null) {
			return null;
		}
		
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(SESSION_KEY, session);
		String name = this.getName();
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);//加盐
		SimpleAuthenticationInfo sim = new SimpleAuthenticationInfo(username,u.getPassWord(),credentialsSalt, name);
		return sim;
	}
	
	
	
	public static void main(String[] args) {
	       String hashAlgorithmName = "MD5";
	        String credentials = "123456";//密码
	        int hashIterations = 1024;
	        ByteSource credentialsSalt = ByteSource.Util.bytes("admin");//盐值
	        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
	        System.out.println(obj);

	}
	
}
