package com.bernardo.servico.ordenacao.model;

public class Book {
	private String titulo;
	private String autor;
	private int edicao;

	public Book() {
	}

	public Book(String titulo, String autor, int edicao) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.edicao = edicao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	@Override
	public String toString() {
		return "Titulo: " + titulo + ", Autor: " + autor + ", Edicao: " + edicao + ";";
	}
}
