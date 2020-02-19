package com.lixinyu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lixinyu.pojo.User;
import com.lixinyu.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userservice;
	@RequestMapping("/logins")
	public String logins(Model m,HttpSession session,User user) {
		int rs=userservice.logins(user);
		if(rs>0) {
			session.setAttribute("name", user.getUsername());
			return "admin/home";
		}else {
			m.addAttribute("error","登录失败");
			return "admin/login";
		}
	}
}
