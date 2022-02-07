package com.example.demo.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.test.model.TestModel;

@Mapper
public interface TestMapper {

	public List<TestModel> getList();
	
	public void insert(TestModel tm);
}
