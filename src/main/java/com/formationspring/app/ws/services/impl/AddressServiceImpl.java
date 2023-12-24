package com.formationspring.app.ws.services.impl;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formationspring.app.ws.entities.AddressEntity;
import com.formationspring.app.ws.entities.UserEntity;
import com.formationspring.app.ws.repositories.AddressRepository;
import com.formationspring.app.ws.repositories.UserRepository;
import com.formationspring.app.ws.services.AddressService;
import com.formationspring.app.ws.shared.Utils;
import com.formationspring.app.ws.shared.dto.AddressDto;
import com.formationspring.app.ws.shared.dto.UserDto;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	Utils utile;
	
	@Override
	public List<AddressDto> getAllAddresses(String email) {
		
		UserEntity currentUser = userRepository.findByEmail(email);
		List<AddressEntity> addresses = currentUser.getAdmin() == true ? addressRepository.findAll() : addressRepository.findByUser(currentUser);
		Type listType = new TypeToken<List<AddressDto>>() {}.getType();
		List<AddressDto> addressesDto = new ModelMapper().map(addresses, listType);
		
		return addressesDto;
	}

	@Override
	public AddressDto createAddress(AddressDto address, String email) {

		UserEntity currentUser = userRepository.findByEmail(email);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(currentUser, UserDto.class);
		
		address.setAddressId(utile.generateStringId(30));
		address.setUser(userDto);
		
		AddressEntity addressEntity = modelMapper.map(address, AddressEntity.class);
		AddressEntity newAddress = addressRepository.save(addressEntity);
		AddressDto addressDto = modelMapper.map(newAddress, AddressDto.class);
		
		return addressDto;
	}

	@Override
	public AddressDto getAddress(String addressId) {
		
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		AddressDto addressDto = new ModelMapper().map(addressEntity, AddressDto.class);
		
		return addressDto;
	}

	@Override
	public void deleteAddress(String addressId) {
		
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		if(addressEntity == null) throw new RuntimeException("Address not found !!! ");
		addressRepository.delete(addressEntity);
	}

	@Override
	public AddressDto updateAddress(String id, AddressDto addressDto) {
		AddressEntity addressEntity = addressRepository.findByAddressId(id);
		if(addressEntity == null) throw new RuntimeException("Address not found !!");
		
		addressEntity.setPostal(addressDto.getPostal());
		addressEntity.setType(addressDto.getType());
		addressEntity.setCity(addressDto.getCity());
		addressEntity.setStreet(addressDto.getStreet());
		addressEntity.setCountry(addressDto.getCountry());
		
		AddressEntity updateAddres = addressRepository.save(addressEntity);
		AddressDto lastAddress = new ModelMapper().map(updateAddres, AddressDto.class);
		return lastAddress;
	}

}
