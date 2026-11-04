package com.example.demo.security.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.RegisterMapper;
import com.example.demo.security.form.RegisterForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {

	private final RegisterMapper registerMapper;
	private final PasswordEncoder passwordEncoder;
	
	/*
	 * 渡された登録情報をDB登録用のMapperへ渡すService。
	 * パスワードは、ハッシュ化してからMapperへ渡す。
	 */
	public boolean registerUser(RegisterForm registerForm) {
		
		String encodePass = passwordEncoder.encode(registerForm.getUserPass());
		
		try {
			
			registerMapper.registerUser(registerForm.getUserName(), encodePass);
			
			return true;
			
		}catch(DuplicateKeyException e) {
			
			return false;
		}
		
	}
	
	//渡されたユーザー名を利用して重複判定用Mapperを呼び出し、結果をbooleande返す
	public boolean checkDuplicateUserName(String userName) {
		return registerMapper.checkDuplicateUserName(userName);
	}
}
