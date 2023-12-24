package com.formationspring.app.ws.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@NotBlank(message = "the field must not be null !!!")
	@Size(min = 3,message = "the field must have at least 3 characters !! ")
	private String firstName;
	
	@NotBlank(message = "the field must not be null !!!")
	@Size(min = 3,message = "the field must have at least 3 characters !! ")
	private String lastName;

	@Email(message = "the field must respect the email format !!")
	@NotBlank(message = "the field must not be null !!!")
	private String email;
	
	@NotBlank(message = "the field must not be null !!!")
	@Size(min = 8,max = 12,message = "the field must have at least between 8 and 12 characters !! ")
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
	message = "this password must have upper and lower case letters and numbers !! ")
	private String password;
	
	private Boolean admin;
	
	private List<AddressRequest> addresses;
	
	private ContactRequest contact;
	
	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRequest(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	public List<AddressRequest> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressRequest> addresses) {
		this.addresses = addresses;
	}
	public ContactRequest getContact() {
		return contact;
	}
	public void setContact(ContactRequest contact) {
		this.contact = contact;
	}
	
	
	

}
