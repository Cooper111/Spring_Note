package com.example.demo.mapper;

import com.example.demo.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

@MapperScan
@Component
public interface ArticleMapper {
    List<Article> selectArticleByOrderId(@Param("id") Integer id);
}
