package com.bernardo.servico.ordenacao.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bernardo.servico.ordenacao.model.Book;
import com.bernardo.servico.ordenacao.service.SortingService;
import com.bernardo.servico.ordenacao.service.ParseFileService;

public class Main {

	public static void main(String[] args) throws Exception {
		ParseFileService parseFileService = new ParseFileService();
		SortingService orderFileDataService = new SortingService();
		String csvFileName = "livros";

		List<Book> booksList = parseFileService.getParseCSV(csvFileName);
		Map<String,String> order = new HashMap<String,String>();
		order.put("autor", "descendente");
		order.put("titulo", "ascendente");
		orderFileDataService.orderBy(booksList, order).forEach(System.out::println);;

	}

}
