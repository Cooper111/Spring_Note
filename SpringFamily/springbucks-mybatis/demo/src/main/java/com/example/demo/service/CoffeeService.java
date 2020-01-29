package com.example.demo.service;

import com.example.demo.model.Coffee;

import java.util.List;

public interface CoffeeService {
    public List<Coffee> findAll();
    public Coffee findOneCoffee(String name);
}
