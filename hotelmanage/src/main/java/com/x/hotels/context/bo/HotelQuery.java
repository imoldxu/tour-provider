package com.x.hotels.context.bo;

import java.util.List;

public class HotelQuery {

	private String name;
	
	//城市
	private String city;
	
	//地址
	private String address;
	
	//商圈\景区\区域
	private List<String> area;

	//携程等级
	private String xclv;
	
	//标准等级
	private String lv;
	
	//是否涉外
	private Boolean canForeign;
	
	//每页的大小
	private int pageSize = 100;
	
	//上一页的页码
	private int pageNum = 1;
	
	//上一页的最后一个数据的ID
	private String lastId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getArea() {
		return area;
	}

	public void setArea(List<String> area) {
		this.area = area;
	}

	public String getXclv() {
		return xclv;
	}

	public void setXclv(String xclv) {
		this.xclv = xclv;
	}

	public String getLv() {
		return lv;
	}

	public void setLv(String lv) {
		this.lv = lv;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getLastId() {
		return lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}

	public Boolean getCanForeign() {
		return canForeign;
	}

	public void setCanForeign(Boolean canForeign) {
		this.canForeign = canForeign;
	}

}
