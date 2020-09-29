package com.tao.player.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.tao.player.pojo.AuthUser;
import com.tao.player.util.collection.CollectionBuilder;

public class UserFilter implements Filter {
	
	private FilterConfig filterConfig;
	
	// 排除不需要登陆的URI
	private final String[] IGNORE_URI = {"/ImageServlet", "/static/login.html", "/static/mp3", "/static/css", "/static/image", "/static/js", "/static/layui", "/static/lib"};

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init-===========init");
		this.filterConfig = filterConfig;
	}

	private boolean canIgnore(HttpServletRequest request) {
		String uri = request.getRequestURI();
		System.out.println("uri=="+uri);
		uri=uri.replace(request.getContextPath(),"");
		System.out.println("uri=="+uri);
		for (String val : IGNORE_URI) {
			if (uri.startsWith(val)) {
				return true;
			}
		}
		
		String method = request.getParameter("method");
		if("/AuthUserController".equals(uri)&&"login".equals(method)) {
			System.out.println("login success");
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {
		System.out.println("destroy-===========destroy");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		String encoding = filterConfig.getInitParameter("encoding");
		System.out.println("doFilter-===========doFilter encoding=="+encoding);
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);

		if (canIgnore(request)) {
			System.out.println("true=============true");
			chain.doFilter(servletRequest, servletResponse);
		}else {			
			HttpSession session = request.getSession(true);
			AuthUser user = (AuthUser) session.getAttribute("currentAuthUser");
			System.out.println("currentAuthUser == "+user);
			if (user != null) {
				System.out.println("========================================start============================================");
				chain.doFilter(servletRequest, servletResponse);
				System.out.println("========================================end==============================================");
			} else {
				if (request.getHeader("x-requested-with") == null)
					response.sendRedirect(request.getContextPath()+"/static/login.html");
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().print(CollectionBuilder.builder(new JSONObject()).put("code", -101).put("msg", "error")
						.put("content", "用户已过期，请重新登录").build());
			}
		}
	}
}
