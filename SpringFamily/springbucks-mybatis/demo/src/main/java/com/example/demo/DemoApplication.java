package com.example.demo;

import com.example.demo.model.Coffee;
import com.example.demo.model.CoffeeOrder;
import com.example.demo.model.OrderState;
import com.example.demo.service.impl.CoffeeOrderServiceImpl;
import com.example.demo.service.impl.CoffeeServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan("com.example.demo.mapper")
public class DemoApplication implements CommandLineRunner {
    @Autowired
    CoffeeServiceImpl coffeeService;
    @Autowired
    CoffeeOrderServiceImpl orderService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        List<Coffee> list =  coffeeService.findAll();
        list.forEach(c -> log.info("Coffee: {}", c));
        Coffee c = coffeeService.findOneCoffee("espresso");
        log.info(c.toString());
        CoffeeOrder co = new CoffeeOrder("Li lei", OrderState.INIT);
//        log.info("Saving Order nums: {}",String.valueOf(orderService.saveOrder(co)));
//        log.info("Saving Bound nums: {}",String.valueOf(orderService.saveBound(c, co)));
        log.info("Saving Order and Bound nums: {}",String.valueOf(orderService.saveOrderAndBound(c, co)));
        log.info("Change Order State: {}", orderService.updateState(co, OrderState.PAID));

    }
}
