package com.backendprogramming.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.backendprogramming.bookstore.domain.Book;
import com.backendprogramming.bookstore.domain.BookRepository;
import com.backendprogramming.bookstore.domain.Category;
import com.backendprogramming.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("Saving categories");
			crepository.save(new Category("Mythology"));
			crepository.save(new Category("Dystopian"));
			log.info("Saving books");
			System.out.println();
			repository.save(new Book(9781405934138L, "Mythos: The Greek Myths Retold", "Stephen Fry", crepository.findById(1L).get(), 2017, 25.90));
			repository.save(new Book(9780439023481L, "The Hunger Games", "Suzanne Collins", crepository.findById(2L).get(), 2008, 14.50));

			log.info("Fetching books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());

				log.info("Fetching categories");
				for (Category category : crepository.findAll()) {
					log.info(category.toString());
				}
			}
		};
	}
}
