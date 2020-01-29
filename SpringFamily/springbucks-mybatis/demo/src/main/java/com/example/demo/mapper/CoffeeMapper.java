package com.example.demo.mapper;

import com.example.demo.model.Coffee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface CoffeeMapper {
    List<Coffee> findAll();
    Coffee findOneCoffee(String name);
}
