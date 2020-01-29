package com.example.demo.mapper;

import com.example.demo.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StudentMapper {
    List<Student> selectStudentByClazzId(@Param("clazz_id") int clazz_id);
    Student selectStudentById(@Param("id") Integer id);
}
