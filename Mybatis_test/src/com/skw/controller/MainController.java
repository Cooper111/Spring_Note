package com.skw.controller;

import java.util.LinkedList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.skw.domain.User;
import com.skw.mapper.UserMapper;

@Controller
public class MainController {
	
	public MainController() {
	}
	
	@Autowired//Spring容器自动注入对象到变量中
	private UserMapper userMapper;
	
	@RequestMapping(value="/userInfo", method= {RequestMethod.GET})
	public String userInfo(Model model,
						HttpSession session) {
		User user = (User)session.getAttribute("user");
		System.out.println("进入用户"+user.getName()+"的详情界面");
		model.addAttribute("user",user);
		return "userInfo";
	}
	
	@RequestMapping(value="/main", method= {RequestMethod.GET})
	public String main(Model model) {
		
		LinkedList<User> users = userMapper.selectUsers();
		for(User user:users) {
			user.setTemp("images/"+user.getName()+".jpg");
		}
		model.addAttribute("user_list", users);
		return "main";
	}
}
