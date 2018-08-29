package com.bernardo.servico.ordenacao.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
        throw new Exception("Invalid order parameter. The parameter must contain: ASC or DESC");
    }
    
    public String validateFilter(String filter) throws Exception {
    	filter = Normalizer.normalize(filter, Normalizer.Form.NFD);
    	filter = filter.replaceAll("[^\\p{ASCII}]", "");
    	if (filter != null && !filter.isEmpty()) {
            if (filter.toUpperCase().contains("TITULO")) {
                return "TITULO";
            } else if (filter.toUpperCase().contains("AUTOR")) {
                return "AUTOR";
            } else if (filter.toUpperCase().contains("EDICAO")) {
                return "EDICAO";
            }
        }
        throw new Exception("Invalid atribute parameter. The parameter must contain: TITULO, AUTOR or EDICAO");
    }
    
    // validateOrderParam(String param); param = atributos dos livros
    
    /**
     * Returns a List<Book> with the objects in the order that was needed
     * @param orderByFilter
     * @return List<Book>
     */
    public List<Book> orderBy(List<Book> books, Map<String,String> orderByFilter){ // o map define o campo e a ordem que devera ser seguida
    	Stream<Book> streamBooks = books.stream();
    	for(Map.Entry<String,String> sortOpition : orderByFilter.entrySet()) {
    		streamBooks = streamBooks.sorted();
    	}
    	
    	
    	
    	try {
    		books = (List<Book>) streamBooks;//TODO PROVEVELMENTE NÃO É A MELHOR MANEIRA DE SE FAZER A CONVERSÃO DO TIPO
    	}catch(ClassCastException e){
    		e.printStackTrace(); //TODO CRIAR NOVO EXCEPTION
    	}
    	return books;
    }
}
