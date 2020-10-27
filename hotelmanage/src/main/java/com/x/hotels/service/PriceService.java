package com.x.hotels.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.x.hotels.context.bo.HotelQuery;
import com.x.hotels.context.bo.PriceBo;
import com.x.hotels.context.bo.PriceQuery;
import com.x.hotels.context.vo.HotelPrices;
import com.x.hotels.context.vo.ProviderPrice;
import com.x.hotels.entity.Hotel;
import com.x.hotels.entity.Price;
import com.x.hotels.entity.Provider;
import com.x.tools.mongo.pageHelper.MongoPageHelper;
import com.x.tools.util.DateUtils;

import ma.glasnost.orika.MapperFacade;

@Service
public class PriceService {

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoPageHelper mongoPageHelper;
	@Autowired
	MapperFacade orikaMapper;
	@Autowired
	HotelService hotelService;

	
	
	public Price add(PriceBo priceBo) {
		Price price = orikaMapper.map(priceBo, Price.class);
		
		Date startDate = price.getStartDate();
		Date endDate = price.getEndDate();
		
		//TODO:此处应该开启事务
		
		//查找是否有与开始时间重合的价格区间,改进前置区间的结束时间
		Price prePrice = findMatchedPrice(price.getHotel(), price.getProvider(), startDate);
		if(prePrice!=null) {
			prePrice.setEndDate(DateUtils.reduceDays(startDate,1));
			mongoTemplate.save(prePrice);
		}
		
		//查找是否有与结束时间重合的价格区间,改进后置区间的开始时间
		Price lastPrice = findMatchedPrice(price.getHotel(), price.getProvider(), endDate);
		if(lastPrice!=null) {
			lastPrice.setStartDate(DateUtils.addDays(endDate,1));
			mongoTemplate.save(lastPrice);
		}
		
		//查找是否有与开始、结束时间包含在内的价格区间，全部移除
		List<Price> priceList = findIncludedPrice(price.getHotel(), price.getProvider(), startDate, endDate);
		priceList.forEach(p->{
			mongoTemplate.remove(p);
		});
		
		//插入新的价格区间
		mongoTemplate.save(price);
		
		
		return price;
	}

//	private List<Price> findIncludedPrice(Date startDate, Date endDate) {
//		Query query = new Query();
//		Criteria criteria = Criteria.where("startDate").gte(startDate).and("endDate").lte(endDate);
//		query.addCriteria(criteria);
//		List<Price> priceList = mongoTemplate.find(query, Price.class);
//		return priceList;
//	}
//	
//	private Price findMatchedPrice(Date date) {
//		Query query = new Query();
//		Criteria criteria = Criteria.where("startDate").lte(date).and("endDate").gte(date);
//		query.addCriteria(criteria);
//		Price prePrice = mongoTemplate.findOne(query, Price.class);
//		return prePrice;
//	}
	
	private List<Price> findIncludedPrice(Hotel hotel, Provider provider, Date startDate, Date endDate) {
		Query query = new Query();
		query.addCriteria(Criteria.where("startDate").gte(startDate).and("endDate").lte(endDate));
		query.addCriteria(Criteria.where("hotel").is(hotel));
		query.addCriteria(Criteria.where("provider").is(provider));
		List<Price> priceList = mongoTemplate.find(query, Price.class);
		return priceList;
	}
	
	private Price findMatchedPrice(Hotel hotel, Provider provider, Date date) {
		Query query = new Query();
		query.addCriteria(Criteria.where("startDate").lte(date).and("endDate").gte(date));
		query.addCriteria(Criteria.where("hotel").is(hotel));
		query.addCriteria(Criteria.where("provider").is(provider));
		Price prePrice = mongoTemplate.findOne(query, Price.class);
		return prePrice;
	}
	
//	private List<Price> searchIncludedPrice(HotelQuery hotelQuery, Date startDate, Date endDate) {
//		List<Hotel> hotels = hotelService.findAllMatched(hotelQuery);
//		
//		Aggregation agg = Aggregation.newAggregation(
//				
//				);
//		
//		mongoTemplate.aggregate(agg, "price", outputType)
//	}
	
	private List<Price> searchMatchedPrice(Hotel hotel, Provider provider, Date date) {
		Query query = new Query();
		query.addCriteria(Criteria.where("startDate").lte(date).and("endDate").gte(date));
		query.addCriteria(Criteria.where("hotel").is(hotel));
		query.addCriteria(Criteria.where("provider").is(provider));
		List<Price> priceList = mongoTemplate.find(query, Price.class);
		return priceList;
	}

	public List<HotelPrices> search(PriceQuery priceQuery) {
		HotelQuery hotelQuery = orikaMapper.map(priceQuery, HotelQuery.class);
		if (null == hotelQuery) {
			hotelQuery = new HotelQuery();
		}
		List<Hotel> hotels = hotelService.findAllMatched(hotelQuery);
		Date date = priceQuery.getDate();
		
		List<HotelPrices> hotelPricesList = new ArrayList<HotelPrices>();
		
		hotels.forEach(hotel->{
			HotelPrices hp = getDayPriceByHotel(hotel, date);
			if(hp!=null) {
				hotelPricesList.add(hp);
			}
		});
		
		
		return hotelPricesList;
	}

	private HotelPrices getDayPriceByHotel(Hotel hotel, Date date) {
		Query query = new Query();
		query.addCriteria(Criteria.where("hotel").is(hotel));
		query.addCriteria(Criteria.where("startDate").lte(date).and("endDate").gte(date));
		List<Price> priceList = mongoTemplate.find(query, Price.class);
		
		if(!priceList.isEmpty()) {
			HotelPrices hotelPrices = new HotelPrices();
			hotelPrices.setHotel(hotel);
			List<ProviderPrice> pricesList = new ArrayList();
			priceList.forEach(p->{
//				ProviderPrice pp = new ProviderPrice();
				ProviderPrice pp = orikaMapper.map(p, ProviderPrice.class);
				//pp.setProvider(p.getProvider());
				pricesList.add(pp);
			});
			hotelPrices.setPricesList(pricesList);
			hotelPrices.setDay(date);
			return hotelPrices;
		}
		return null;
	}

	public List<PriceBo> uploadByExcel(List<PriceBo> list) {
		List<PriceBo> failed = new ArrayList<PriceBo>();
		
		list.forEach(pricebo->{
			Hotel hotel = hotelService.findOne(pricebo.getHotel());
			if(hotel == null) {
				failed.add(pricebo);
			}else {
				pricebo.setHotel(hotel);
				add(pricebo);
			}
		});
		return failed;
	}
	
//	public List<PriceVo> search(PriceQuery priceQuery) {
//		
//		Date startDate = priceQuery.getStartDate();
//		Date endDate = priceQuery.getEndDate();
//		
//		Hotel hotel = priceQuery.getHotel();
//		Provider provider = priceQuery.getProvider();
//		
//		Price prePrice = searchMatchedPrice(hotel, provider, startDate);
//		List<Price> includePrice = searchIncludedPrice(hotel, provider, startDate, endDate);
//		Price lastPrice = searchMatchedPrice(hotel, provider, endDate);
//		
//		List<Price> allPrice = new ArrayList<Price>();
//		allPrice.add(prePrice);
//		allPrice.addAll(includePrice);
//		allPrice.add(lastPrice);
//		
//		List<PriceVo> priceVos = new ArrayList<PriceVo>();
//		Date currentDate = startDate;
//		allPrice.forEach(p ->{
//			Date sd = p.getStartDate();
//			Date ed = p.getEndDate();
//			
//			while(currentDate.after(ed)) {
//				if(currentDate.after(sd) && currentDate.before(ed)) {
//					PriceVo priceVo =
//				}
//						
//				priceVos.add(priceVo);
//				
//				currentDate = DateUtils.addDays(currentDate, 1);
//			}
//			
//		});
//		
//		
//		return ret;
//	}
	
}
