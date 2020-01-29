package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeOrder extends BaseEntity implements Serializable {
    private String customer;
    private List<Coffee> items;
    private OrderState state;

    public CoffeeOrder(String customer, OrderState state) {
        this.setCreateTime(new Date());
        this.setUpdateTime(new Date());
        this.setState(state);
        this.customer = customer;
    }

    public Date getCreateTime() {
        return super.getCreateTime();
    }

    public void setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
    }

    public Date getUpdateTime() {
        return super.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        super.setUpdateTime(updateTime);
    }



}
