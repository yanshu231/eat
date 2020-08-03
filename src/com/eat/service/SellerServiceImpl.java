package com.eat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.eat.mapper.SellerMapper;
import com.eat.model.Addr;
import com.eat.model.Member;
import com.eat.model.Seller;

import jdk.nashorn.internal.ir.annotations.Immutable;
@Service
@Transactional(propagation = Propagation.REQUIRED)//事务管理
public class SellerServiceImpl implements ISellerService {

	@Autowired
	SellerMapper sellerMapper;

	@Override
	public Seller login(Seller seller) {
		return (Seller) sellerMapper.login(seller);
	}

	@Override
	public boolean register(Map map) {
		return sellerMapper.register(map)>0;
	}

	@Override
	public boolean check(String name) {
		if(sellerMapper.check(name)==null) { 
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean delete(String sn) {
		return sellerMapper.delete(sn)>0;
	}

	@Override
	public List getSellers(Map map) {
		return  sellerMapper.getAll(map);
	}

	@Override
	public boolean updateSeller(Map map) {
		return sellerMapper.update(map)>0;
	}

	@Override
	public Seller getSeller(int sellid) {
		return null;
	}

	@Override
	public List getTypes(Map map) {
		return sellerMapper.getTypes(map);
	}

		
}
