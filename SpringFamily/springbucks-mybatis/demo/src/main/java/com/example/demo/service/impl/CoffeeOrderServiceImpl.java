package com.example.demo.service.impl;


import com.example.demo.mapper.BoundMapper;
import com.example.demo.mapper.CoffeeMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Coffee;
import com.example.demo.model.CoffeeOrder;
import com.example.demo.model.OrderState;
import com.example.demo.service.CoffeeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CoffeeOrderServiceImpl implements CoffeeOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private BoundMapper boundMapper;

    @Override
    public int saveOrder(CoffeeOrder coffeeOrder) {
        return orderMapper.saveOrder(coffeeOrder);
    }

    @Override
    public int saveBound(Coffee coffee, CoffeeOrder order) {
        return boundMapper.createBound(coffee.getId(), order.getId());
    }

    @Override
    public int saveOrderAndBound(Coffee coffee, CoffeeOrder order) {
        orderMapper.saveOrder(order);
        return boundMapper.createBound(coffee.getId(), order.getId());
    }

    @Override
    public boolean updateState(CoffeeOrder order, OrderState state) {
        return orderMapper.updateState(order, state.ordinal());
    }

//    @Override
//    public CoffeeOrder createOrder(String name, Coffee coffee) {
//        return orderMapper.createOrder(name, coffee);
//    }
//
//    @Override
//    public boolean updateState(CoffeeOrder order, OrderState state) {
//        return orderMapper.updateState(order, state);
//    }
}
