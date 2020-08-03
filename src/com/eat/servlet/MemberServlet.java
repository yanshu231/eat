package com.eat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eat.dao.Dao;

//@WebServlet(urlPatterns = "/member")
public class MemberServlet extends HttpServlet {
	Dao dao = new Dao();

	/**
	 * 可以把方法写在doPost()方法中,在doGet()方法中调用执行,这样,无论你提交的是post还是get方法都可以执行
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// login/register
		//获取请求参数
		String op = req.getParameter("op");
		String memid = req.getParameter("memid");
		String mempass = req.getParameter("mempass");
		String memalias = req.getParameter("memalias");
		// menu
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String pic = req.getParameter("pic");
		String remark = req.getParameter("remark");
		//PrintWriter它的实例就是向前台的页面输出结果
		//获取一个向浏览器输出的对象
		PrintWriter out = resp.getWriter();
		String sql = null;// 初始化
//		resp.getWriter().print(sql);
		dao.exeUpdate(sql);
		if (op == null) {
			op = "";
		}
		switch (op) {
		case "login":
			sql = "select * from Member where memid='" + memid + "'" + " and mempass='" + mempass + "'";
			if (dao.isExist(sql)) {
				req.getSession().setAttribute("islogin", memid);
				// 会话连接
				out.print("login success!");
			} else {
				req.getSession().setAttribute("islogin", "htc_null");
				// 会话连接
				out.print("login failure!");
			}
			break;
		case "logout":
			//
			req.getSession().setAttribute("islogin", "htc_null");// //对islogin对象赋值
			resp.sendRedirect("login.html");
		break;	
		case "islogin":
			//getSession会话连接
			String islogin=(String) req.getSession().getAttribute("islogin");//取得islogin对象的值
			if(islogin==null) {
				islogin="htc_null";
			}
			out.print(islogin);
			break;
		case "check":
			sql = "select * from Member where memid='" + memid + "'";
			if (dao.isExist(sql)) {
				out.print("have");
			} else {
				out.print("meiyou");
			}
			break;
		case "register":
			sql = "insert into Member(memid,mempass,memalias) values('" + memid + "','" + mempass + "','" + memalias
					+ "')";
			if (dao.exeUpdate(sql)) {
				// out.print("register success");
				resp.sendRedirect("login.html");

			} else {
				// out.print("register fail!");
				resp.sendRedirect("register.html");
			}
			break;
		
		}
	}
}
