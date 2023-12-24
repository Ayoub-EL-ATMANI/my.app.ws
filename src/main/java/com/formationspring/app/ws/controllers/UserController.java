package com.formationspring.app.ws.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.formationspring.app.ws.exceptions.UserException;
import com.formationspring.app.ws.requests.UserRequest;
import com.formationspring.app.ws.responses.ErrorMessages;
import com.formationspring.app.ws.responses.UserResponse;
import com.formationspring.app.ws.services.UserService;
import com.formationspring.app.ws.shared.dto.UserDto;

@RestController    // send and receive request http & https 
@RequestMapping("/users") // https://localhost:8090/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getname")
	public String myName() {
		return "Hi Ayoub EL ATMANI";
	}
	
	@GetMapping(path = "/{id}" , produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> getUser(@PathVariable String id) { // same name of the path id = id
		
		UserDto userDto = userService.getUserByUserId(id);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(userDto, userResponse);
		
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<UserResponse> getAllUsers(@RequestParam(value="page" , defaultValue = "1")int page,@RequestParam(value="limit", defaultValue = "15")int limit,
			@RequestParam(value="search", defaultValue = "")String search,
			@RequestParam(value="status", defaultValue = "1")int status){
		
		List<UserResponse> usersResponse = new ArrayList<>();
		List<UserDto> users = userService.getUsers(page,limit,search,status);
		
		for (UserDto userDto : users) {
			ModelMapper modelMapper = new ModelMapper();
			UserResponse userRespone = modelMapper.map(userDto,UserResponse.class);
			usersResponse.add(userRespone);
		}
		
		return usersResponse;
	}
	
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
				 produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public  ResponseEntity<UserResponse> creatUser( @RequestBody @Valid UserRequest userRequest){// @RequestBody we are receive date with concept of deserialization from outside (object json ex)   
		
		if(userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessages());
		
		// presentation layer
//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userRequest, userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		
		// services layer
		UserDto createUser = userService.createUser(userDto);
		//UserResponse userResponse = new UserResponse();
		//BeanUtils.copyProperties(createUser, userResponse);
		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}
	@PutMapping(path = "/{id}",
			 	consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			 	produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {
		
		// presentation layer
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		// services layer
		UserDto updateUser = userService.updateUser(id , userDto);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);
		
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.ACCEPTED);
	}
	
	
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deletetUser(@PathVariable String id) {
		
		userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
	}
}
