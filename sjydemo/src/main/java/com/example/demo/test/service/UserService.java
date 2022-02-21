package com.example.demo.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.test.mapper.UserMapper;
import com.example.demo.test.model.ImgModel;
import com.example.demo.test.model.UserModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	
	public List<UserModel> getUserList(){
		return userMapper.getUserList();
	}
	
	public int insertUser(UserModel userModel) {
		return userMapper.insertUser(userModel);
	}
	
	public UserModel userDetail(UserModel userModel) {
		return userMapper.userDetail(userModel);
	}
	
	public int updateUser(UserModel userModel) {
		return userMapper.updateUser(userModel);
	}
	
	public int deleteUser(UserModel userModel) {
		return userMapper.deleteUser(userModel);
	}
	
}
