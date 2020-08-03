package com.eat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.eat.model.Member;

public interface MemberMapper  {
	int register(Map map);
	Integer check(String name);
	Object login(Object obj);
	int update(Map map);
	int delete(@Param("ids") String ids);
	List getAll(Map map);
	Member getObject(Object obj);

}
