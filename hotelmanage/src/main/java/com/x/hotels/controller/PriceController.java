package com.x.hotels.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.x.hotels.context.bo.PriceBo;
import com.x.hotels.context.bo.PriceQuery;
import com.x.hotels.context.vo.HotelPrices;
import com.x.hotels.entity.Price;
import com.x.hotels.service.PriceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/price")
@Api("酒店价格接口")
@Validated
public class PriceController {

	@Autowired
	PriceService priceService;
	
	@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "添加价格", notes = "添加价格")
	public Price add(
			@ApiParam(name = "priceBo", value = "价格") @Validated @RequestBody PriceBo priceBo,
			HttpServletRequest request, HttpServletResponse response) {

		Price price = priceService.add(priceBo);
		
		return price;
	}
	
	@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "查询价格", notes = "查询价格")
	public List<HotelPrices> searchPrice(
			@ApiParam(name = "priceQuery", value = "酒店价格查询") @Valid PriceQuery priceQuery,
			HttpServletRequest request, HttpServletResponse response) {

		List<HotelPrices> priceList = priceService.search(priceQuery);
		
		return priceList;
	}
	
}
