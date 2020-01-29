package com.example.demo.mapper;

import com.example.demo.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderMapper {
    Order selectOrderByUserId(@Param("id") Integer id);
    Order selectOrderById(@Param("id") Integer id);
}
