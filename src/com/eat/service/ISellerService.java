package com.eat.service;



import java.util.List;
import java.util.Map;

import com.eat.model.Addr;
import com.eat.model.Member;
import com.eat.model.Seller;






public interface ISellerService {
	Seller login(Seller seller);	
	boolean register(Map map);
	boolean check(String name);
	
	boolean delete(String  sn);
    List getSellers(Map map);
    boolean updateSeller(Map map);
    Seller getSeller(int sellid);
    List getTypes(Map map);
}
