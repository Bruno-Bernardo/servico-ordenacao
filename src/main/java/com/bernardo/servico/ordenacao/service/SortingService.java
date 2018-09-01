package com.bernardo.servico.ordenacao.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bernardo.servico.ordenacao.model.Book;

/**
 * @author bbbru
 *
 */
public class SortingService {

	/**
	 * Receive a String and validate if it contains ASC or DESC. It can be written
	 * with lowercase, uppercase, alternating between both or containing accents
	 * 
	 * @param String
	 *            order to be validated
	 * @return String order validated: ASC or DESC
	 * @throws Exception
	 *             when parameter is null, empty or not contains ASC or DESC
	 */
	public String validateOrder(String order) throws SortingServiceException {
		order = Normalizer.normalize(order, Normalizer.Form.NFD);
		order = order.replaceAll("[^\\p{ASCII}]", "");
		if (order != null && !order.isEmpty()) {
			if (order.toUpperCase().contains("ASC")) {
				return "ASC";
			} else if (order.toUpperCase().contains("DESC")) {
				return "DESC";
			}
		}
		throw new SortingServiceException("Invalid order parameter. The parameter must contain: ASC or DESC");
	}

	/**
	 * Receive a String and validate if it contains TITLE, AUTHOR or EDITION. It can
	 * be written with lowercase, uppercase, alternating between both or containing
	 * accents
	 * 
	 * @param String
	 *            filter to be validated
	 * @return String filter validated: TITLE, AUTHOR or EDITION
	 * @throws Exception
	 *             when parameter is null, empty or not contains TITLE, AUTHOR or
	 *             EDITION
	 */
	public String validateAtribute(String atribute) throws SortingServiceException {
		atribute = Normalizer.normalize(atribute, Normalizer.Form.NFD);
		atribute = atribute.replaceAll("[^\\p{ASCII}]", "");
		if (atribute != null && !atribute.isEmpty()) {
			if (atribute.toUpperCase().contains("TITLE")) {
				return "TITLE";
			} else if (atribute.toUpperCase().contains("AUTHOR")) {
				return "AUTHOR";
			} else if (atribute.toUpperCase().contains("EDITION")) {
				return "EDITION";
			}
		}
		throw new SortingServiceException(
				"Invalid atribute parameter. The parameter must contain: TITLE, AUTHOR or EDITION");
	}

	// validateOrderParam(String param); param = atributos dos livros

	/**
	 * Returns a List<Book> with the objects in the order that was defined by the
	 * parameter "orderByFilter"
	 * 
	 * @param orderByFilter
	 * @return List<Book>
	 */
	public List<Book> sortBy(List<Book> booksList, Map<String, String> orderByFilterList)
			throws SortingServiceException {
		Comparator<Book> comparator = createComparator(orderByFilterList);
		List<Book> booksListResult = new ArrayList<Book>();
		if(comparator != null) {
			booksListResult = booksList.stream().sorted()
				.collect(Collectors.toCollection(ArrayList::new));
		}
		return booksListResult;
	}

	/**
	 * Uses a Map<String,String> orderByFilterList to return Comparator<Book>, where
	 * the key value must contain TITLE, AUTHOR or EDITION indicating the attribute
	 * to be filtered and the value must contain ASC or DESC indicating the order of
	 * the attribute.
	 * 
	 * @return Comparator<Book>
	 */
	public Comparator<Book> createComparator(Map<String, String> orderByFilterList) throws SortingServiceException {
		Comparator<Book> orderByTitle = Comparator.comparing(Book::getTitle);
		Comparator<Book> orderByAutor = Comparator.comparing(Book::getAuthor);
		Comparator<Book> orderByEdicao = Comparator.comparing(Book::getEdition);
		Comparator<Book> comparator = null;
		int i = 0;
		if (orderByFilterList != null && !orderByFilterList.isEmpty()) {
			for (Map.Entry<String, String> orderByFilter : orderByFilterList.entrySet()) {
				try {
					String validatedOrder = validateOrder(orderByFilter.getValue());
					String validatedFilter = validateAtribute(orderByFilter.getKey());
					if (i == 0) {
						if (validatedOrder.equals("ASC")) {
							if (validatedFilter.equals("TITLE")) {
								comparator = orderByTitle;
							} else if (validatedFilter.equals("AUTHOR")) {
								comparator = orderByAutor;
							} else if (validatedFilter.equals("EDITION")) {
								comparator = orderByEdicao;
							}
						} else if (validatedOrder.equals("DESC")) {
							if (validatedFilter.equals("TITLE")) {
								comparator = orderByTitle.reversed();
							} else if (validatedFilter.equals("AUTHOR")) {
								comparator = orderByAutor.reversed();
							} else if (validatedFilter.equals("EDITION")) {
								comparator = orderByEdicao.reversed();
							}
						}
					} else {
						if (validatedOrder.equals("ASC")) {
							if (validatedFilter.equals("TITLE")) {
								comparator = comparator.thenComparing(orderByTitle);
							} else if (validatedFilter.equals("AUTHOR")) {
								comparator = comparator.thenComparing(orderByTitle);
							} else if (validatedFilter.equals("EDITION")) {
								comparator = comparator.thenComparing(orderByTitle);
							}
						} else if (validatedOrder.equals("DESC")) {
							if (validatedFilter.equals("TITLE")) {
								comparator = comparator.thenComparing(orderByTitle.reversed());
							} else if (validatedFilter.equals("AUTHOR")) {
								comparator = comparator.thenComparing(orderByAutor.reversed());
							} else if (validatedFilter.equals("EDITION")) {
								comparator = comparator.thenComparing(orderByEdicao.reversed());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				i++;
			}
			return comparator;
		}else if(orderByFilterList != null && orderByFilterList.isEmpty()) {
			return null;
		}
		throw new SortingServiceException("The order cannot be empty or null");
	}
}
