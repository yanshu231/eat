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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eat.service.ICartService;

@Controller
@RequestMapping("/cart")
public class CartController  {
	Logger log=Logger.getLogger(CartController.class);
	
	@Autowired
	ICartService cartService;
	
		
	
	@RequestMapping(value="/insert/{menuid}",method=RequestMethod.POST)
	private void insert(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map  map,@PathVariable int menuid) throws IOException, JasperException {
		PrintWriter out=resp.getWriter();
		String memid=(String.valueOf(req.getSession().getAttribute("islogin")));
		map.put("memid", memid);
		map.put("menuid", menuid);
		log.debug(map);	
		if (cartService.insert(map)) {
			out.print("success");
		} else {
			out.print("failure");

		}
		
	}
	
	@RequestMapping(value = "/delete/{menuid}",method = RequestMethod.GET)
	private String delete(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map,@PathVariable int menuid) throws IOException {
		String memid=(String.valueOf(req.getSession().getAttribute("islogin")));
		map.put("memid", memid);
		map.put("menuid", menuid);
		if(cartService.delete(map)){
			return "cart";	
		}else {
			return "cart";		
		}
	}
	
	@RequestMapping(value = "/sel",method=RequestMethod.GET)
	public  String ajaxsel(HttpServletRequest req) throws IOException {
		String memid=(String.valueOf(req.getSession().getAttribute("islogin")));
		System.err.println(memid);
		return "discart";
	}
	
	@RequestMapping(value = "/sel/{name}",method=RequestMethod.POST)
	public  @ResponseBody List ajaxsel(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map,@PathVariable String name) throws IOException, JasperException {
		String memid=(String.valueOf(req.getSession().getAttribute("islogin")));
		System.err.println(memid+1);
		System.err.println(memid);
		map.put("memid", memid);
		if(name.equals("abc")) {
			name=null;
		}
		map.put("name", name);
		List lst=cartService.getCart(map);
		log.debug(lst);
		return lst;	
	}		
}
