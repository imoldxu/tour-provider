package com.x.hotels.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="price")
public class Price {

	@Id
	private String id;
	
	//开始时间
	private Date startDate;
	
	//结束时间
	private Date endDate;
	
	@DBRef
	private Provider provider;
	
	@DBRef
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
	
	//加床价格
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
