package com.eat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eat.mapper.CartMapper;

@Service
@Transactional(propagation = Propagation.REQUIRED)//事务管理
public class CartServiceImpl implements ICartService {

	@Autowired
	CartMapper cartMapper;

	@Override
	public boolean insert(Map map) {
		return cartMapper.insert(map)>0;
	}

	@Override
	public boolean delete(Map map) {
		return cartMapper.delete(map)>0;
	}

	@Override
	public List getCart(Map map) {	
		return cartMapper.getCart(map);
	}



	
}
