package com.example.demo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

//ユーザー認証情報を扱うクラス
public class LoginUser extends User{
	//UserDetailsインターフェースを簡易実装したUserクラスを継承
	
	//コンストラクタ
	public LoginUser (String username,
					  String password,
					  //ここは権限を渡している(内部的な処理は分からない)
					  Collection<? extends GrantedAuthority> authorities) {
		
		super(username,password,authorities);
	}
}
