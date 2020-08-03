package com.eat.model;

public class Seller {
//| sellid | name     | pass   | tel  | addr
	int sellid;
	String name;
	String pass;
	int tel;
	int addr;
	public int getSellid() {
		return sellid;
	}
	public void setSellid(int sellid) {
		this.sellid = sellid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public int getAddr() {
		return addr;
	}
	public void setAddr(int addr) {
		this.addr = addr;
	}
	
}
