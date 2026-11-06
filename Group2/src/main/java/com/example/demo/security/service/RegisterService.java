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
	 * ユーザー名が重複していた場合はfalseを返す
	 */
	public boolean registerUser(RegisterForm registerForm) {
		
		//ユーザー名が重複しているかどうかチェック
		if(registerMapper.checkDuplicateUserName(registerForm.getUserName())) {
			return false;
		}
		
		//パスワードのハッシュ化
		String encodePass = passwordEncoder.encode(registerForm.getUserPass());
		
		//登録情報をもとに登録処理を行うとともに、同時登録などの重複判定も行う
		try {
			//登録処理を呼び出す
			registerMapper.registerUser(registerForm.getUserName(), encodePass);
			return true;
			
			//同時登録などで、重複チェックをすり抜けてINSERT文が実行された時の例外処理
		}catch(DuplicateKeyException e) {
			return false;
		}
		
	}
	
	//渡されたユーザー名を利用して重複判定用Mapperを呼び出し、結果をbooleande返す
	public boolean checkDuplicateUserName(String userName) {
		return registerMapper.checkDuplicateUserName(userName);
	}
}
