package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private String remark;
    //商品和订单之间是多对多的关系，即一种商品可以出现在多个订单中
    private List<Order> orders;
}
