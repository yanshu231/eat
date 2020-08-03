package com.eat.service;

import java.util.List;
import java.util.Map;

import com.eat.model.Member;



public interface IMemberService{
	Member login(Member member);	
	boolean register(Map map);
	boolean check(String name);
	
	boolean delete(String  sn);
    List getUsers(Map map);
    boolean updateUser(Map map);
    Member getUser(int uid);
}