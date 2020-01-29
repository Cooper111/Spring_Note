package com.example.demo.mapper;

import com.example.demo.model.Coffee;
import com.example.demo.model.CoffeeOrder;
import com.example.demo.model.OrderState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderMapper {
//    CoffeeOrder createOrder(String name, Coffee coffee);
//    boolean updateState(CoffeeOrder order, OrderState state);
    int saveOrder(CoffeeOrder coffeeOrder);
    boolean updateState(@Param("order") CoffeeOrder order, @Param("state") Integer state);
}
