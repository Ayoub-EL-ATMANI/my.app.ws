package com.formationspring.app.ws.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.formationspring.app.ws.entities.UserEntity;
import com.formationspring.app.ws.repositories.UserRepository;
import com.formationspring.app.ws.services.UserService;
import com.formationspring.app.ws.shared.Utils;
import com.formationspring.app.ws.shared.dto.AddressDto;
import com.formationspring.app.ws.shared.dto.UserDto;

@Service

public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utile;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		UserEntity checkUser = userRepository.findByEmail(userDto.getEmail());
		if(checkUser != null) throw new RuntimeException("User already existe !!!!");
				

		
		for (int i = 0; i < userDto.getAddresses().size(); i++) {
			AddressDto address = userDto.getAddresses().get(i);
			address.setUser(userDto);
			address.setAddressId(utile.generateStringId(30));
			userDto.getAddresses().set(i, address);
		}
		userDto.getContact().setContactId(utile.generateStringId(30));
		userDto.getContact().setUser(userDto);
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setUserId(utile.generateStringId(32));
		
		UserEntity newUser = userRepository.save(userEntity);		
		UserDto userdtofin = modelMapper.map(newUser, UserDto.class);
		
		return userdtofin;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		
		return userDto;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		
		UserEntity userUpdated = userRepository.save(userEntity);
		BeanUtils.copyProperties(userUpdated, userDto);
		
		return userDto;
	}

	@Override
	public void deleteUser(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException(userId);
		userRepository.delete(userEntity);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit, String search, int status) {
		
		if(page > 0) page--;
		List<UserDto> usersDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> userPage;
		
		if(search.isEmpty()) {
			userPage = userRepository.findAllUsers(pageableRequest);
		}
		else {
			userPage = userRepository.findAllUserByCriteria(pageableRequest,search,status);
		}
		
		List<UserEntity> users = userPage.getContent();
		for (UserEntity userEntity : users) {
			
			ModelMapper modelMapper = new ModelMapper();
			UserDto userDto = modelMapper.map(userEntity,UserDto.class);
			usersDto.add(userDto);
		}
		
		return usersDto;
	}

}
