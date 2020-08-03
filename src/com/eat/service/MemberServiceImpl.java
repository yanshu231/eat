package com.eat.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.mapper.MemberMapper;
import com.eat.model.Member;


@Service
public class MemberServiceImpl implements IMemberService{
	
	@Autowired
	MemberMapper  userMapper;
	Logger log=Logger.getLogger(MemberServiceImpl.class);
	
	/**
	 * 用户登录
	 */	 
	@Override
	public Member login(Member member) {
		return (Member) userMapper.login(member);
	}

	/**
	 * 增加用户
	 */
	@Override
	public boolean register(Map map) {
		return userMapper.register(map)>0;
	}

	/**
	 * 检查用户名重复
	 */
	@Override
	public boolean check(String name) {
		if(userMapper.check(name)==null) { 
			return false;
		}else {
			return true;
		}
		
	}

	/**
	 * 删除用户
	 */
	@Override
	public boolean delete(String name) {
		return userMapper.delete(name)>0;
	}

	/**
	 * 查询用户
	 */
	@Override
	public List getUsers(Map map) {
		return  userMapper.getAll(map);
	}

	/**
	 * 更新用户
	 */
	@Override
	public boolean updateUser(Map map) {
		return userMapper.update(map)>0;
	}

	/**
	 * 获取用户
	 */
	@Override
	public Member getUser(int uid) {
		
		return null;
	}
}
