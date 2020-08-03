package com.eat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.apache.jasper.JasperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eat.model.Member;
import com.eat.service.IMemberService;



@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	IMemberService memberService;	
	
	Logger log=Logger.getLogger(MemberController.class);
	
	/**
	 * 检查用户注册名重复
	 * @param req
	 * @param resp
	 * @param memid
	 * @throws IOException
	 */
	@RequestMapping("/check/{memid}")
	public void check(HttpServletRequest req, HttpServletResponse resp,@PathVariable String memid) throws IOException {
		PrintWriter out=resp.getWriter();
		if(memberService.check(memid)){
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
	@RequestMapping(value = "/delete/{memid}",method = RequestMethod.GET)
	private String delete(HttpServletRequest req, HttpServletResponse resp,@PathVariable String name) throws IOException {
		if(memberService.delete(name)){
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
		String islogin=(String.valueOf(req.getSession().getAttribute("islogin")));//取得islogin对象的值
		log.debug(islogin);
		if(islogin==null) {
			islogin="htc_null";
		}
		out.print(islogin);
	}
	
	/**
	 * 用户注册
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public  String  register() throws IOException {
		return "register";
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	private String register(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) {
		if (memberService.register(map)){
			return "login";
		} else {
			req.setAttribute("error","!!无效的用户或密码!!");
			return "register";
		}
	}
	
	
	/**
	 * 用户登录
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public  String  login() throws IOException {
		return "login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest req, HttpServletResponse resp,Member member){
		member=memberService.login(member);		
		log.debug(member);
		if(member!=null){
			req.getSession().setAttribute("islogin",member.getMemid());
			return "redirect:/getmenu";
		}else{
			req.getSession().setAttribute("islogin",null);
			req.setAttribute("error","!!无效的用户或密码!!");
			return "login";
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
		req.getSession().setAttribute("islogin",null);
		resp.sendRedirect("member/login");
	}
	
	/**
	 * 用户更新信息
	 * @param uid
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="update/{uid}",method=RequestMethod.GET)
	private  ModelAndView update(@PathVariable int uid) throws ServletException, IOException {
		Member member=memberService.getUser(uid);
		if (member!=null) {
			return new ModelAndView("modifyUser", "member",member);	
		} else {
			return new ModelAndView("showUser");
		}	
	}
	
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	private String  update(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map) throws ServletException, IOException {		
		if(memberService.updateUser(map)) {
				return "redirect:/member/show";
		} else {
				return "update";
		}	
	}

	/**
	 * 用户信息展示
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="show",method=RequestMethod.GET)
	private  ModelAndView show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member1=(Member) req.getSession().getAttribute("isLogin");		
		int uid=member1.getMemid();
		System.err.println(uid);
		Member member2=memberService.getUser(uid);
		if (member2!=null) {
			return new ModelAndView("showUser", "member" , member2);	
		} else {
			return new ModelAndView("showUser");
		}	
	}
		
}