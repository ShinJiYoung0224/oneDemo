package com.example.demo.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.test.mapper.ImgMapper;
import com.example.demo.test.mapper.UserMapper;
import com.example.demo.test.model.ImgModel;
import com.example.demo.test.model.UserModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImgService {
	
	private final ImgMapper imgMapper;
	
	public List<ImgModel> getImgList(){
		return imgMapper.getImgList();
	}
	
	public int deleteImg(int userNo) {
		return imgMapper.deleteImg(userNo);
	}
	
	public int insertImg(ImgModel imgModel) {
		return imgMapper.insertImg(imgModel);
	}
}
