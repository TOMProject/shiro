package com.shiro.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class LoginServlet extends HttpServlet{

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		System.out.println("login do post");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		try {
			subject.login(token);
			Session session = subject.getSession();
			System.out.println("session-id : "+session.getId());
			System.out.println("session-host : "+session.getHost());
			System.out.println("session-timeout : "+session.getTimeout());
			session.setAttribute("json", "json 数据");//向前端传输json数据；
			response.sendRedirect("index.jsp");//重定向到前端页面
					
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "用户名或者密码错！");
			request.getRequestDispatcher("login.jsp").forward(request, response);//请求转发
		}
		
	}
		
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login do get");
		doPost(request, response);
		
	}
	
}
