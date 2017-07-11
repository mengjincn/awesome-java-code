package com.java.code;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaCodeApplicationTests {
	@Autowired
	Environment environment;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	/**
	 * 测试MySQL数据库配置是否正确，JdbcTemplate是否可以使用
	 */
	public void testJdbcTemplate() {
		Integer count = jdbcTemplate.queryForObject("select count(pk) from operator", Integer.class);
		assertThat(count, greaterThan(0));
	}

	@Test
	public void testEnvironment(){
		//获取系统环境变量
		System.out.println(environment.getProperty("JAVA_HOME"));
	}
}
