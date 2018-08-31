package com.bernardo.servico.ordenacao.model;

import org.junit.Assert;
import org.junit.Test;

public class TestBook {

	@Test
	public void testToString() {
		String expected = "Titulo: Titulo1, Autor: Autor1, Edicao: 2018;";
		Book book = new Book("Titulo1","Autor1",2018);
		String result = book.toString();
		Assert.assertEquals(expected, result);
	}

}
