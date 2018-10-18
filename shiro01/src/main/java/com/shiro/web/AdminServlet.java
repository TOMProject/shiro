package com.shiro.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {

	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		  System.out.println("admin do post");
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		  System.out.println("admin do get");
	}
	
}
