package com.example.utils;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


//context confiugration permet de cibler la classe
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataUtils.class)
public class DataUtilsTest {

	@Autowired
	DataUtils dataUtils; 
	
	@Value("${ext.url}")
	private String url;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
