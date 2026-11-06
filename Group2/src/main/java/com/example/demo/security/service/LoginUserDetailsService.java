package com.example.demo.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.mapper.AuthenticationMapper;
import com.example.demo.security.LoginUser;
import com.example.demo.security.Role;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {
	
	private final AuthenticationMapper authenticationMapper;

	//与えられた引数をもとに、対象のUserDetailsクラスを返すメソッド
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		//Mapperで、引数の値に対応するユーザー情報をDBから取得して変数に代入
		Users user = authenticationMapper.selectUserByUserName(username);
		
		//ユーザー情報が存在した場合、User(UserDetailsクラスを)
		if(user != null) {
			return new LoginUser(user.getUser_id(),
								 user.getUser_name(),
								 user.getUser_password(),
								 getAuthorityList(user.getUser_role())
								 );
		}else {
			throw new UsernameNotFoundException(username + "は存在しません");
		}
	}
	
	
	//渡されたユーザーのroleに対応した権限情報を、Listで返すメソッド
	private List<GrantedAuthority> getAuthorityList(Role role){
		
		//権限情報を格納する空のリストを作成
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		//Usersエンティティが持つroleが何かを判定し、対応する権限をリストに格納
		if(role == Role.消費者) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}else if(role == Role.管理者) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else {
			throw new IllegalArgumentException("不正なRoleです: " + role);
		}
		
		return authorities;
	}
}

























