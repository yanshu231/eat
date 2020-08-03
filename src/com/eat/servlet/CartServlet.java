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

//@WebServlet(urlPatterns = "/cart")

public class CartServlet extends HttpServlet {
	Dao dao = new Dao();
	/**
	 * 可以把方法写在doPost()方法中,在doGet()方法中调用执行,这样,无论你提交的是post还是get方法都可以执行
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req.setCharacterEncoding("utf-8");
		doPost(req, resp);
	}
	//
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取html页面中op对象的值
		String op = req.getParameter("op");
		String id = req.getParameter("id");
		String menuid = req.getParameter("menuid");
		/**
		 * response顾名思义就是服务器对浏览器的相应，PrintWriter它的实例就是向前台的JSP页面输出结果，比如
out.print("Hello World")；
		 */
		PrintWriter out = resp.getWriter();
		String sql = null;
		if (op == null) {
			op = "";
		}
		/**
		 * 通过req.getsession()获得session 对象 再调用它的getAttibute（String key）方法来获得含有关键字“islogin”的对象！
		 */
		String memid = (String) req.getSession().getAttribute("islogin");
		switch (op) {
		case "insert":
			sql = "insert into cart(memid) values('" + menuid + "', '" + memid + "')";
			if (dao.exeUpdate(sql)) {
				out.print("addcart success!");
			} else {
				out.print("addcart failure!");
			}
			break;
		case "delete":
			sql = "delete from cart where menuid=" + menuid+" and memid='"+memid+"'";
			if (dao.exeUpdate(sql)) {
				out.print("delete success!");
			} else {
				out.print("delete failure!");
			}
			break;
		case "getcart":
			String name = req.getParameter("name");
			if (name == null) {
				name = "";
			}
			sql = "select menu.id,pic,name,price,remark " + " from menu,cart" + " where menu.id=cart.menuid"
					+ " and memid='" + memid + "'" + " and name like '%" + name + "%'";
			System.out.println(sql);
			List lst = dao.getData(sql);
			// resp.getWriter().print(lst.size());
			
			out.println(MyUtil.toJSON(lst));
			break;
		default:
			break;
		}

	}
}
