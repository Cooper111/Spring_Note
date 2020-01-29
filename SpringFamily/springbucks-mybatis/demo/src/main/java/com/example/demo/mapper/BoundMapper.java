package com.example.demo.mapper;

import com.example.demo.model.Coffee;
import com.example.demo.model.CoffeeOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BoundMapper {
    int createBound(@Param("coffee_id") Long coffee_id, @Param("order_id") Long order_id);
}
