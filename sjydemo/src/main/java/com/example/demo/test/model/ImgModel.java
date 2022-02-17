package com.example.demo.test.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImgModel {

	private int imgNo;
	private UserModel userInfo;
	private String imgName;
}
