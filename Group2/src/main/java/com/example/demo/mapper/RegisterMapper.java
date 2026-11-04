package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {

	//会員登録画面で入力された情報を、ユーザー情報としてDBに登録するMapper
	void registerUser(String userName,
					  String userPass);
}
