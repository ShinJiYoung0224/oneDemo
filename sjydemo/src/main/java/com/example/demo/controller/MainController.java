package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.model.TestModel;
import com.example.demo.test.service.TestService;

import lombok.RequiredArgsConstructor;

@Controller
//@RestController
@RequiredArgsConstructor
public class MainController {
	
	private final TestService testService;
	

	@RequestMapping("/main")
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("test.html");
		mv.addObject("test", "지영짱");
		return mv;
//		return "test.html";
	}
	
	@RequestMapping("/getList")
	public ModelAndView getList(ModelAndView mv){
		List<TestModel> tm; 
		tm = testService.getList();
		mv.setViewName("list.html");
		mv.addObject("list", tm);
		return mv; 
	}
	
	@RequestMapping("/insertForm")
	public ModelAndView insertForm(ModelAndView mv) {
		mv.setViewName("insert.html");
		return mv;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(TestModel tm,ModelAndView mv) {
		testService.insert(tm);
		mv.setViewName("redirect:/getList");
		return mv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(TestModel tm, ModelAndView mv) {
		tm = testService.detail(tm);
		mv.setViewName("detail.html");
		mv.addObject("tm", tm);
		return mv;
	}
	
	@RequestMapping("ajaxTest")
	@ResponseBody
	public String ajaxTest(TestModel tm) {
		String result = testService.ajaxTest(tm);
		return result;
	}
}
