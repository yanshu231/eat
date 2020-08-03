package com.eat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eat.mapper.GetMenuMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED)//事务管理
public class GetMenuServiceImpl implements IGetMenuService {

	@Autowired
	GetMenuMapper getMenuMapper;

	@Override
	public List getMenu(Map map) {
		List lst=getMenuMapper.getAll(map);
		return lst;
	}	
}
