package com.tao.player.controller;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.tao.player.pojo.AuthUserRole;
import com.tao.player.service.IAuthUserRoleService;
import com.tao.player.service.impl.AuthUserRoleService;
import com.tao.player.util.proxy.ProxyService;

@WebServlet("/AuthUserRoleController")
public class AuthUserRoleController extends HttpServlet{
private static final long serialVersionUID=1L;

	private IAuthUserRoleService authUserRoleService = (IAuthUserRoleService)new ProxyService().setTarget(new AuthUserRoleService()).getProxy();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodname = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodname, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);// 调用各自的方法
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void findAuthUserRoleById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			AuthUserRole authUserRole = authUserRoleService.findAuthUserRoleById(id);
			
			JSONObject result = new JSONObject();
			result.put("data",authUserRole);
			result.put("code", "02");
			System.out.println(result);
			response.getWriter().println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().close();
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void addAuthUserRole(HttpServletRequest request, HttpServletResponse response) {
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		AuthUserRole authUserRole = new AuthUserRole(roleId, userId);
		try {
			authUserRoleService.addAuthUserRole(authUserRole);

			JSONObject result = new JSONObject();
			result.put("code", "01");
			response.getWriter().println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().close();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException |SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void deleteAuthUserRoleById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id==" + id);
			authUserRoleService.deleteAuthUserRoleById(id);
			
			JSONObject result = new JSONObject();
			result.put("code", "02");
			System.out.println(result);
			response.getWriter().println(result);
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().close();
		} catch (SecurityException | IllegalArgumentException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}