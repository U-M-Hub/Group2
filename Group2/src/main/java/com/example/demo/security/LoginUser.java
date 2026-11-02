package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

//ユーザー認証情報を扱うクラス
//UserDetailsインターフェースを簡易実装したUserクラスを継承
public class LoginUser extends User{
	
	private final Integer userId;
	
	//コンストラクタ
	public LoginUser (Integer userId,
					  String username,
					  String password,
					  //ここは権限を渡している(内部的な処理は分からない)
					  Collection<? extends GrantedAuthority> authorities) {
		
		super(username,password,authorities);
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
}
