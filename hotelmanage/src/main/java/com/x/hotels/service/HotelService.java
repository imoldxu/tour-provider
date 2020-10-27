package com.x.hotels.service;

import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.x.hotels.context.bo.HotelBo;
import com.x.hotels.context.bo.HotelQuery;
import com.x.hotels.entity.Hotel;
import com.x.tools.mongo.pageHelper.MongoPageHelper;
import com.x.tools.mongo.pageHelper.PageResult;

import ma.glasnost.orika.MapperFacade;

@Service
public class HotelService {

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoPageHelper mongoPageHelper;
	@Autowired
	MapperFacade orikaMapper;
	
	public Hotel add(HotelBo hotelBo) {
		Hotel hotel = orikaMapper.map(hotelBo, Hotel.class);
		mongoTemplate.save(hotel);
		return hotel;
	}
	
	public List<Hotel> match(HotelQuery hotelQuery){
		Query query = new Query();
		Hotel exhotel = orikaMapper.map(hotelQuery, Hotel.class);
		CriteriaDefinition criteriaDefinition = Criteria.byExample(exhotel);
		query.addCriteria(criteriaDefinition);
		List<Hotel> hotels = mongoTemplate.find(query, Hotel.class);
		return hotels;
	}
	
	
	public List<Hotel> findAllMatched(HotelQuery hotelQuery){
		Query query = buildHotelQuery(hotelQuery);
		List<Hotel> hotels = mongoTemplate.find(query, Hotel.class);
		return hotels;
	}
	
	public PageResult<Hotel> find(HotelQuery hotelQuery){
		Query query = buildHotelQuery(hotelQuery);
		
		int pageNum = hotelQuery.getPageNum();
		int pageSize = hotelQuery.getPageSize();
		String lastId = hotelQuery.getLastId();
		
		PageResult<Hotel> hotels;
		if(StringUtils.isNotBlank(lastId)) {
			hotels = mongoPageHelper.pageQuery(query, Hotel.class, Function.identity(), pageSize, pageNum, lastId);
		}else {
			hotels = mongoPageHelper.pageQuery(query, Hotel.class, pageSize, pageNum);
		}
		return hotels;
	}

	private Query buildHotelQuery(HotelQuery hotelQuery) {
		Query query = new Query();
		String name = hotelQuery.getName();
		if (StringUtils.isNotBlank(name)){
			query.addCriteria( Criteria.where("name").regex(name));
		}
		String address = hotelQuery.getAddress();
		if (StringUtils.isNoneBlank(address)) {
			query.addCriteria(Criteria.where("address").regex(address));
		}
		String city = hotelQuery.getCity();
		if(StringUtils.isNoneBlank(city)) {
			query.addCriteria(Criteria.where("city").is(city));
		}
		String xclv = hotelQuery.getXclv();
		if(StringUtils.isNoneBlank(xclv)) {
			query.addCriteria(Criteria.where("xclv").is(xclv));
		}
		
		String lv = hotelQuery.getLv();
		if(StringUtils.isNoneBlank(lv)) {
			query.addCriteria(Criteria.where("lv").is(lv));
		}
		
		List<String> area = hotelQuery.getArea();
		if(null != area && !area.isEmpty()) {
			query.addCriteria(Criteria.where("area").all(area));
		}
		
		Boolean canForegin = hotelQuery.getCanForeign();
		if(canForegin != null) {
			query.addCriteria(Criteria.where("canForeign").is(canForegin));
		}
		return query;
	}

	public Hotel findOne(Hotel hotel) {
		Query query =new Query(Criteria.byExample(hotel));
		Hotel ret = mongoTemplate.findOne(query, Hotel.class);
		return ret;
	}
}
