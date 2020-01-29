package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    User selectUserById(@Param("id") Integer id);
}
