package com.tao.player.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInit
//implements WebApplicationInitializer 
{

	//@Override
	//public void onStartup(ServletContext servletContext) throws ServletException {
		//首先来加载 SpringMVC 的配置文件
        //AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        //ctx.register(SpringMVCConfig.class);
        //ctx.register(SpringConfig.class);
        // 添加 DispatcherServlet
        //ServletRegistration.Dynamic springmvc = servletContext.addServlet("springmvc", new DispatcherServlet(ctx));
        // 给 DispatcherServlet 添加路径映射
        //springmvc.addMapping("*.do");
        // 给 DispatcherServlet 添加启动时机
        //springmvc.setLoadOnStartup(1);
	//}

}
