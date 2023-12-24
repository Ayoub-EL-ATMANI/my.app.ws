package com.formationspring.app.ws.services;

import java.util.List;

import com.formationspring.app.ws.shared.dto.AddressDto;

public interface AddressService {
	
	public List<AddressDto> getAllAddresses(String email);
	public AddressDto createAddress(AddressDto addressDto, String email);
	public AddressDto getAddress(String addressId);
	public void deleteAddress(String addressId);
	public AddressDto updateAddress(String id,AddressDto addressDto);
	
}
