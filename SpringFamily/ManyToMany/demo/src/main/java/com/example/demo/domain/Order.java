package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private Integer id;
    private String code;
    private Double total;
    //订单和用户是多对一的关系，即一个订单只属于一个用户
    private User user;
    //订单和商品是多对多的关系，即一个订单可以包含多种商品
    private List<Article> articles;
}
