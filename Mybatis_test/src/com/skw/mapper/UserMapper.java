package com.skw.mapper;

import java.util.LinkedList;

import org.apache.ibatis.annotations.Param;

import com.skw.domain.User;

/*访问User表
 * @author
 */

public interface UserMapper {
	/*根据账号和密码查询
	 * MyBatis的查询操作，应该返回数据表中的数据
	 * ORM数据库中的数据，映射成java中的对象
	 * @return User对象，
	 */
	User selectUser(@Param("name")String name);
	
	int insertUser(User user);
	
	LinkedList<User> selectUsers();
}
