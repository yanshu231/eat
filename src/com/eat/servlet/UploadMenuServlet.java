package com.eat.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.channels.NonReadableChannelException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.eat.dao.Dao;
import com.eat.model.Menu;
import com.eat.util.MyUtil;


//@WebServlet(urlPatterns = "/menu")
public class UploadMenuServlet extends HttpServlet{
	Dao dao=new Dao();
	/**
	 * 可以把方法写在doPost()方法中,在doGet()方法中调用执行,这样,无论你提交的是post还是get方法都可以执行
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8");
	}
	//创建servlet
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Menu menu=new Menu();
		System.out.println(req.getCharacterEncoding());
		try {
	        //
			DiskFileItemFactory factory =new DiskFileItemFactory();
			//
			ServletFileUpload upload=new ServletFileUpload(factory);
			//
			List<FileItem> formItems=upload.parseRequest(req);
			
			if (formItems!=null && formItems.size()>0) {
				for (FileItem item : formItems) {
					if (item.isFormField()) {
						MyUtil.setParams(item, menu);
					}else {
						//ֵ
						String filename=item.getName();
						String ext=filename.substring(filename.lastIndexOf("."));
						String sPath=req.getRealPath("/")+"/upload/";//
//						System.out.println(menu.getPic())
						menu.setPic(menu.getName()+"_"+ext);
						mkdir(sPath);
						item.write(new File(sPath+menu.getName()+"_"+ext));
					}
				}
			
			}
			String sellname=(String) req.getSession().getAttribute("selllogin");
			int sellid=dao.count("select id from seller where name='"+sellname+"'");
			String sql="insert into menu(name,price,remark,pic,sellid) values('"+menu.getName()+"','"+menu.getPrice()+"','"
			+menu.getRemark()+"','"+menu.getPic()+"','"+sellid+"')";
			
			if (dao.exeUpdate(sql)) {
				resp.sendRedirect("sellmenu.html");
			}else {
				resp.sendRedirect("dismenu.html");
			}
			
//			req.setAttribute("msg", "upload success!!");
//			req.setAttribute("menu", menu);
//			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void mkdir(String sPath) {
		File uploadDir = new File(sPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
	}
}