package com.lixinyu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lixinyu.common.CmsConstant;
import com.lixinyu.common.JsonResult;
import com.lixinyu.pojo.Tousu;
import com.lixinyu.pojo.User;
import com.lixinyu.service.TousuService;

@Controller
@RequestMapping("/tousu/")
public class TousuController {
	@Autowired
	private TousuService toususervice;
	/**
	 * @Title: add   
	 * @Description: 添加投诉 
	 * @param: @param comment
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public @ResponseBody JsonResult add(Tousu tousu,HttpSession session) {
		User userInfo = (User)session.getAttribute(CmsConstant.UserSessionKey);
		if(userInfo==null) {
			return JsonResult.fail(CmsConstant.unLoginErrorCode, "用户未登录");
		}
		tousu.setUserId(userInfo.getId());
		boolean result = toususervice.add(tousu);
		if(result) {
			return JsonResult.sucess();
		}
		return JsonResult.fail(10000, "未知错误");
	}
}
