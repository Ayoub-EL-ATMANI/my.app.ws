package com.formationspring.app.ws.controllers;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formationspring.app.ws.requests.AddressRequest;
import com.formationspring.app.ws.responses.AddressResponse;
import com.formationspring.app.ws.services.AddressService;
import com.formationspring.app.ws.shared.dto.AddressDto;

@RestController
@RequestMapping("/addresses")
public class AdressController {
	
	@Autowired
	AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<AddressResponse>> getAddresses(Principal principal){
		
		List<AddressDto> addresssesDto = addressService.getAllAddresses(principal.getName());
		Type listType = new TypeToken<List<AddressResponse>>() {}.getType();
		List<AddressResponse> addressResponses = new ModelMapper().map(addresssesDto, listType);
		
		return new ResponseEntity<List<AddressResponse>>(addressResponses,HttpStatus.OK);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
				 produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AddressResponse> storeAddress(@RequestBody AddressRequest addressRequest, Principal principal){
		
		ModelMapper modelMapper = new ModelMapper();
		
		AddressDto addressDto = modelMapper.map(addressRequest, AddressDto.class);
		AddressDto createAddress = addressService.createAddress(addressDto,principal.getName());
		AddressResponse addressResponse = modelMapper.map(createAddress, AddressResponse.class);
		
		return new ResponseEntity<AddressResponse>(addressResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> getOneAddress(@PathVariable(name="id") String addressId){
		
		AddressDto addressDto = addressService.getAddress(addressId);
		AddressResponse addressResponse = new ModelMapper().map(addressDto, AddressResponse.class);
		
		return new ResponseEntity<AddressResponse>(addressResponse,HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}",
		 	consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
		 	produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AddressResponse> updateAddress(@PathVariable(name="id") String id,@RequestBody AddressRequest addressRequest ){
		
		AddressDto addressDto = new ModelMapper().map(addressRequest, AddressDto.class);
		AddressDto updateAddress = addressService.updateAddress(id,addressDto);
		AddressResponse addressResponse = new ModelMapper().map(updateAddress, AddressResponse.class);
		
		return new ResponseEntity<AddressResponse>(addressResponse,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletetUser(@PathVariable(name="id") String addressId) {		
			addressService.deleteAddress(addressId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

}
