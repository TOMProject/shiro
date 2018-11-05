package com.shiro.realm;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

/**
 * 自定义session 保存持久化
 * @author xyc
 *
 */

public class MySessionDao extends CachingSessionDAO{

	@Override
	protected void doUpdate(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doDelete(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Serializable doCreate(Session session) {
		System.out.println("开始创建session--!");
		return null;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		System.out.println("开始读取session !");
		return null;
	}

}
