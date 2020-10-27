package com.x.hotels.context.bo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.x.hotels.entity.Liaison;

public class ProviderBo {

	@NotBlank
	private String name;//姓名
	
	@Valid
	private List<Liaison> contact;

	public List<Liaison> getContact() {
		return contact;
	}

	public void setContact(List<Liaison> contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
