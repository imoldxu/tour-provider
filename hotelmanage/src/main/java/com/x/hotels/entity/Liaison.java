package com.x.hotels.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 联系人
 * @author 老徐
 *
 */
@Valid
public class Liaison {

	@NotBlank(message="请输入联系人姓名")
	private String name;
	
	@Size(min=11, max=11, message="请输入正确的手机号")
	@Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message="请输入正确的手机号")
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
		
}
