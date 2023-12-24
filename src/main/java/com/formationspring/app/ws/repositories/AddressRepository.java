package com.formationspring.app.ws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formationspring.app.ws.entities.AddressEntity;
import com.formationspring.app.ws.entities.UserEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>{
	
	List<AddressEntity> findByUser(UserEntity currentUser);
	AddressEntity findByAddressId(String addressId);

}
