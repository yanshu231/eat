package com.eat.model;
//
public class Menu {
	int id;


	String name;
	float price;
	String remark;
	String pic;
	int sellid;
	int cid;
	 //id | name | price | remark | pic   | sellid | cid
	public Menu(String name, float price, String remark, String pic) {
		super();
		this.name = name;
		this.price = price;
		this.remark = remark;
		this.pic = pic;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSellid() {
		return sellid;
	}

	public void setSellid(int sellid) {
		this.sellid = sellid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public Menu() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getSpNo() {
		return null;
	}

	
}
