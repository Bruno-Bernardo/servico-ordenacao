package com.bernardo.servico.ordenacao.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
		System.out.println("result:");
		List<Book> result = parseFileService.getParseCSV("books").stream().map(e -> new Book(e.getTitulo(), e.getAutor(), e.getEdicao())).collect(Collectors.toCollection(ArrayList::new));
		result.forEach(System.out::println);
		System.out.println("expected:");
		expected.forEach(System.out::println);
		
		expected.forEach(p -> {
		    Book book = result.stream().filter(
		        pe -> pe.getTitulo().equals(p.getTitulo())
		    ).findAny().orElseThrow(() -> new AssertionError("Product: " + p.getTitulo() + " not found!"));
		    Assert.assertEquals(p.getTitulo(), book.getTitulo());
		}); 
	}
	// TODO a comparação do resultado que não funciona

	@Test
	public void testGetParseCSV02() throws Exception {
		List<Book> expected = new ArrayList<Book>();
		expected.add(new Book("Java How to Program", "Deitel & Deitel", 2007));
		expected.add(new Book("Patterns of Enterprise Application Architecture", "Martin Fowler", 2002));
		expected.add(new Book("Head First Design Patterns", "Elisabeth Freeman", 2004));
		expected.add(new Book("Internet & World Wide Web: How to Program", "Deitel & Deitel", 2007));
		List<Book> result = parseFileService.getParseCSV("books").stream().map(e -> new Book(e.getTitulo(), e.getAutor(), e.getEdicao())).collect(Collectors.toCollection(ArrayList::new));
		
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
