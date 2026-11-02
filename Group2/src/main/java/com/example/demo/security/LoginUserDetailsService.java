package com.example.demo.security;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.mapper.AuthenticationMapper;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
	
	private final AuthenticationMapper authenticationMapper;

	//与えられた引数をもとに、対象のUserDetailsクラスを返すメソッド
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Users user = authenticationMapper.selectUserByUserName(username);
		
		
		if(user != null) {
			return new LoginUser(user.getUser_name(),
								 user.getUser_password(),
								 Collections.emptyList());
		}else {
			throw new UsernameNotFoundException(username + "は存在しません");
		}
	}
}
