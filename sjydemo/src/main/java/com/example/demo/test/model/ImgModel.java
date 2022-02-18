package com.example.demo.test.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Data 도 사용가능 
public class ImgModel {

	private int imgNo;
	private UserModel userInfo;
	private String imgName;
}
