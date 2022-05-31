package com.grupo06.parcial2.models.dto;

import javax.validation.constraints.NotBlank;

public class BookingInfoDTO {
	@NotBlank
	private String username;
	@NotBlank
	private String code;
	public BookingInfoDTO(@NotBlank String username, @NotBlank String code) {
		super();
		this.username = username;
		this.code = code;
	}
	public BookingInfoDTO() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
