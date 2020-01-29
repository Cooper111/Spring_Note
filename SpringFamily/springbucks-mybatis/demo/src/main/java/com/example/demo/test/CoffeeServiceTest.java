package com.example.demo.test;

import com.example.demo.model.Coffee;
import com.example.demo.service.impl.CoffeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
//@MapperScan("com.example.demo.mapper")
//@ComponentScan("com.example.demo.mapper")
//@ComponentScan("com.example.demo.service.impl")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CoffeeServiceTest{
    @Autowired
    CoffeeServiceImpl coffeeService;

    @Test
    private void testFindAll() {
        log.info("====Start Testing Find ALL=====");
        List<Coffee> list =  coffeeService.findAll();
        list.forEach(c -> log.info("Coffee: {}", c));
    }

}
