package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    //学生和班级是多对一的关系，即一个学生只属于一个班级
    private Clazz clazz;
}
