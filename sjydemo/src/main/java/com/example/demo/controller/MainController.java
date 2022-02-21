package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.model.ImgModel;
import com.example.demo.test.model.UserModel;
import com.example.demo.test.service.ImgService;
import com.example.demo.test.service.UserService;
import com.mysql.cj.log.Log;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Controller
//@RestController
@RequiredArgsConstructor
@Slf4j
public class MainController {
	
	private final UserService userService;
	private final ImgService imgService;
	
	
	//유저리스트(유저에 해당하는 이미지 리스트까지 가져옴)
	@RequestMapping("/getUserList")
	public ModelAndView getUserList(ModelAndView mv){
		List<UserModel> userList; 
		userList = userService.getUserList();
		mv.setViewName("userList.html");
		mv.addObject("userList", userList);
		return mv; 
	}
	
	//등록 폼
	@RequestMapping("/insertForm")
	public ModelAndView insertForm(ModelAndView mv) {
		mv.setViewName("insert.html");
		return mv;
	}
	
	//등록
	@RequestMapping("/insertUser")
	public ModelAndView insertUser(/* @Valid */ UserModel userModel, ImgModel imgModel,
			/* BindingResult result, */ ModelAndView mv, HttpServletRequest request) {
		
//		String[] imgList = imgModel.getImgName().split(",");
		String[] imgList = request.getParameterValues("imgName");
		
		int result = userService.insertUser(userModel);
		
		if(result > 0) {
			for(String img : imgList) {			
				imgModel.setImgName(img);
				imgModel.setUserInfo(userModel);
				imgService.insertImg(imgModel);
			}
		}else {
			log.info("유저 추가 실패");
		}
		
		
		mv.setViewName("redirect:/getUserList");
		
		return mv;
	}
	
	//디테일 및 수정화면
	@RequestMapping("/userDetail")
	public String userDetail(UserModel userModel, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		userModel = userService.userDetail(userModel);
		model.addAttribute("um", userModel);
		
		  //submit 수정 후 detail 화면으로 redirect시 alert 띄우기
		String referer = request.getHeader("referer"); 
		  if(referer.indexOf("userDetail") > -1) {
		  response.setContentType("text/html; charset=euc-kr");
		  PrintWriter out = response.getWriter();
		  out.println("<script>alert('수정 완료'); </script>");
		  out.flush(); }
		 
		return "userDetail.html";
	}
	

	//submit으로 유저 정보 업데이트
	@RequestMapping("updateUserSubmit")
	public ModelAndView updateUserSubmit(UserModel userModel, ModelAndView mv) {
		int result = userService.updateUser(userModel);
		if(result > 0) {
			mv.setViewName("redirect:/userDetail?userNo="+userModel.getUserNo());
		}else {
			log.info("업데이트 실패");
		}
		return mv;
	}
	
	//ajax로 유저 정보 업데이트
	@RequestMapping("updateUserAjax")
	@ResponseBody
	public String updateUserAjax(UserModel userModel, ModelAndView mv) {
		int result = userService.updateUser(userModel);
		if(result > 0) {
			return "OK";			
		}else {
			return "FAIL";
		}
	}
	
	//sql에서 유저 테이블 참조하여 유저의 이미지까지 삭제
	@RequestMapping("deleteUserAndUserImgRef")
	public String deleteUserAndUserImgRef(UserModel userModel, HttpServletRequest request) {
		String[] delUser = request.getParameterValues("del");
		for(String userNo : delUser) {
			userModel.setUserNo(Integer.parseInt(userNo));
			userService.deleteUser(userModel);			
		}
		return "redirect:/getUserList";
	}
	
	//유저 삭제 후 유저 이미지 삭제
	@RequestMapping("deleteUserAndUserImg")
	public String deleteUserAndUserImg(UserModel userModel, HttpServletRequest request) {
		String[] delUser = request.getParameterValues("del");
		for(String userNo : delUser) {
			userModel.setUserNo(Integer.parseInt(userNo));
			int result = userService.deleteUser(userModel);	
			if(result > 0) {
				imgService.deleteImg(userModel.getUserNo());
			}else {
				log.info("삭제 실패");
			}
		}
		
		return "redirect:/getUserList";
	}
	
	//이미지 리스트(각 이미지에 해당하는 유저정보까지 가져옴)
	@RequestMapping("getImgList")
	public ModelAndView getImgList(ModelAndView mv) {
		List<ImgModel> imgList;
		imgList = imgService.getImgList();
		mv.setViewName("imgList.html");
		mv.addObject("imgList", imgList);
		return mv;
	}

}
