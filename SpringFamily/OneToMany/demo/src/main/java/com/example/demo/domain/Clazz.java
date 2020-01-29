package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Clazz implements Serializable {
    private Integer id;
    private String code;//班级编号
    private String name;
    //班级和学生是一对多的关系，即一个班级可以有多个学生
    private List<Student> students;
}
