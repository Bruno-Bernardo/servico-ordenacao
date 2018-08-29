package com.bernardo.servico.ordenacao.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.bernardo.servico.ordenacao.model.Book;
import com.bernardo.servico.ordenacao.service.OrderFileDataService;

/**
 * @author bbbru
 *
 */
public class TestOrderFileDataService {

	private final OrderFileDataService orderFileDataService = new OrderFileDataService();

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
    public void testvalidateFilter01() throws Exception {
        String expected = "TITULO";
        String result = orderFileDataService.validateFilter("titulo");
        Assert.assertEquals(expected, result);
    }
    
    @Test(expected = Exception.class)
    public void testvalidateFilter02() throws Exception {
        String expected = "TITULO";
        String result = orderFileDataService.validateFilter("ttulo");
        Assert.assertEquals(expected, result);
    }
    
    @Test
    public void testvalidateFilter03() throws Exception {
        String expected = "AUTOR";
        String result = orderFileDataService.validateFilter("aUtOr");
        Assert.assertEquals(expected, result);
    }
    
    @Test(expected = Exception.class)
    public void testvalidateFilter04() throws Exception {
        String expected = "AUTOR";
        String result = orderFileDataService.validateFilter("Autro");
        Assert.assertEquals(expected, result);
    }
    
    @Test
    public void testvalidateFilter05() throws Exception {
        String expected = "EDICAO";
        String result = orderFileDataService.validateFilter("EDIÇÃO");
        Assert.assertEquals(expected, result);
    }
    
    @Test(expected = Exception.class)
    public void testvalidateFilter06() throws Exception {
        String expected = "EDICAO";
        String result = orderFileDataService.validateFilter("edcao");
        Assert.assertEquals(expected, result);
    }
    
    @Test
    public void testOrderBy01() throws Exception {
        List<Book> expected = new ArrayList<Book>();
        expected.add(new Book("Head First Design Patterns","Elisabeth Freeman",2004));
        expected.add(new Book("Internet & World Wide Web: How to Program","Deitel & Deitel",2007));
        expected.add(new Book("Java How to Program","Deitel & Deitel",2007)); 
        expected.add(new Book("Patterns of Enterprise Application Architecture","Martin Fowler",2002));
        List<Book> books = new ArrayList<Book>();
        expected.add(new Book("Java How to Program","Deitel & Deitel",2007)); 
        expected.add(new Book("Patterns of Enterprise Application Architecture","Martin Fowler",2002));
        expected.add(new Book("Head First Design Patterns","Elisabeth Freeman",2004));
        expected.add(new Book("Internet & World Wide Web: How to Program","Deitel & Deitel",2007));
        Map<String,String> order = new HashMap<String,String>();
        order.put("título", "ascendente");
        List<Book> result = orderFileDataService.orderBy(books,order);
        Assert.assertEquals(expected, result);
    }
    
    @Test
    public void testOrderBy02() throws Exception {
        List<Book> expected = new ArrayList<Book>();
        expected.add(new Book("Head First Design Patterns","Elisabeth Freeman",2004));
        expected.add(new Book("Internet & World Wide Web: How to Program","Deitel & Deitel",2007));
        expected.add(new Book("Java How to Program","Deitel & Deitel",2007)); 
        expected.add(new Book("Patterns of Enterprise Application Architecture","Martin Fowler",2002));
        List<Book> books = new ArrayList<Book>();
        expected.add(new Book("Java How to Program","Deitel & Deitel",2007)); 
        expected.add(new Book("Patterns of Enterprise Application Architecture","Martin Fowler",2002));
        expected.add(new Book("Head First Design Patterns","Elisabeth Freeman",2004));
        expected.add(new Book("Internet & World Wide Web: How to Program","Deitel & Deitel",2007));
        Map<String,String> order = new HashMap<String,String>();
        order.put("título", "ascendente");
        List<Book> result = orderFileDataService.orderBy(books,order);
        Assert.assertEquals(expected, result);
    }

}
