package com.example.demo.mapper;

import com.example.demo.domain.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CardMapper {
    Card selectCardById(@Param("id") Integer id);
}
