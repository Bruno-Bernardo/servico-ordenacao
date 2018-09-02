package com.bernardo.servico.ordenacao.service;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.bernardo.servico.ordenacao.model.Book;

/**
 * @author bbbru
 *
 */
public class TestSortingService {

	private final SortingService orderFileDataService = new SortingService();

	@Test(expected = Exception.class)
	public void testValidateOrder01() throws Exception {
		String expected = "ASC";
		String result = orderFileDataService.validateOrder(null);
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testValidateOrder02() throws Exception {
		String expected = "ASC";
		String result = orderFileDataService.validateOrder("ascendente");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testValidateOrder03() throws Exception {
		String expected = "DESC";
		String result = orderFileDataService.validateOrder("descendente");
		Assert.assertEquals(expected, result);
	}

	@Test(expected = Exception.class)
	public void testValidateOrder04() throws Exception {
		String expected = "DESC";
		String result = orderFileDataService.validateOrder("dscendente");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testValidateAtribute01() throws Exception {
		String expected = "TITLE";
		String result = orderFileDataService.validateAttribute("title");
		Assert.assertEquals(expected, result);
	}

	@Test(expected = Exception.class)
	public void testValidateAtribute02() throws Exception {
		String expected = "TITLE";
		String result = orderFileDataService.validateAttribute("ttle");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testValidateAtribute03() throws Exception {
		String expected = "AUTHOR";
		String result = orderFileDataService.validateAttribute("autHor");
		Assert.assertEquals(expected, result);
	}

	@Test(expected = Exception.class)
	public void testValidateAtribute04() throws Exception {
		String expected = "AUTHOR";
		String result = orderFileDataService.validateAttribute("Autro");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testValidateAtribute05() throws Exception {
		String expected = "EDITION";
		String result = orderFileDataService.validateAttribute("EDItiOn");
		Assert.assertEquals(expected, result);
	}

	@Test (expected = Exception.class)
	public void testValidateAtribute06() throws Exception {
		String expected = "EDICAO";
		String result = orderFileDataService.validateAttribute("edtion");
		Assert.assertEquals(expected, result);
	}

	@Test
	public void testSortBy01() throws Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		books.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		books.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		books.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		Map<String, String> order = new LinkedHashMap<String, String>();
		order.put("title", "ascendent");
		List<Book> result = orderFileDataService.sortBy(books, order);
		
		String resultString = result.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		Assert.assertEquals(resultString, expectedString);
	}

	@Test
	public void testSortBy02() throws Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		books.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		books.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		books.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));

		Map<String, String> order = new LinkedHashMap<String, String>();
		order.put("author", "ascendent");
		order.put("title", "descendent");
		List<Book> result = orderFileDataService.sortBy(books, order);
		
		String resultString = result.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		Assert.assertEquals(resultString, expectedString);
	}
	
	@Test
	public void testSortBy03() throws Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		books.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		books.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		books.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));

		Map<String, String> order = new LinkedHashMap<String, String>();
		order.put("edition", "descendent");
		order.put("author", "descendent");
		order.put("title", "ascendent");
		
		List<Book> result = orderFileDataService.sortBy(books, order);
		
		String resultString = result.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		Assert.assertEquals(resultString, expectedString);
	}
	
	@Test
	public void testSortBy04(){
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		
		List<Book> result = new ArrayList<Book>();
		result.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		result.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		result.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		result.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));

		String resultString = result.stream().sorted(
                comparing(Book::getAuthor)
                .thenComparing(comparing(Book::getTitle).reversed()))
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream().map(Book::toString).collect(Collectors.joining());
		
		Assert.assertTrue(expectedString.equals(resultString));
		
	}
	
	@Test
	public void testSortBy05(){
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		
		List<Book> result = new ArrayList<Book>();
		result.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		result.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		result.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		result.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));

		String resultString = result.stream().sorted(
                comparing(Book::getAuthor)
                .thenComparing(comparing(Book::getTitle)))
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream().map(Book::toString).collect(Collectors.joining());
		
		Assert.assertTrue(expectedString.equals(resultString));
		
	}
	
	@Test
	public void testSortBy06() throws SortingServiceException{
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		books.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		books.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		books.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));

		Map<String, String> order = new LinkedHashMap<String, String>();
		
		List<Book> result = orderFileDataService.sortBy(books, order);
		
		Assert.assertTrue(result.isEmpty());
		
	}
	
	@Test(expected = SortingServiceException.class)
	public void testSortBy07() throws SortingServiceException{
		List<Book> expected = new ArrayList<Book>();
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		books.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		books.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		books.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));

		Map<String, String> order = null;
		
		List<Book> result = orderFileDataService.sortBy(books, order);
		
		String resultString = result.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		Assert.assertEquals(resultString, expectedString);
		
	}

}
