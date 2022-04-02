package com.api.parkingcontrol.data;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.parkingcontrol.model.User;

public class UserDetailData implements UserDetails{
	
	private Optional<User> user;
	
	public UserDetailData(Optional<User> user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//permissões do usuário
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		//user.orElse(parametro) -> se o valor de user estiver presente o retorna, senão retorna o valor do parâmetro
		return user.orElse(new User()).getPassword();
	}

	@Override
	public String getUsername() {
		return user.orElse(new User()).getCpf();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
