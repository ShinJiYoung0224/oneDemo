package com.example.demo.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.model.ImgModel;
import com.example.demo.test.model.UserModel;
import com.example.demo.test.service.ImgService;
import com.example.demo.test.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
//@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {

	private final UserService userService;
	private final ImgService imgService;

	// 유저리스트(유저에 해당하는 이미지 리스트까지 가져옴)
	@GetMapping("/getUserList")
	public ModelAndView getUserList(ModelAndView mv) {
		List<UserModel> userList;
		userList = userService.getUserList();
		mv.setViewName("userList.html");
		mv.addObject("userList", userList);
		return mv;
	}

	// 등록 폼
	@GetMapping("/insertForm")
	public ModelAndView insertForm(ModelAndView mv) {
		mv.setViewName("insertForm.html");
		return mv;
	}
	
	// 등록
	@PostMapping("/insertUser")
	@ResponseBody
	public String insertUser(@Valid UserModel userModel, ImgModel imgModel, BindingResult bindingResult,
			ModelAndView mv, HttpServletRequest request) {

		String result = userService.insertUser(userModel, imgModel);

		return result;
	}

	// 디테일 및 수정화면
	@GetMapping("/userDetail")
	public String userDetail(UserModel userModel, Model model, HttpServletRequest request, HttpServletResponse response) {
		userModel = userService.userDetail(userModel);
		model.addAttribute("user", userModel);

		return "userDetail.html";
	}

	// ajax로 유저 정보 업데이트
	@PostMapping("updateUserAjax")
	@ResponseBody
	public String updateUserAjax(UserModel userModel, ImgModel imgModel) {

		String result = userService.updateUser(userModel, imgModel);
		
		return result;
	}

	// sql에서 유저 테이블 참조하여 유저의 이미지까지 삭제
//	@RequestMapping("deleteUserAndUserImgRef")
//	public String deleteUserAndUserImgRef(UserModel userModel, HttpServletRequest request) {
//		String[] delUser = request.getParameterValues("del");
//		for (String userNo : delUser) {
//			userModel.setUserNo(Integer.parseInt(userNo));
//			userService.deleteUser(userModel);
//		}
//		return "redirect:/getUserList";
//	}

	// 유저 삭제 후 유저 이미지 삭제
	@PostMapping("deleteUserAndUserImg")
	@ResponseBody
	public String deleteUserAndUserImg(@Param(value="del") String[] del) {
		
		String result = userService.deleteUser(del);

		return result;
	}

	// 이미지 리스트(각 이미지에 해당하는 유저정보까지 가져옴)
	@GetMapping("getImgList")
	public ModelAndView getImgList(ModelAndView mv) {
		List<ImgModel> imgList;
		imgList = imgService.getImgList();
		mv.setViewName("imgList.html");
		mv.addObject("imgList", imgList);
		return mv;
	}

}
