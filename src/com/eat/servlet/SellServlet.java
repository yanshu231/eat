package com.eat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eat.dao.Dao;

//@WebServlet(urlPatterns = "/seller")
public class SellServlet extends HttpServlet {
	Dao dao = new Dao();
	/**
	 * 可以把方法写在doPost()方法中,在doGet()方法中调用执行,这样,无论你提交的是post还是get方法都可以执行
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = req.getParameter("op");
		//login/register
		//String id = req.getParameter("id");
		String name = req.getParameter("name");
	//	name=new String(name.getBytes(),"utf-8");
		String pass = req.getParameter("pass");
		String tel = req.getParameter("tel");
		String addr = req.getParameter("addr");
		
		PrintWriter out = resp.getWriter();
		String sql = null;
//		resp.getWriter().print(sql);
		dao.exeUpdate(sql);

		if (op == null) {
			op = "";
		}
		switch (op) {
		case "slogin":
			sql="select * from seller where name='"+name+"'"+"and pass='"+pass+"'";
			if (dao.isExist(sql)) {
				req.getSession().setAttribute("selllogin", name);
				out.print("success");
			}else {
				req.getSession().setAttribute("selllogin", "mtc_null");
				out.print("failure");
			}
			break;
		case "logout":
				req.getSession().setAttribute("selllogin", "mtc_null");
				resp.sendRedirect("slogin.html");
			break;
		case "islogin":
			String islogin=(String) req.getSession().getAttribute("selllogin");
			if(islogin==null) {
				islogin="mtc_null";
			}
			out.print(islogin);
		break;
		case "check":
			sql = "select * from seller where name='" + name + "'";
			if (dao.isExist(sql)) {
				out.print("have");
			}else {
				out.print("meiyou");
			}
			break;
		case "sregister":
			sql="insert into seller(name,pass,tel,addr)"+"values('"+name+"','"+pass+"','"+tel+"','"+addr+"')";
			if (dao.exeUpdate(sql)) {
				//out.print("register success");
				resp.sendRedirect("slogin.html");//跳转
			} else {
				//out.print("register fail!");
				resp.sendRedirect("sregister.html");//停留在本页面
			}
			break;
		}
	}
}
