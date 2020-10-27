//package com.x.hotels.context.vo;
//
//import java.util.Date;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.x.hotels.entity.Hotel;
//import com.x.hotels.entity.Provider;
//
//public class PriceVo {
//
//	//当前时间
//  @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
//	private Date date;
//	
//	private Provider provider;
//	
//	private Hotel hotel;
//	
//	//团队价格
//	private Integer teamPrice;
//	
//	//散客价格
//	private Integer scatteredPrice;
//	
//	//是否含早
//	private Boolean haveBreakfast;
//	
//	//加早餐价格
//	private Integer breakfastPrice;
//	
//	//是否加床
//	private Boolean canAddBed;
//	
//	//加床价格
//	private Integer addBedPrice;
//
//	//备注
//	private String remarks;
//	
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public Provider getProvider() {
//		return provider;
//	}
//
//	public void setProvider(Provider provider) {
//		this.provider = provider;
//	}
//
//	public Hotel getHotel() {
//		return hotel;
//	}
//
//	public void setHotel(Hotel hotel) {
//		this.hotel = hotel;
//	}
//
//	public Integer getTeamPrice() {
//		return teamPrice;
//	}
//
//	public void setTeamPrice(Integer teamPrice) {
//		this.teamPrice = teamPrice;
//	}
//
//	public Integer getScatteredPrice() {
//		return scatteredPrice;
//	}
//
//	public void setScatteredPrice(Integer scatteredPrice) {
//		this.scatteredPrice = scatteredPrice;
//	}
//
//	public Integer getAddBedPrice() {
//		return addBedPrice;
//	}
//
//	public void setAddBedPrice(Integer addBedPrice) {
//		this.addBedPrice = addBedPrice;
//	}
//
//	public Boolean getHaveBreakfast() {
//		return haveBreakfast;
//	}
//
//	public void setHaveBreakfast(Boolean haveBreakfast) {
//		this.haveBreakfast = haveBreakfast;
//	}
//
//	public Integer getBreakfastPrice() {
//		return breakfastPrice;
//	}
//
//	public void setBreakfastPrice(Integer breakfastPrice) {
//		this.breakfastPrice = breakfastPrice;
//	}
//
//	public Boolean getCanAddBed() {
//		return canAddBed;
//	}
//
//	public void setCanAddBed(Boolean canAddBed) {
//		this.canAddBed = canAddBed;
//	}
//
//	public String getRemarks() {
//		return remarks;
//	}
//
//	public void setRemarks(String remarks) {
//		this.remarks = remarks;
//	}
//	
//}
