package com.example.demo.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.test.model.ImgModel;
import com.example.demo.test.model.UserModel;

@Mapper
public interface UserMapper {

	public List<UserModel> getList();
	
	public void insert(UserModel um);
	
	public UserModel detail(UserModel um);
	
	public String ajaxTest(UserModel um);
	
	public void update(UserModel um);
	
	public void deleteUser(UserModel um);
	
	public List<ImgModel> getImgList();
	
}
