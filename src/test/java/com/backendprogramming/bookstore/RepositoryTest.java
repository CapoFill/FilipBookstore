package com.backendprogramming.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.backendprogramming.bookstore.domain.Book;
import com.backendprogramming.bookstore.domain.BookRepository;
import com.backendprogramming.bookstore.domain.Category;
import com.backendprogramming.bookstore.domain.CategoryRepository;
import com.backendprogramming.bookstore.domain.User;
import com.backendprogramming.bookstore.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTest {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	@Autowired
	private UserRepository urepository;

	// Book Repository
	@Test
	public void testFindByIsbn() {
		List<Book> books = repository.findByIsbn(9781405934138L);
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Mythos: The Greek Myths Retold");
	}

	// Category Repository
	@Test
	public void testFindByName() {
		List<Category> categories = crepository.findByName("Mythology");
		assertThat(categories).hasSize(1);
	}

	// User Repository

	@Test
	public void testFindByUsername() {
		User user = urepository.findByUsername("user");
		assertThat(user.getRole()).isEqualTo("USER");
	}

	// Book and Category create
	@Test
	public void createNewBookAndCategory() {
		Book book = new Book(9780571364879L, "Klara and the Sun", "Kazuo Ishiguro", new Category("Science Fiction"),
				2021, 22.90);
		repository.save(book);
		assertThat(book.getIsbn()).isNotNull();
	}
	
	// Book delete
	@Test
	public void deleteBookAndCategory() {
		repository.deleteById(9781405934138L);
		List<Book> books = repository.findByIsbn(9780571364879L);
		assertThat(books).hasSize(0);
	}

	// User create and delete
	@Test
	public void createUserAndDelete() {
		User testUser = new User("test", "$2a$10$Vg/.S66MjkNeJp4pC/H8QuD6HH24PRhTyFY2EJ78iztdDbRM1VaN6", "USER");
		urepository.save(testUser);
		User user = urepository.findByUsername("test");
		assertThat(user.getRole()).isEqualTo("USER");
		urepository.delete(user);
		assertThat(urepository.count()).isEqualTo(2);
	}
}