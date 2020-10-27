package com.x.hotels.context.bo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.x.hotels.entity.Hotel;
import com.x.hotels.entity.Provider;

public class PriceBo {

	//开始时间
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	//结束时间
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	//供应商
	private Provider provider;

	//酒店
	private Hotel hotel;
	
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
	
	//备注
	private String remarks;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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

	public Boolean getCanAddBed() {
		return canAddBed;
	}

	public void setCanAddBed(Boolean canAddBed) {
		this.canAddBed = canAddBed;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
	
}
