package com.example.demo.service.impl;

import com.example.demo.mapper.CoffeeMapper;
import com.example.demo.model.Coffee;
import com.example.demo.service.CoffeeService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class CoffeeServiceImpl implements CoffeeService {
    @Autowired
    private CoffeeMapper coffeeMapper;

    @Override
    public List<Coffee> findAll() {
        return coffeeMapper.findAll();
    }

    @Override
    public Coffee findOneCoffee(String name) {
        return coffeeMapper.findOneCoffee(name);
    }
}
