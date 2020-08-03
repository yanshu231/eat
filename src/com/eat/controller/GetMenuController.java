package com.eat.controller;


import java.io.IOException;
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



import com.eat.service.IGetMenuService;




@Controller
@RequestMapping("/getmenu")
public class GetMenuController  {
	Logger log=Logger.getLogger(GetMenuController.class);
	
	@Autowired
	IGetMenuService getMenuService;


	
	@RequestMapping(value = "/sel",method=RequestMethod.GET)
	public  String ajaxsel() throws IOException {
		return "dismenu";
	}
	
	
	@RequestMapping(value = "/sel/{name}",method=RequestMethod.POST)
	public  @ResponseBody List ajaxsel(HttpServletRequest req, HttpServletResponse resp,@RequestParam Map map,@PathVariable String name) throws IOException, JasperException {
		if(name.equals("abc")) {
			name=null;
		}
		map.put("name", name);
		List lst=getMenuService.getMenu(map);
		return lst;	
	}
}
