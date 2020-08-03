package com.eat.service;



import java.util.List;
import java.util.Map;





public interface ICartService {
    boolean insert(Map map);
    boolean delete(Map map);
    List getCart(Map map);

    
    
}
