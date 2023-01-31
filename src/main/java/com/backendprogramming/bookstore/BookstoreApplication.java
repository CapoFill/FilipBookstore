package com.backendprogramming.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backendprogramming.bookstore.domain.Book;
import com.backendprogramming.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("Saving books");
			repository.save(new Book(9781405934138L, "Mythos: The Greek Myths Retold", "Stephen Fry", 2017, 25.90));
			repository.save(new Book(9780439023481L, "The Hunger Games", "Suzanne Collins", 2008, 14.50));

			log.info("Fetching books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}

