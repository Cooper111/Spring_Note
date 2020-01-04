package com.skw.domain;

import java.io.Serializable;

public class Book implements Serializable{
	private String name;
	private String author;
	private double price;
	private String image;
	
	public Book(String image, String name, String author, double price) {
		this.image = image;
		this.name = name;
		this.author = author;
		this.price = price;
	}
	public Book() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
