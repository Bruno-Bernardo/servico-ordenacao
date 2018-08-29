package com.bernardo.servico.ordenacao.model;

import org.junit.Assert;
import org.junit.Test;

public class testBook {

	@Test
	public void testToString() {
		String expected = "Titulo: Titulo1, Autor: Autor1, Edicao: Edicao1;";
		Book book = new Book("Titulo1","Autor1","Edicao1");
		String result = book.toString();
		Assert.assertEquals(expected, result);
	}

}
