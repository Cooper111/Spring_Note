package com.skw.controller;

import java.util.ArrayList;
import java.util.List;

import com.skw.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

	@RequestMapping(value="/mainBook")
	public String main(Model model) {

		List<Book> book_list = new ArrayList<Book>();
		book_list.add(new Book("1.jpg","书名1","作者1", 1));
		book_list.add(new Book("2.jpg","书名2","作者2", 2));
		book_list.add(new Book("3.jpg","书名3","作者3", 3));
		book_list.add(new Book("4.jpg","书名4","作者4", 4));
		model.addAttribute("book_list", book_list);

		return "mainBook";
	}
}
