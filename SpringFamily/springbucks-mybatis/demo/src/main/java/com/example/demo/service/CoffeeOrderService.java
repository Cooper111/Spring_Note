package com.example.demo.service;

import com.example.demo.mapper.CoffeeMapper;
import com.example.demo.model.Coffee;
import com.example.demo.model.CoffeeOrder;
import com.example.demo.model.OrderState;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


public interface CoffeeOrderService {
//    CoffeeOrder createOrder(String name, Coffee coffee);
//    boolean updateState(CoffeeOrder order, OrderState state);
    int saveOrder(CoffeeOrder coffeeOrder);
    int saveBound(Coffee coffee, CoffeeOrder order);
    int saveOrderAndBound(Coffee coffee, CoffeeOrder order);
    boolean updateState(CoffeeOrder order, OrderState state);
}
