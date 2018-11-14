package com.shiro.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.entity.Menu;
import com.shiro.utils.AjaxResponse;
import com.shiro.utils.Constant;

@Controller
@RequestMapping(value="/menu")
public class MenuController {
	
	@RequestMapping(value="tree",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<List<Menu>> getMenuTree(){
		AjaxResponse<List<Menu>> ajaxResponse = new AjaxResponse<>(Constant.RS_CODE_ERROR,"获取菜单失败！");
		
		
		
		return ajaxResponse;
		
	}
	
}
