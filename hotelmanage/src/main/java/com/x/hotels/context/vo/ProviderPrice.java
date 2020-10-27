package com.x.hotels.context.vo;

import com.x.hotels.entity.Provider;

public class ProviderPrice {

	//供应商
	private Provider provider;
	
	//团队价格
	private Integer teamPrice;
	
	//散客价格
	private Integer scatteredPrice;
	
	//是否含早
	private Boolean haveBreakfast;
	
	//加早餐价格
	private Integer breakfastPrice;
	
	//是否加床
	private Boolean canAddBed;
	
	//加床价格
	private Integer addBedPrice;

	//
	private String remarks;
	
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Integer getTeamPrice() {
		return teamPrice;
	}

	public void setTeamPrice(Integer teamPrice) {
		this.teamPrice = teamPrice;
	}

	public Integer getScatteredPrice() {
		return scatteredPrice;
	}

	public void setScatteredPrice(Integer scatteredPrice) {
		this.scatteredPrice = scatteredPrice;
	}

	public boolean isHaveBreakfast() {
		return haveBreakfast;
	}

	public void setHaveBreakfast(boolean haveBreakfast) {
		this.haveBreakfast = haveBreakfast;
	}

	public Integer getAddBedPrice() {
		return addBedPrice;
	}

	public void setAddBedPrice(Integer addBedPrice) {
		this.addBedPrice = addBedPrice;
	}

	public Boolean getHaveBreakfast() {
		return haveBreakfast;
	}

	public void setHaveBreakfast(Boolean haveBreakfast) {
		this.haveBreakfast = haveBreakfast;
	}

	public Integer getBreakfastPrice() {
		return breakfastPrice;
	}

	public void setBreakfastPrice(Integer breakfastPrice) {
		this.breakfastPrice = breakfastPrice;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getCanAddBed() {
		return canAddBed;
	}

	public void setCanAddBed(Boolean canAddBed) {
		this.canAddBed = canAddBed;
	}
	
	
}
