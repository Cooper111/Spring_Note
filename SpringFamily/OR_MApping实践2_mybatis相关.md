# 0x05 通过Mybatis操作数据库

- 认识 MyBatis

  - MyBatis（https://github.com/mybatis/mybatis-3）
    - ⼀款优秀的持久层框架
    - ⽀持定制化 SQL、存储过程和⾼级映射
  - 在 Spring 中使⽤ MyBatis 
    - MyBatis Spring Adapter（https://github.com/mybatis/spring）
    - MyBatis Spring-Boot-Starter（https://github.com/mybatis/spring-boot-starter）

  - 简单配置

    - mybatis.mapper-locations = classpath*:mapper/**/*.xml
    - mybatis.type-aliases-package = 类型别名的包名
    - mybatis.type-handlers-package = TypeHandler扫描包名
    - mybatis.configuration.map-underscore-to-camel-case = true

  - Mapper 的定义与扫描
    - @MapperScan 配置扫描位置
    - @Mapper 定义接⼝
    - 映射的定义—— XML 与注解