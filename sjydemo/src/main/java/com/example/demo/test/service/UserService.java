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
	
	public void insertUser(UserModel um) {
		userMapper.insertUser(um);
	}
	
	public UserModel detailUser(UserModel um) {
		return userMapper.detailUser(um);
	}

	public String ajaxTest(UserModel um) {
		return userMapper.ajaxTest(um);
	}
	
	public void updateUser(UserModel um) {
		userMapper.updateUser(um);
	}
	
	public int deleteUser(UserModel um) {
		return userMapper.deleteUser(um);
	}
}
