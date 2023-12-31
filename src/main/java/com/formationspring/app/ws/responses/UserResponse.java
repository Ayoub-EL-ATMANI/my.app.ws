package com.formationspring.app.ws.responses;

import java.util.List;

public class UserResponse {
	
	// we can return object of UserRequest but in professional mode we can't 
	// display password and some informations for maintain of customer privacy
	
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean admin;
	private List<AddressResponse> addresses;
	private ContactResponse contact;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public List<AddressResponse> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressResponse> addresses) {
		this.addresses = addresses;
	}
	public ContactResponse getContact() {
		return contact;
	}
	public void setContact(ContactResponse contact) {
		this.contact = contact;
	}
	
	
	
	
	
}
