package com.eat.mapper;

import java.util.List;
import java.util.Map;


public interface CartMapper {
	int insert(Object obj);
	int delete(Map map);	
	List getCart(Map map);

}
