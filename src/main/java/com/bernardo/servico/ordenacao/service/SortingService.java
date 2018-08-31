package com.bernardo.servico.ordenacao.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bernardo.servico.ordenacao.model.Book;

/**
 * @author bbbru
 *
 */
public class SortingService {
	
	/**
	 * descrição do metodo
	 * 
	 * @param order
	 * @return a order validated: ASC or DESC
	 * @throws Exception
	 *             when param is null, empty or not contains asc or desc
	 */
	public String validateOrder(String order) throws Exception {
		if (order != null && !order.isEmpty()) {
			if (order.toUpperCase().contains("ASC")) {
				return "ASC";
			} else if (order.toUpperCase().contains("DESC")) {
				return "DESC";
			}
		}
		throw new Exception("Invalid order parameter. The parameter must contain: ASC or DESC");
	}

	public String validateFilter(String filter) throws Exception {
		filter = Normalizer.normalize(filter, Normalizer.Form.NFD);
		filter = filter.replaceAll("[^\\p{ASCII}]", "");
		if (filter != null && !filter.isEmpty()) {
			if (filter.toUpperCase().contains("TITULO")) {
				return "TITULO";
			} else if (filter.toUpperCase().contains("AUTOR")) {
				return "AUTOR";
			} else if (filter.toUpperCase().contains("EDICAO")) {
				return "EDICAO";
			}
		}
		throw new Exception("Invalid atribute parameter. The parameter must contain: TITULO, AUTOR or EDICAO");
	}

	// validateOrderParam(String param); param = atributos dos livros

	/**
	 * Returns a List<Book> with the objects in the order that was defined by the parameter "orderByFilter"
	 * 
	 * @param orderByFilter
	 * @return List<Book>
	 */
	public List<Book> orderBy(List<Book> books, Map<String, String> orderByFilter) throws Exception { // o map define o campo e a ordem que devera ser seguida
		
		Stream<Book> booksListStream = books.stream();
		
		for (Map.Entry<String,String> book : orderByFilter.entrySet()) {
			try {
				
				book.setValue(validateOrder(book.getValue()));
				if(book.getValue().equals("ASC")) {
					if(validateFilter(book.getKey()).equals("TITULO")) {
						booksListStream = booksListStream.sorted(Comparator.comparing(Book::getTitulo));
					}else if(validateFilter(book.getKey()).equals("AUTOR")) {
						booksListStream = booksListStream.sorted(Comparator.comparing(Book::getAutor));
					}else if(validateFilter(book.getKey()).equals("EDICAO")) {
						booksListStream = booksListStream.sorted(Comparator.comparing(Book::getEdicao));
					}
				}else if(book.getValue().equals("DESC")) {
					if(validateFilter(book.getKey()).equals("TITULO")) {
						booksListStream = booksListStream.sorted(Comparator.comparing(Book::getTitulo).reversed());
					}else if(validateFilter(book.getKey()).equals("AUTOR")) {
						booksListStream = booksListStream.sorted(Comparator.comparing(Book::getAutor).reversed());
					}else if(validateFilter(book.getKey()).equals("EDICAO")) {
						booksListStream = booksListStream.sorted(Comparator.comparing(Book::getEdicao).reversed());
					}
				}else {
					System.out.println("DEU BEM ERRADO");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		//booksListStream = orderByFilter.forEach((filter,order) -> {
			
		
		//});
		
		
		//List<Book> booksList = books.stream().sorted(Comparator.comparing(Book::getTitulo)).map(e -> new Book(e.getTitulo(), e.getAutor(), e.getEdicao())).collect(Collectors.toCollection(ArrayList::new));
		//booksList.addAll(books.stream().sorted(Comparator.comparing(Book::getAutor).reversed()).map(e -> new Book(e.getTitulo(), e.getAutor(), e.getEdicao())).collect(Collectors.toCollection(ArrayList::new)));
		//booksList.addAll(books.stream().sorted(Comparator.comparing(Book::getAutor)).map(e -> new Book(e.getTitulo(), e.getAutor(), e.getEdicao())).collect(Collectors.toCollection(ArrayList::new)));
		
		return booksListStream.collect(Collectors.toCollection(ArrayList::new));
	}
}
