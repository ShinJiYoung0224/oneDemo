package com.example.demo.test.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Data 도 사용가능
public class UserModel {
	
	private int userNo;
	
	@NotBlank(message = "이메일을 입력하세요.")
	@Email(message = "이메일 형식을 확인해주세요.")
	private String email;
	
	private String name;
	
	@NotBlank(message = "휴대폰번호를 입력하세요.")
	@PositiveOrZero(message = "휴대폰번호는 숫자만 입력해주세요.")
	private String tel;
	
	private List<ImgModel> imgList;
}
