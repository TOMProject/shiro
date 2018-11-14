package com.shiro.realm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.entity.Menu;
import com.shiro.entity.Role;
import com.shiro.entity.Users;
import com.shiro.service.MenuService;
import com.shiro.service.RoleService;
import com.shiro.service.UsersService;
/**
 *  自定义Realm
 * @author 
 *
 */
public class MyRealm extends AuthorizingRealm {

	@Autowired
	UsersService usersSer;
	@Autowired
	RoleService roleSer;
	@Autowired
	MenuService menuSer;

	public  static final String SESSION_KEY	= "session_key";
	/**
	 * shiro 授权 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		System.out.println("shiro 开始授权");
		 String userName = (String)principal.getPrimaryPrincipal();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//根据用户获取角色集合
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		List<Role> roles = roleSer.selectRoleByUser(map);
		
		Set<String> roleNames = roles.stream().map(Role :: getRoleName).collect(Collectors.toSet());
		simpleAuthorizationInfo.setRoles(roleNames);
		List<Menu> menus = menuSer.selectMenuPermissionByUserName(userName);
		Set<String> permissionSet = new HashSet<String>();
		for (Menu m : menus) {
			if (m != null && m.getPermission() != null) {
				permissionSet.addAll(Arrays.asList(m.getPermission().split(",")));
			}
		}
		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		
		return simpleAuthorizationInfo;

	}

	
	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		Users user = new Users();
		//user.setPassWord(password);
		user.setUserName(username);
		Users u = usersSer.selectListSelective(user);
		if(u == null) {
			return null;
		}
		
//		Session session = SecurityUtils.getSubject().getSession();
//		session.setAttribute(SESSION_KEY, session);
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




