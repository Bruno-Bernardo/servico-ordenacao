package com.bernardo.servico.ordenacao.main;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bernardo.servico.ordenacao.model.Book;
import com.bernardo.servico.ordenacao.service.ParseFileService;
import com.bernardo.servico.ordenacao.service.SortingService;

public class Main {

	public static void main(String[] args) throws Exception {
		ParseFileService parseFileService = new ParseFileService();
		SortingService orderFileDataService = new SortingService();
		
		//csv file name
		String csvFileName = "books";
		//get a List<Book> from the csv file name
		List<Book> booksList = parseFileService.getParseCSV(csvFileName);
		//define the attributes to be filtered and their respective order
		Map<String,String> order = new LinkedHashMap<String,String>();
		order.put("edition", "descendent");
		order.put("author", "descendent");
		order.put("title", "ascendent");
		//order the data from the bookList using the order previously defined 
		orderFileDataService.sortBy(booksList, order).forEach(System.out::println);;

	}

}
