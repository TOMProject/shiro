package com.shiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.entity.Role;
import com.shiro.service.RoleService;
import com.shiro.utils.AjaxResponse;
import com.shiro.utils.Constant;

/**
 * 角色controller
 * @author 
 *
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {

	@Autowired
	RoleService roleSer;
	
	@RequestMapping(value="/getRoleList",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResponse<List<Role>> getRoleList(@RequestBody Role role){
		AjaxResponse<List<Role>> ajaxResponse = new AjaxResponse<List<Role>>(Constant.RS_CODE_ERROR,"获取角色失败！");
		try {
			List<Role> roles = roleSer.selectListSelective(role);
			ajaxResponse.setCode(Constant.RS_CODE_SUCCESS);
			ajaxResponse.setMsg("获取角色成功");
			ajaxResponse.setData(roles);
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxResponse;
		}
		return ajaxResponse;
		
	}
}
