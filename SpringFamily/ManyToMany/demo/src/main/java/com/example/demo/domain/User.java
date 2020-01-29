package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String username;
    private String loginname;
    private String password;
    private String phone;
    private String address;
    //用户和订单是一对多的关系，即一个用户可以有多个订单
    private List<Order> orders;
}
