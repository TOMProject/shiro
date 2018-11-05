package com.shiro.realm;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * 我的session监听器。
 * @author 
 *
 */
public class MySessionlistener implements SessionListener {

	@Override
	public void onStart(Session session) {
		System.out.println("session 会话被创建！");
	}

	@Override
	public void onStop(Session session) {
		System.out.println("session 会话被终止！");
		
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("session 会话过期被回调！ ");
		
	}

	
	
}
