package com.eat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eat.model.Addr;
import com.eat.model.Seller;




public interface SellerMapper  {
	int register(Map map);
	Integer check(String name);
	Object login(Object obj);
	int update(Map map);
	int delete(@Param("ids") String ids);
	List getAll(Map map);
	Seller getObject(Object obj);	
	List  getTypes(Map map);
}
