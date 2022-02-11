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
	
	public List<UserModel> getList(){
		return userMapper.getList();
	}
	
	public void insert(UserModel um) {
		userMapper.insert(um);
	}
	
	public UserModel detail(UserModel um) {
		return userMapper.detail(um);
	}

	public String ajaxTest(UserModel um) {
		return userMapper.ajaxTest(um);
	}
	
	public void update(UserModel um) {
		userMapper.update(um);
	}
	
	public void deleteUser(UserModel um) {
		userMapper.deleteUser(um);
	}
	
	public List<ImgModel> getImgList(){
		return userMapper.getImgList();
	}
}
