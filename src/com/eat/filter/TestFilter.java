
package com.eat.filter;


import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eat.model.Member;

@WebFilter(urlPatterns = "/*")
public class TestFilter implements Filter{
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
					
		String sURI=req.getRequestURI();		
		int ps=sURI.lastIndexOf("/");
		sURI=sURI.substring(ps+1);
		System.out.println(sURI);
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
}