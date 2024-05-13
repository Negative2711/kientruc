package com.example.se.qlsv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.se.qlsv.entity.User;
import com.example.se.qlsv.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String mssv) throws UsernameNotFoundException {
		User user = userRepository.findByMssv(mssv).orElseThrow(() -> new UsernameNotFoundException("User not found: " + mssv));
		return user;
	}

}
