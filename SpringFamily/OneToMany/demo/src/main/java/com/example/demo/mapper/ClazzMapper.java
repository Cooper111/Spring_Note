package com.example.demo.mapper;

import com.example.demo.domain.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ClazzMapper {
    Clazz selectClazzById(@Param("id") Integer id);
}
