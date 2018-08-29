package com.bernardo.servico.ordenacao.service;

import java.util.List;
import java.util.Map;

import com.bernardo.servico.ordenacao.model.Book;

/**
 * @author bbbru
 *
 */
public class OrderFileDataService {
	/**
     * descrição do metodo
     * @param order
     * @return a order validated: ASC or DESC
     * @throws Exception when param is null, empty or not contains asc or desc
     */
    public String validateOrder(String order) throws Exception {
        if (order != null && !order.isEmpty()) {
            if (order.toUpperCase().contains("ASC")) {
                return "ASC";
            } else if (order.toUpperCase().contains("DESC")) {
                return "DESC";
            }
        }
        throw new Exception("Invalid order param, excepted: ASC or DESC");
    }
    // validateOrderParam(String param); param = atributos dos livros
    
    /**
     * Returns a List<Book> with the objects in the order that was needed
     * @param orderByFilter
     * @return List<Book>
     */
    public List<Book> orderBy(Map<String,String> orderByFilter){ // o map define o campo e a ordem que devera ser seguida
    	
    	return null;//nao pode deixar null
    }
}
