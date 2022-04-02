package com.api.parkingcontrol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.parkingcontrol.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByCpf(String cpf);

}
