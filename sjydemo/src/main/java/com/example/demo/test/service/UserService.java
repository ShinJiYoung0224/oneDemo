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
	private final ImgService imgService;
	
	public List<UserModel> getUserList(){
		return userMapper.getUserList();
	}
	
	public int insertUser(UserModel userModel) {
		return userMapper.insertUser(userModel);
	}
	
	public UserModel userDetail(UserModel userModel) {
		return userMapper.userDetail(userModel);
	}
	
	public String updateUser(UserModel userModel, ImgModel imgModel) {
		
		int result = userMapper.updateUser(userModel);
		if (result > 0) {
			if (!imgModel.getImgName().equals("")) {
				imgService.deleteImg(userModel.getUserNo());
				imgModel.setUserInfo(userModel);
				//현재 이미지를 텍스트로 받아와서 가능함...! 실제 이미지 올리는걸로 바꾸게되면 수정요
				String[] imgList = imgModel.getImgName().split(",");
				for (String img : imgList) {
					imgModel.setImgName(img);
					imgModel.setUserInfo(userModel);
					imgService.insertImg(imgModel);
				}
			}else {
				imgService.deleteImg(userModel.getUserNo());
			}
			return "OK";
		} else {
			return "FAIL";
		}
	}
	
	public int deleteUser(UserModel userModel) {
		return userMapper.deleteUser(userModel);
	}
	
}
