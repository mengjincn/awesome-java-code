package com.java.code;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.code.amqp.Sender;
import com.java.code.flyway.Person;
import com.java.code.flyway.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaCodeApplicationTests {
	@Autowired
	Environment environment;
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
    private Sender sender;

	@Test
	public void contextLoads() {
	}

	@Test
	/**
	 * 测试MySQL数据库配置是否正确，JdbcTemplate是否可以使用
	 */
	public void testJdbcTemplate() {
		Integer count = jdbcTemplate.queryForObject("select count(id) from person", Integer.class);
		assertThat(count, greaterThan(0));
		
		long  c = repository.count();
		assertThat(c, greaterThan(0l));
	}

	@Test
	public void testEnvironment(){
		//获取系统环境变量
		System.out.println(environment.getProperty("JAVA_HOME"));
	}
	
	@Test
	public void testRedis(){
		/**
		 * 参考：
		 * http://blog.didispace.com/springbootredis/
		 * http://blog.csdn.net/aacm1992/article/details/21977237
		 */
		
		stringRedisTemplate.opsForValue().set("aaa", "111");		
		String value = stringRedisTemplate.opsForValue().get("aaa");
		assertThat(value,equalTo("111"));
		Person person = new Person();
		person.setFirstName("Meng");
		person.setLastName("Jin");
		//对象类型，对象主键，对象
		redisTemplate.opsForHash().put("Person", "ID", person);
		Person p = (Person) redisTemplate.opsForHash().get("Person", "ID");
		assertThat(p.toString(),equalTo(person.toString()));
	}
	
	@Test
	public void testAmqp(){
		sender.send();
	}
}
