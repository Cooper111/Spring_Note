package com.skw.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skw.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 拦截器必须实现HandlerInterceptor接口
 *
 */
public class AuthorizationInterceptor implements HandlerInterceptor{
	
	//不拦截“/loginForm”和"/login"请求
	private static final String[] IGNORE_URI = {"/loginForm","login","/uploadForm","/registForm","/regist","download","/upload",};
	
	/**
	 * 该方法将在整个请求完成之后执行，主要作用是清理资源
	 * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response,Object handler,Exception exception)
			throws Exception{
		System.out.println("AuthorizationInterceptor afterCompletion -->");
		
	}
	/**
	 * 该方法将在Controller的方法调用之后执行，方法中可以对ModelAndView进行操作，
	 * 该方法也只能在当前Interceptor的preHandle方法的返回值为true时才会执行
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response,Object handler,ModelAndView mv)
			throws Exception{
		System.out.println("AuthorizationInterceptor postHandle -->");
		
	}
	/**
	 * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前被调用，
	 * 该方法的返回值为true时拦截器才会继续往下执行，该方法的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,Object handler) {
		System.out.println("AuthorizationInterceptor postHandle -->");
		//flag变量用于判断用户是否登录，默认为false
		boolean flag = false;
		//获取请求的路径进行判断
		String servletPath = request.getServletPath();
		//判断请求是否需要拦截
		for(String s : IGNORE_URI) {
			if(servletPath.contains(s)) {
				flag = true;
				break;
			}
		}
		//1.获取session中的用户
		User user = (User) request.getSession().getAttribute("user");
		if(user != null && !user.getName().isEmpty()) {
			flag = true;
		}
		
		//拦截请求，未登录
		if(!flag) {
			//如果用户没有登陆，则设置提示信息，跳转到登陆页面
			System.out.println("AuthorizationInterceptor 拦截请求:");
			request.setAttribute("message", "请先登陆再访问网站");
			try {
				request.getRequestDispatcher("loginForm").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
				//System.out.println(user.getName().isEmpty());
				//如果用户已经登陆，则验证通过，放行
				System.out.println("AuthorizationInterceptor 放行请求：");
			}
		return flag;
	}

}
