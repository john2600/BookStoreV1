package com.virtualpairprogrammers.avalon.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.avalon.data.BookNotFoundException;
import com.virtualpairprogrammers.avalon.domain.Book;
import com.virtualpairprogrammers.avalon.services.BookService;

public class Client {
	public static void main(String[] args) throws BookNotFoundException {
		ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		try {
			BookService bookService = container.getBean("bookService", BookService.class);
			bookService.registerNewBook(new Book("2384928389222", "War and Peace", "Leo Tolstoy", 10.99));
			List<Book> allBooks = bookService.getEntireCatalogue();
			for (Book next : allBooks) {
				System.out.println(next);
			}
			try {
				Book foundBook = bookService.getBookByIsbn("2384928389222");
				System.out.println(" Book found price is " + foundBook.getPrice());
			} catch (BookNotFoundException e) {
				System.out.println("Sorry, that book doesn't exist");
			}
		} finally {
			container.close();
		}

	}
}
