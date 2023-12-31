package com.formationspring.app.ws.shared.dto;

public class AddressDto {
	
	private long id;
	private String addressId;
	private String city;
	private String country;
	private String street;
	private String postal;
	private String type;
	private UserDto user;
	
	
	
	
	public String getCity() {
		return city;
	}
	public long getId() {
		return id;
	}
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostal_code() {
		return postal;
	}
	public void setPostal_code(String postal) {
		this.postal = postal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	

}
