package com.skw.tst;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.skw.domain.User;

public class MyBatisTest {
	
	public static void main(String[] args) {
		//����sqlsessionʵ��
		SqlSession sqlSession = null;
		try(//��ȡMybatis-config.xml�ļ�
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		){
			//��ʼ��mybatis������sqlsessionFactory���ʵ��
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			sqlSession = sqlSessionFactory.openSession();
			//����User����
			User user = new User("admin","��",22);
			//��������
			sqlSession.insert("com.skw.mapper.UserMapper.insertUser", user);
			//�ύ����
			sqlSession.commit();
		} catch (Exception e) {
			// �ع�����
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			try {
				//�ر�sqlSession
				if(sqlSession != null) {
					sqlSession.close();
					} 
				} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
