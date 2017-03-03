package com.zhj.system.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhj.utils.SessionUtil;
import com.zhj.vo.User;

public class RequestFilter implements Filter {

	private static final String LOGIN_URL = "/web/login.html";
	private static final String REGISTER_URL = "/web/doRegister.html";
	private static List<String> STATIC_FILE = new ArrayList<String>();
	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
        User user = SessionUtil.getCurrentUser(request);
        String uri = request.getRequestURI();
		String rootPath = request.getContextPath()+"/";
		if(null != uri && uri.toString().indexOf(rootPath) < 0) {
			//	进此if则说明访问的外网
			response.sendRedirect(rootPath);
		} else {
			if(null == user && !uri.endsWith(rootPath) && !uri.endsWith(LOGIN_URL) && !uri.endsWith(REGISTER_URL)) {
				//进此if则说明还未登录就访问了本网站的其他内容
				String sufferUrl = uri.substring(uri.lastIndexOf(".")).trim().toLowerCase();
				if(STATIC_FILE.contains(sufferUrl)) {	//静态资源放过过滤
					arg2.doFilter(request, response);
				} else {
					response.sendRedirect(rootPath);
				}
			}else {
				// 请求本网站请求时session过期
				//请求地址 放过
				arg2.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// 设置静态资源后缀名
		STATIC_FILE.add(".js");
		STATIC_FILE.add(".css");
		STATIC_FILE.add(".png");
	}

}
