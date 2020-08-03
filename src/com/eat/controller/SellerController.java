package com.eat.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.JasperException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eat.model.Seller;
import com.eat.service.ISellerService;




@Controller
@RequestMapping("/seller")
public class SellerController  {
	Logger log=Logger.getLogger(SellerController.class);
	
	@Autowired
	ISellerService sellerService;
	
	
	/**
	 * 检查用户注册名重复
	 * @param req
	 * @param resp
	 * @param memid
	 * @throws IOException
	 */
	@RequestMapping("/check/{name}")
	public void check(HttpServletRequest req, HttpServletResponse resp,@PathVariable String name) throws IOException {
		PrintWriter out=resp.getWriter();
		if(sellerService.check(name)){
			out.print("have");
		}else {
			out.print("not");
		}
	}
	
	/**
	 * 删除用户
	 * @param req
	 * @param resp
	 * @param memid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/delete/{sellid}",method = RequestMethod.GET)
	private String delete(HttpServletRequest req, HttpServletResponse resp,@PathVariable String sellid) throws IOException {
		if(sellerService.delete(sellid)){
			return "showUser";	
		}else {
			return "user";		
		}
	}
	
	/**
	 * 用户登录检测
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping(value="/islogin",method=RequestMethod.GET)
	public  void  isLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out=resp.getWriter();
		//getSession会话连接
		String islogin=(String.valueOf(req.getSession().getAttribute("selllogin")));//取得islogin对象的值
		log.debug(islogin);
		if(islogin==null) {
			islogin="mtc_null";
		}
		out.print(islogin);
	}
	
	/**
	 * 用户注册
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/sregister",method=RequestMethod.GET)
	public  String  register() throws IOException {
		return "sregister";
	}
	
	@RequestMapping(value = "/sregister",method = RequestMethod.POST)
	private String register(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) {
		if (sellerService.register(map)){
			return "slogin";
		} else {
			req.setAttribute("error","!!无效的用户或密码!!");
			return "sregister";
		}
	}
	
	/**
	 * 用户登录
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/slogin",method=RequestMethod.GET)
	public  String  login() throws IOException {
		return "slogin";
	}
	@RequestMapping(value="/slogin",method=RequestMethod.POST)
	public String login(HttpServletRequest req, HttpServletResponse resp,Seller seller){
		seller=sellerService.login(seller);		
		log.debug(seller);
		if(seller!=null){
			req.getSession().setAttribute("selllogin",seller.getSellid());
			return "redirect:/getmenu";
		}else{
			req.getSession().setAttribute("selllogin",null);
			req.setAttribute("error","!!无效的用户或密码!!");
			return "slogin";
		}
	}
	
	/**
	 * 用户注销
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws JasperException
	 */
	@RequestMapping(value = "/logout")
	public  void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, JasperException {
		req.getSession().setAttribute("selllogin",null);
		resp.sendRedirect("seller/slogin");
	}
	

	
	@RequestMapping(value="/gettype")//获取属性（typename）
	public @ResponseBody  List gettype(@RequestParam Map map) throws IOException {
		List lst=sellerService.getTypes(map);
		return lst;	
	}
	
	

	

//	@RequestMapping(value="update",method=RequestMethod.POST)
//	private String  update(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) throws ServletException, IOException {
//		PrintWriter out=resp.getWriter();
//		
//		if(sellerService.updateGsyb(map)) {
//				return "redirect:/gsyb";
//		} else {
//				return "update";
//		}	
//	}
//	@RequestMapping(value="update/{gsya}",method=RequestMethod.GET)//传参的时候，需要@PathVariable int gsya
//	private  ModelAndView update(@PathVariable int gsya) throws ServletException, IOException {
//		Gsyb gsyb=sellerService.getGsyb(gsya);
//		if (gsyb!=null) {
//			return new ModelAndView("modifyGsy", "gsyb", gsyb);	
//		} else {
//			return new ModelAndView("showByjava");
//		}	
//	}

//	@RequestMapping(value="/exit",method=RequestMethod.POST)
//	public String exit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		req.getSession().setAttribute("isLogin", null);
//    	System.out.println("success");
//    	return "login"; 
//	}
	
}
