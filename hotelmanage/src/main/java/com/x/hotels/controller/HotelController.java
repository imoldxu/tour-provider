package com.x.hotels.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.x.hotels.context.bo.HotelBo;
import com.x.hotels.context.bo.HotelQuery;
import com.x.hotels.entity.Hotel;
import com.x.hotels.service.HotelService;
import com.x.tools.mongo.pageHelper.PageResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/hotel")
@Api("酒店")
@Validated
public class HotelController {

	@Autowired
	HotelService hotelService;
	

	@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "提交酒店信息", notes = "提交酒店信息")
	public Hotel add(@ApiParam(name = "hotelBo", value = "酒店") @Validated @RequestBody HotelBo hotelBo,
			HttpServletRequest request, HttpServletResponse response) {
		
		Hotel hotel = hotelService.add(hotelBo);
		
		return hotel;
	}

	@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "查询酒店信息", notes = "查询酒店信息")
	public PageResult<Hotel> search(@ApiParam(name = "name", value = "酒店名称") @Valid HotelQuery hotelQuery,
//			@ApiParam(name = "name", value = "酒店名称") @RequestParam(name="name", required=false) String name,
//			@ApiParam(name = "city", value = "所在城市") @RequestParam(name="city", required=false) String city,
//			@ApiParam(name = "canForeign", value = "是否涉外") @RequestParam(name="canForeign", required=false) Boolean canForeign,
//			@ApiParam(name = "area", value = "区域") @RequestParam(name="area", required=false) String area,
//			@ApiParam(name = "lv", value = "标准等级") @RequestParam(name="lv", required=false) String lv,
//			@ApiParam(name = "xclv", value = "携程等级") @RequestParam(name="xclv", required=false) String xclv,
//			@ApiParam(name = "pageNum", value = "页码1-n") @RequestParam(name="pageNum", defaultValue="1") Integer pageNum,
//			@ApiParam(name = "pageSize", value = "每页数据") @RequestParam(name="pageSize", defaultValue="20") Integer pageSize,
//			@ApiParam(name = "lastId", value = "上一页的最后一个数据") @RequestParam(name="lastId", required=false) String lastId,
			HttpServletRequest request, HttpServletResponse response) {
		
//		HotelQuery hotelQuery = new HotelQuery();
//		hotelQuery.setName(name);
//		if (StringUtils.isNotBlank(area)) {
//			List<String> areas = new ArrayList<String>();
//			areas.add(area);
//			hotelQuery.setArea(areas);
//		}
//		hotelQuery.setCanForeign(canForeign);
//		hotelQuery.setCity(city);
//		hotelQuery.setLv(lv);
//		hotelQuery.setXclv(xclv);
//		hotelQuery.setPageNum(pageNum);
//		hotelQuery.setPageSize(pageSize);
//		hotelQuery.setLastId(lastId);
		
		
		PageResult<Hotel> hotels = hotelService.find(hotelQuery);
		return hotels;
	}
	
}
