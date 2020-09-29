package com.tao.player.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

import com.tao.player.util.jdbc.JdbcUtil;

public class ProxyService implements InvocationHandler {
	
	private Object target;

	public ProxyService setTarget(Object target) {
		this.target = target;
		return this;
	}

	public Object getProxy() {
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object object = null;
		try {
			System.out.println("methodName="+method.getName());
			System.out.println("setAutoCommitFalse==");
			setAutoCommitFalse();
			
			object = method.invoke(target, args);
			
			System.out.println("commit==");
			commit();
		} catch (RuntimeException e) {
			System.out.println("33333333");
			rollback();
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("44444444");
			rollback();
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("55555");
			rollback();
			e.printStackTrace();
		}
		return object;
	}
	
	public void setAutoCommitFalse() throws SQLException {
		JdbcUtil.getConn().setAutoCommit(false);
	}
	
	public void rollback() {
		JdbcUtil.rollback();
	}
	
	public void commit() {
		JdbcUtil.commit();
	}
}
