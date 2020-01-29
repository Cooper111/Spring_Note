package com.example.demo.mapper;

import com.example.demo.domain.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PersonMapper {
    Person selectPersonById(@Param("id") Integer id);
}
