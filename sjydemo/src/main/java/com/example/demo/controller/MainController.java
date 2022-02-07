package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.test.model.TestModel;
import com.example.demo.test.service.TestService;

import lombok.RequiredArgsConstructor;

//@Controller
@RestController
@RequiredArgsConstructor
public class MainController {
	
	private final TestService testService;

	@RequestMapping("/main")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test.html");
		mv.addObject("test", "지영짱");
		return mv;
//		return "test.html";
	}
	
	@RequestMapping("/getList")
//	@ResponseBody
	public ModelAndView getList(){
		List<TestModel> tm; 
		tm = testService.getList();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("list.html");
		mv.addObject("list", tm);
		return mv; 
	}
	
	@RequestMapping("/insertForm")
	public ModelAndView insertForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("insert.html");
		return mv;
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(TestModel tm) {
		testService.insert(tm);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/getList");
		return mv;
	}
}
