package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.model.ImgModel;
import com.example.demo.test.model.UserModel;
import com.example.demo.test.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
//@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	

	@RequestMapping("/test")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("index.html");
		return mv;
//		return "test.html";
	}
	
	@RequestMapping("/getList")
	public ModelAndView getList(ModelAndView mv){
		List<UserModel> um; 
		um = userService.getList();
		mv.setViewName("list.html");
		mv.addObject("list", um);
		return mv; 
	}
	
	@RequestMapping("/insertForm")
	public ModelAndView insertForm(ModelAndView mv) {
		mv.setViewName("insert.html");
		return mv;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(UserModel um,ModelAndView mv) {
		userService.insert(um);
		mv.setViewName("redirect:/getList");
		return mv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(UserModel um, ModelAndView mv) {
		um = userService.detail(um);
		mv.setViewName("detail.html");
		mv.addObject("um", um);
		return mv;
	}
	
	@RequestMapping("ajaxTest")
	@ResponseBody
	public String ajaxTest(UserModel um) {
		String result = userService.ajaxTest(um);
		return result;
	}
	
	@RequestMapping("update")
	public ModelAndView update(UserModel um, ModelAndView mv) {
		userService.update(um);
		mv.addObject("um",um);
		mv.addObject("updateYn", "OK");
		mv.setViewName("redirect:/detail?userNo="+um.getUserNo());
		return mv;
	}
	
	@RequestMapping("updateAjax")
	@ResponseBody
	public String updateAjax(UserModel um, ModelAndView mv) {
		userService.update(um);
		return "OK";
	}
	
	@RequestMapping("deleteUser")
	public String deleteUser(UserModel um, HttpServletRequest request) {
		String[] delUser = request.getParameterValues("del");
		for(String userNo : delUser) {
			um.setUserNo(Integer.parseInt(userNo));
			userService.deleteUser(um);			
		}
		return "redirect:/getList";
	}
	
	@RequestMapping("getImgList")
	public ModelAndView getImgList(ModelAndView mv) {
		List<ImgModel> im;
		im = userService.getImgList();
		mv.addObject("imgList", im);
		mv.setViewName("imgList.html");
		return mv;
	}
}
