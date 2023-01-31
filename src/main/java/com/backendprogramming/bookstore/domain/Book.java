package com.backendprogramming.bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Book {
	@Id
	private long isbn; // ISBN-13
	private String title;
	private String author;
	private int publicationYear;
	private double price;
	
	public Book() {}
	
	public Book(long isbn, String title, String author, int publicationYear, double price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.price = price;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the publicationYear
	 */
	public int getPublicationYear() {
		return publicationYear;
	}
	/**
	 * @param publicationYear the publicationYear to set
	 */
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	/**
	 * @return the isbn
	 */
	public long getIsbn() {
		return isbn;
	}
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Book: [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publication year=" + publicationYear + ", price=" + price + "]";
	}
	
}
