package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Users;

@Mapper
public interface AuthenticationMapper {
	//ユーザー名で、ユーザー情報を取得
	Users selectUserByUserName(String userName);
}
