package com.x.hotels.context.bo;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HotelBo {

	@NotBlank(message="请输入酒店名称")
	private String name;
	
	//城市
	@NotBlank(message="请选择城市")
	private String city;
	
	//地址
	@NotBlank(message="请输入酒店地址")
	private String address;
	
	//商圈\景区\区域
	private List<String> area;

	//携程等级
	@NotBlank(message="请选择携程等级")
	private String xclv;
	
	//标准等级
	@NotBlank(message="请选择标准等级")
	private String lv;
	
	//是否涉外
	@NotNull(message="请选择是否涉外")
	private Boolean canForeign;

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

	public Boolean getCanForeign() {
		return canForeign;
	}

	public void setCanForeign(Boolean canForeign) {
		this.canForeign = canForeign;
	}
	
}
