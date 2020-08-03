package com.eat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eat.dao.Dao;
import com.eat.model.Menu;
import com.eat.util.MyUtil;

//@WebServlet(urlPatterns = "/getmenu")

public class DealMenuServlet extends HttpServlet {
	Dao dao = new Dao();
	/**
	 * 可以把方法写在doPost()方法中,在doGet()方法中调用执行,这样,无论你提交的是post还是get方法都可以执行
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	//
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name=req.getParameter("name");
		System.out.println(name);
		String sql=null;
		if(name==null) {//显示所有
			sql="select name,price,pic,remark from menu";
			
		}else {//显示name为那个的
			sql="select id,name,price,pic,remark from menu where name like '%"+name+"%'";
		}
		List lst = dao.getData(sql);
		
		// resp.getWriter().print(lst.size());
		PrintWriter out = resp.getWriter();
		out.println(MyUtil.toJSON(lst));

	}
}
