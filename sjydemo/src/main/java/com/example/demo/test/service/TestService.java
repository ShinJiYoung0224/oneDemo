package com.example.demo.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.test.mapper.TestMapper;
import com.example.demo.test.model.TestModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
	
	private final TestMapper testMapper;
	
	public List<TestModel> getList(){
		return testMapper.getList();
	}
	
	public void insert(TestModel tm) {
		testMapper.insert(tm);
	}
	
	public TestModel detail(TestModel tm) {
		return testMapper.detail(tm);
	}

	public String ajaxTest(TestModel tm) {
		return testMapper.ajaxTest(tm);
	}
	
	public void update(TestModel tm) {
		testMapper.update(tm);
	}
}
