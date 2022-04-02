package com.api.parkingcontrol.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.api.parkingcontrol.data.UserDetailData;
import com.api.parkingcontrol.model.User;
import com.api.parkingcontrol.repository.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByCpf(username);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("User [" +username+ "] not found.");
		}		
		
		return new UserDetailData(user);
	}

}
