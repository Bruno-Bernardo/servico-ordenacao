package com.bernardo.servico.ordenacao.model;

/**
 * @author bbbru
 *
 */
public class Book {
	private String title;
	private String author;
	private int edition;

	public Book() {
	}

	public Book(String titulo, String author, int edition) {
		super();
		this.title = titulo;
		this.author = author;
		this.edition = edition;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String titulo) {
		this.title = titulo;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	@Override
	public String toString() {
		return "Title: " + title + ", Author: " + author + ", Edition: " + edition + ";";
	}
}
