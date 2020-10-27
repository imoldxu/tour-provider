package com.x.hotels.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.x.hotels.context.bo.ProviderBo;
import com.x.hotels.entity.Provider;

import ma.glasnost.orika.MapperFacade;

@Service
public class ProviderService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	MapperFacade orikaMapper;
	
	public Provider add(ProviderBo providerBo) {
		Provider provider = orikaMapper.map(providerBo, Provider.class);
		mongoTemplate.save(provider);
		return provider;
	}

	public List<Provider> findByName(String name) {
		
		Query query = new Query();
		if (StringUtils.isNotBlank(name)){
			query.addCriteria(Criteria.where("name").regex(name));
		}
		List<Provider> providerList = mongoTemplate.find(query, Provider.class);
		return providerList;
	}
	
}
