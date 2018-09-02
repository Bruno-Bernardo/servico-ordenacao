package com.bernardo.servico.ordenacao.main;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bernardo.servico.ordenacao.model.Book;
import com.bernardo.servico.ordenacao.service.ParseFileService;
import com.bernardo.servico.ordenacao.service.SortingService;
import com.bernardo.servico.ordenacao.service.SortingServiceException;

/**
 * @author bbbru
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		ParseFileService parseFileService = new ParseFileService();
		SortingService orderFileDataService = new SortingService();
		
		//csv file name
		String csvFileName = "books";
		
		System.out.println("Example:");
		//get a List<Book> from the csv file name
		List<Book> booksList = parseFileService.getParseCSV(csvFileName);
		//define the attributes to be filtered and their respective order
		Map<String,String> order = new LinkedHashMap<String,String>();
		order.put("edition", "descendent");
		order.put("author", "descendent");
		order.put("title", "ascendent");
		//order the data from the bookList using the order previously defined and print all the itens from the returned List<Book>
		orderFileDataService.sortBy(booksList, order).forEach(System.out::println);

		//Tests answer 
		//ALL THIS TESTS ARE IMPLEMENTED IN THE CLASS: "TestMain.java"
		System.out.println("Response to the test By Bruno Bernardo:");
		//First test answer 
		System.out.println("TEST 01:");
		Map<String,String> orderTest01 = new LinkedHashMap<String,String>();
		orderTest01.put("title", "ascendent");
		orderFileDataService.sortBy(parseFileService.getParseCSV(csvFileName),orderTest01).forEach(System.out::println);
		
		//Second test answer 
		System.out.println("TEST 02:");
		Map<String,String> orderTest02 = new LinkedHashMap<String,String>();
		orderTest02.put("author", "ascendent");
		orderTest02.put("title", "descendent");
		orderFileDataService.sortBy(parseFileService.getParseCSV(csvFileName),orderTest02).forEach(System.out::println);
		
		//Third test answer 
		System.out.println("TEST 03:");
		Map<String,String> orderTest03 = new LinkedHashMap<String,String>();
		orderTest03.put("edition", "descendent");
		orderTest03.put("author", "descendent");
		orderTest03.put("title", "ascendent");
		orderFileDataService.sortBy(parseFileService.getParseCSV(csvFileName),orderTest03).forEach(System.out::println);
		
		//Fourth test answer 
		System.out.println("TEST 04:");
		Map<String,String> orderTest04 = null;
		try {
			orderFileDataService.sortBy(parseFileService.getParseCSV(csvFileName),orderTest04);
		}catch(SortingServiceException e){
			System.out.println("A SortingServiceException was catched");
		}
		
		//Fifth test answer 
		System.out.println("TEST 05:");
		Map<String,String> orderTest05 = new LinkedHashMap<String,String>();
		if(orderFileDataService.sortBy(parseFileService.getParseCSV(csvFileName),orderTest05).isEmpty()){
			System.out.println("The books list returned empty");
		}
		
	}

}
