package com.example.demo.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.test.model.ImgModel;
import com.example.demo.test.model.UserModel;

@Mapper
public interface ImgMapper {
	
	public List<ImgModel> getImgList();
	
	public int insertImg(ImgModel imgModel);	
	
	public int deleteImg(int userNo);
	
}
