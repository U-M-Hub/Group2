package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterMapper {

	//会員登録画面で入力された情報を、ユーザー情報としてDBに登録するMapper
	void registerUser(@Param("userName") String userName,
					  @Param("userPass") String userPass);
	
	//渡されたユーザー名が、既に使用されているかどうか判定してbooleanで返すMapper
	boolean checkDuplicateUserName(@Param("userName") String userName);
}
