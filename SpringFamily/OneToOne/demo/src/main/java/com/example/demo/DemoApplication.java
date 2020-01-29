package com.example.demo;

import com.example.demo.domain.Person;
import com.example.demo.mapper.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private PersonMapper personMapper;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Person p = personMapper.selectPersonById(1);
		log.info("Select Person: {}", p);
	}
}
