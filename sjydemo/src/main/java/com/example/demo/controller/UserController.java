package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.model.ImgModel;
import com.example.demo.test.model.UserModel;
import com.example.demo.test.service.ImgService;
import com.example.demo.test.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
//@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final ImgService imgService;
	

	@RequestMapping("/test")
	public ModelAndView main(ModelAndView mv) {
		System.out.println("aㅁaa");
		mv.setViewName("index.html");
		return mv;
//		return "test.html";
	}
	
	@RequestMapping("/getList")
	public ModelAndView getList(ModelAndView mv){
		List<UserModel> um; 
		um = userService.getUserList();
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
	public ModelAndView insertUser(UserModel um,ModelAndView mv) {
		userService.insertUser(um);
		mv.setViewName("redirect:/getList");
		return mv;
	}
	
	@RequestMapping("/detail")
	public String detailUser(UserModel um, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		um = userService.detailUser(um);
		model.addAttribute("um", um);
		
		String referer = request.getHeader("referer");
		if(referer.indexOf("detail") > -1) {
			response.setContentType("text/html; charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정 완료'); </script>");
			out.flush();
		}
		return "detail.html";
	}
	
	@RequestMapping("ajaxTest")
	@ResponseBody
	public String ajaxTest(UserModel um) {
		String result = userService.ajaxTest(um);
		return result;
	}
	
	@RequestMapping("updateUser")
	public ModelAndView updateUser(UserModel um, ModelAndView mv) {
		userService.updateUser(um);
		mv.addObject("um",um);
		mv.setViewName("redirect:/detail?userNo="+um.getUserNo());
		return mv;
	}
	
	@RequestMapping("updateAjax")
	@ResponseBody
	public String updateAjax(UserModel um, ModelAndView mv) {
		userService.updateUser(um);
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
		im = imgService.getImgList();
		mv.setViewName("imgList.html");
		mv.addObject("imgList", im);
		return mv;
	}
}
