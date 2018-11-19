package com.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.common.Tree;
import com.shiro.entity.Menu;
import com.shiro.service.MenuService;
import com.shiro.utils.AjaxResponse;
import com.shiro.utils.Constant;

@Controller
@RequestMapping(value="/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuSer;
	
	
	@RequestMapping(value="addMenu",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<Object> saveMenu(@RequestBody Menu menu){
		AjaxResponse<Object> ajaxResponse = new AjaxResponse<Object>(Constant.RS_CODE_ERROR,"新增菜单失败！");
		try {
			menuSer.insertSelective(menu);
			ajaxResponse.setCode(Constant.RS_CODE_SUCCESS);
			ajaxResponse.setMsg("新增菜单成功!");
		} catch (Exception e) {
			return ajaxResponse;
		}
		
		return ajaxResponse;
				
		
	}

	@RequestMapping(value="tree",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<Tree<Menu>> getMenuTree(){
		AjaxResponse<Tree<Menu>> ajaxResponse = new AjaxResponse<>(Constant.RS_CODE_ERROR,"获取菜单失败！");
		try {
			Tree<Menu> trees = menuSer.selectMenu();
			ajaxResponse.setData(trees);
			ajaxResponse.setMsg("获取菜单成功");
			ajaxResponse.setCode(Constant.RS_CODE_SUCCESS);
		} catch (Exception e) {
			return ajaxResponse;
		}
		return ajaxResponse;
	}
	
	
	
	
	
	
}
