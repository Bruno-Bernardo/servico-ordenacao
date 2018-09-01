package com.bernardo.servico.ordenacao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.bernardo.servico.ordenacao.model.Book;

public class TestParseFileService {

	private final ParseFileService parseFileService = new ParseFileService();

	@Test
	public void testGetParseCSV01() throws Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		List<Book> result = parseFileService.getParseCSV("books").stream().map(e -> new Book(e.getTitle(), e.getAuthor(), e.getEdition())).collect(Collectors.toCollection(ArrayList::new));
		
		String resultString = result.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		String expectedString = expected.stream()
				.map(Book::toString)
				.collect(Collectors.joining());
		Assert.assertTrue(expectedString.equals(resultString));
	}
	// TODO a comparação do resultado que não funciona

	@Test
	public void testGetParseCSV02() throws Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		List<Book> result = parseFileService.getParseCSV("books").stream().map(e -> new Book(e.getTitle(), e.getAuthor(), e.getEdition())).collect(Collectors.toCollection(ArrayList::new));
		
		Assert.assertEquals(expected.size(), result.size());
	}
	
	@Test
	public void testGetParseCSV03() throws Exception {
		String csvFileName = "books";
		Assert.assertNotNull(parseFileService.getParseCSV(csvFileName));
	}

	@Test(expected = Exception.class)
	public void testGetParseCSV04() throws Exception {
		String csvFileName = "book";
		Assert.assertNotNull(parseFileService.getParseCSV(csvFileName));
	}

	@Test
	public void testGetParseCSV05() throws Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		List<Book> result = parseFileService.getParseCSV("books");
		
		List<String> expectedStringList = expected.stream().map(Object::toString).collect(Collectors.toCollection(ArrayList::new));
		List<String> resultStringList = result.stream().map(Object::toString).collect(Collectors.toCollection(ArrayList::new));
		
		Assert.assertTrue(expectedStringList.equals(resultStringList));
	}

	@Test(expected = Exception.class)
	public void testFindFile01() throws Exception {
		String csvFileName = "book";
		Assert.assertNotNull(parseFileService.findFile(csvFileName));
	}

	@Test
	public void testFindFile02() throws Exception {
		String csvFileName = "books";
		Assert.assertNotNull(parseFileService.findFile(csvFileName));
	}


}
