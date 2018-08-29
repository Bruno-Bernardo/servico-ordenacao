package com.bernardo.servico.ordenacao.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

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
    
    @Test(expected = Exception.class)
    public void testOrderBy01() throws Exception {
        List<Book> expected = new ArrayList<Book>();
        expected.add(new Book("Titulo01","Autor04","Edicao08")); 
        expected.add(new Book("Titulo04","Autor05","Edicao09"));
        expected.add(new Book("Titulo02","Autor06","Edicao10"));
        expected.add(new Book("Titulo03","Autor07","Edicao11"));
        Map<String,String> order = new HashMap<String,String>();
        order.put("Autor", "DESC");
        List<Book> result = orderFileDataService.orderBy(order);
        Assert.assertEquals(expected, result);
    }

}
