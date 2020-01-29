package com.example.demo;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.example.demo.mapper")
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = userMapper.selectUserById(1);
		log.info("Select User: {}", user);
		Order order = orderMapper.selectOrderById(2);
		log.info("Select Order: {}", order);
	}
}
