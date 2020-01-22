package com.skw.controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Pattern.Flag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.skw.domain.User;
import com.skw.mapper.UserMapper;
import com.sun.org.apache.bcel.internal.generic.I2F;
//由它代替Servlet
@Controller
public class UserController {
	public UserController() {
	}
	
	@Autowired//Spring容器自动注入对象到变量中
	private UserMapper userMapper;
	
	
	
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET})
	public String LoginForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	@RequestMapping(value="/login", method= {RequestMethod.POST})
	public ModelAndView login(
			@ModelAttribute User user,
			ModelAndView model,
			HttpSession session) {
		//查询用户是否存在
		boolean flag = false;
		String name = user.getName();
		User user1 = userMapper.selectUser(name);
		if(user1 != null) {
			flag = true;
		}
		//登陆拦截时候使用
		session.setAttribute("user", user);

		model.addObject("user",user);
		//如果用户存在
		if(flag) {
			model.addObject("user", user);
			model.setViewName("redirect:main");
			return model;
		}else {
			model.setViewName("error");
			return model;
		}
	}
	
	@RequestMapping(value="/registForm", method= {RequestMethod.GET})
	public String registForm(
					String request_locale,
					Model model,
					HttpServletRequest request,
					HttpServletResponse response) {
		
		System.out.println("request_locale = "+request_locale);
		if(request_locale != null) {
			if(request_locale.equals("zh_CN")) {
				Locale locale = new Locale("zh","CN");
				(new CookieLocaleResolver()).setLocale (request,response,locale);
			}else if(request_locale.equals("en_US")) {
				Locale locale = new Locale("en","US");
				(new CookieLocaleResolver()).setLocale (request,response,locale);
			}else {
				(new CookieLocaleResolver()).setLocale (request,response,LocaleContextHolder.getLocale());
			}
		}
		
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
	@RequestMapping(value="/regist", method= {RequestMethod.POST})
	public ModelAndView regist(
					HttpServletRequest request,
					@Valid @ModelAttribute User user,
					Errors errors,
					ModelAndView model) throws IllegalStateException, IOException {
		
		if(errors.hasErrors()) {
			model.addObject("message", "注册格式不对！！");
			model.setViewName("register");
			return model;
		}
		//查询用户是否存在
		boolean flag = false;
		String name = user.getName();
		User user1 = userMapper.selectUser(name);
		if(user1 != null) {
			flag = true;
		}
		//如果存在，返回提示
		if(flag) {
			model.addObject("message", "该用户已存在！");
			model.setViewName("register");
			return model;
		}else {//如果不存在，执行注册和头像上传
			int unkown = userMapper.insertUser(user);
			System.out.println("用户"+user.getName()+"插入数据库，insert语句执行结果为"+unkown);
			//接下来是上传头像
			//如果文件不为空，写入上传路径
			System.out.println("user.getImage(): "+user.getImage());
			if(!user.getImage().isEmpty()) {
				//上传文件路径
				//getservletcontext()函数获取当前服务器对象，可以调用其下的各种方法
				String path = request.getServletContext().getRealPath("/images");
				//上传文件名
				//String filename = user.getImage().getOriginalFilename();
				String filename = user.getName();
				File filepath = new File(path,filename);
				//判断路径是否存在，如果不存在就创建一个
				if(!filepath.getParentFile().exists()) {
					filepath.getParentFile().mkdirs();
				}
				//将上传文件保存到一个目标文件当中
				user.getImage().transferTo(new File(path+File.separator+filename+".jpg"));
				System.out.println("上传文件路径"+(path+File.separator+filename+".jpg"));
			}
			
			model.setViewName("success");
			return model;
		}
	}
}
