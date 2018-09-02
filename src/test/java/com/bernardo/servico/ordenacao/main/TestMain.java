/**
 * 
 */
package com.bernardo.servico.ordenacao.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.bernardo.servico.ordenacao.model.Book;
import com.bernardo.servico.ordenacao.service.ParseFileService;
import com.bernardo.servico.ordenacao.service.SortingService;
import com.bernardo.servico.ordenacao.service.SortingServiceException;

/**
 * @author bbbru
 *
 */
public class TestMain {

	final ParseFileService parseFileService = new ParseFileService();
	final SortingService orderFileDataService = new SortingService();
	
	@Test
	public void test01() throws SortingServiceException, Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		
		Map<String,String> order= new LinkedHashMap<String,String>();
		order.put("title", "ascendent");
		
		List<Book> result = orderFileDataService.sortBy(parseFileService.getParseCSV("books"),order);
		
		String resultString = result.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		Assert.assertTrue(expectedString.equals(resultString));
	}
	
	@Test
	public void test02() throws SortingServiceException, Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		
		Map<String,String> order= new LinkedHashMap<String,String>();
		order.put("author", "ascendent");
		order.put("title", "descendent");
		
		List<Book> result = orderFileDataService.sortBy(parseFileService.getParseCSV("books"),order);
		
		String resultString = result.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		Assert.assertTrue(expectedString.equals(resultString));
	}
	
	@Test
	public void test03() throws SortingServiceException, Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		
		Map<String,String> order= new LinkedHashMap<String,String>();
		order.put("edition", "descendent");
		order.put("author", "descendent");
		order.put("title", "ascendent");
		
		List<Book> result = orderFileDataService.sortBy(parseFileService.getParseCSV("books"),order);
		
		String resultString = result.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		Assert.assertTrue(expectedString.equals(resultString));
	}
	
	@Test (expected = SortingServiceException.class)
	public void test04() throws SortingServiceException, Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		
		Map<String,String> order= null;
		
		List<Book> result = orderFileDataService.sortBy(parseFileService.getParseCSV("books"),order);

		Assert.assertTrue(!result.isEmpty());
	}
	
	@Test
	public void test05() throws SortingServiceException, Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		
		Map<String,String> order= new LinkedHashMap<String,String>();
		
		List<Book> result = orderFileDataService.sortBy(parseFileService.getParseCSV("books"),order);

		Assert.assertTrue(result.isEmpty());
	}

}
