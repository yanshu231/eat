package com.eat.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eat.dao.Dao;
import com.eat.util.MyUtil;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

//import com.eat.menu.*;
//import com.eat.myutil.*;
import java.util.*;
//@WebServlet(urlPatterns = "/sellermenu")
public class SellerMenuServlet extends HttpServlet{
	Dao dao=new Dao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取html中name对象的值
		String name=req.getParameter("name");
		String op=req.getParameter("op");
		String menuid=req.getParameter("menuid");
		PrintWriter out = resp.getWriter();
		//取得selllogin对象的值
		String sellname=(String) req.getSession().getAttribute("selllogin");
		if(op==null) {
			op="";
		}
		String sql=null;
		int sellid=dao.count("select id from seller where name='"+sellname+"'");
		switch (op) {
		case "delete":
			sql = "delete from menu where id=" + menuid;
			if (dao.exeUpdate(sql)) {
				out.print("delete success!");
			} else {
				out.print("delete failure!");
			}
			break;
		case "check":
			sql = "select * from menu where name='" + name + "'";
			if (dao.isExist(sql)) {
				out.print("have");
			} else {
				out.print("meiyou");
			}
			break;
		case "select":
			if(name==null) {
				sql="select id,name,price,pic,remark from menu where sellid="+sellid;
			}else {
				sql="select id,name,price,pic,remark from menu where name like '%"+name+"%' and sellid='"+sellid+"'";
			}
			List lst=dao.getData(sql);
			out.print(MyUtil.toJSON(lst));
			break;
		default:
			break;
		}
		
		
	}
	
}
