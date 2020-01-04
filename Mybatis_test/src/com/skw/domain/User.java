package com.skw.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.web.multipart.MultipartFile;

public class User implements Serializable{
	//年龄不能为空
	@Range(min = 16,max = 100,message = "年龄在16到100之间")
	private int age;
	//性别不能为空
	@NotBlank(message="性别不能为空,填写男或者女")
	private String sex;
	//登录名不能为空
	@NotBlank(message="用户名不能为空")
	private String name;
	//年龄
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	private MultipartFile image;
	private String temp;
	
	public User() {
		super();
	}
	
	public User(String name, String sex,int age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
