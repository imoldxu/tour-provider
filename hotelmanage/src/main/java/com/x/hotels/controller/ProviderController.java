package com.x.hotels.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.x.hotels.context.bo.ProviderBo;
import com.x.hotels.entity.Provider;
import com.x.hotels.service.ProviderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/provider")
@Api("供应商接口")
public class ProviderController {

	@Autowired
	ProviderService providerService;

	@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "注册", notes = "注册")
	public Provider add(@ApiParam(name = "providerBo", value = "供应商") @Valid @RequestBody ProviderBo providerBo,
			HttpServletRequest request, HttpServletResponse response) {

		Provider provider = providerService.add(providerBo);

		return provider;

	}
	
	@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "查找供应商", notes = "查找供应商")
	public List<Provider> findbyName(@ApiParam(name = "name", value = "供应商名称") @RequestParam(name="name", required=false) String name,
			HttpServletRequest request, HttpServletResponse response) {

		List<Provider> providerList = providerService.findByName(name);

		return providerList;
	}

}
