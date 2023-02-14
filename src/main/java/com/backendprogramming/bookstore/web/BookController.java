package com.backendprogramming.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.backendprogramming.bookstore.domain.Book;
import com.backendprogramming.bookstore.domain.BookRepository;
import com.backendprogramming.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;

	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/delete/{isbn}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("isbn") Long isbn, Model model) {
		repository.deleteById(isbn);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/edit/{isbn}")
	public String editBook(@PathVariable("isbn") Long isbn, Long id, Model model){
		model.addAttribute("book", repository.findById(isbn));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
	
	//REST return all books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }    
	
	//REST return book by isbn
    @RequestMapping(value="/book/{isbn}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("isbn") Long isbn) {	
    	return repository.findById(isbn);
    }       
}
