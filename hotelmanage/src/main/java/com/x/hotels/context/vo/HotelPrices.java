package com.x.hotels.context.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.x.hotels.entity.Hotel;

public class HotelPrices {

	private Hotel hotel;
	
	private List<ProviderPrice> pricesList;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date day;

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<ProviderPrice> getPricesList() {
		return pricesList;
	}

	public void setPricesList(List<ProviderPrice> pricesList) {
		this.pricesList = pricesList;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}
	
}
