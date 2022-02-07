package com.example.demo.test.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestModel {

	private String userNo;
	private String email;
	private String name;
	private String tel;
	private List<ImgModel> imgName;
}
