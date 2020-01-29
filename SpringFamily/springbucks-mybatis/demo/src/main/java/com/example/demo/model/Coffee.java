package com.example.demo.model;

import lombok.*;
import org.joda.money.Money;

import java.io.Serializable;


@Builder
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Coffee extends BaseEntity implements Serializable {
    private String name;
    private Money price;
}
