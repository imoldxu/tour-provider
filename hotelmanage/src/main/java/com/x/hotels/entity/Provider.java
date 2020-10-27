package com.x.hotels.entity;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="provider")
public class Provider {
	
	@Id
	public String id;
	
	@NotBlank
	private String name;//姓名
	
	@Valid
	private List<Liaison> contact;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Liaison> getContact() {
		return contact;
	}

	public void setContact(List<Liaison> contact) {
		this.contact = contact;
	}

}
