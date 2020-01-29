package com.example.demo;

import com.example.demo.domain.Clazz;
import com.example.demo.domain.Student;
import com.example.demo.mapper.ClazzMapper;
import com.example.demo.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private ClazzMapper clazzMapper;
	@Autowired
	private StudentMapper studentMapper;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Clazz clazz = clazzMapper.selectClazzById(1);
		log.info("Select Clazz: {}", clazz);
		Student student = studentMapper.selectStudentById(1);
		log.info("Select Student: {}", student);
	}
}
