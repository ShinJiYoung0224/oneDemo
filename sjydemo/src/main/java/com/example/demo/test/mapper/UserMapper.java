package com.example.demo.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.test.model.ImgModel;
import com.example.demo.test.model.UserModel;

@Mapper
public interface UserMapper {

	public List<UserModel> getUserList();
	
	public int insertUser(UserModel um);
	
	public UserModel userDetail(UserModel um);
	
	public int updateUser(UserModel um);
	
	public int deleteUser(int userNo);
	
}
