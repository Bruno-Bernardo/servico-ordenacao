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
	 *            attribute to be validated
	 * @return String attribute validated: TITLE, AUTHOR or EDITION
	 * @throws Exception
	 *             when parameter is null, empty or not contains TITLE, AUTHOR or
	 *             EDITION
	 */
	public String validateAttribute(String attribute) throws SortingServiceException {
		attribute = Normalizer.normalize(attribute, Normalizer.Form.NFD);
		attribute = attribute.replaceAll("[^\\p{ASCII}]", "");
		if (attribute != null && !attribute.isEmpty()) {
			if (attribute.toUpperCase().contains("TITLE")) {
				return "TITLE";
			} else if (attribute.toUpperCase().contains("AUTHOR")) {
				return "AUTHOR";
			} else if (attribute.toUpperCase().contains("EDITION")) {
				return "EDITION";
			}
		}
		throw new SortingServiceException(
				"Invalid attribute parameter. The parameter must contain: TITLE, AUTHOR or EDITION");
	}


	/**
	 * Returns a List<Book> with the objects in the order that was defined by the
	 * parameter "orderByAttributeList"
	 * 
	 * @param orderByAttribute
	 * @return List<Book>
	 */
	public List<Book> sortBy(List<Book> booksList, Map<String, String> orderByAttributeList)
			throws SortingServiceException {
		Comparator<Book> comparator = createComparator(orderByAttributeList);
		if(comparator != null) {
			return booksList.stream().sorted(comparator)
				.collect(Collectors.toCollection(ArrayList::new));
		}
		return new ArrayList<Book>();
	}

	/**
	 * Uses a Map<String,String> orderByAttributeList to return Comparator<Book>, where
	 * the key value must contain TITLE, AUTHOR or EDITION indicating the attribute
	 * to be filtered and the value must contain ASC or DESC indicating the order of
	 * the attribute.
	 * 
	 * @return Comparator<Book>
	 */
	public Comparator<Book> createComparator(Map<String, String> orderByAttributeList) throws SortingServiceException{
		Comparator<Book> orderByTitle = Comparator.comparing(Book::getTitle);
		Comparator<Book> orderByAutor = Comparator.comparing(Book::getAuthor);
		Comparator<Book> orderByEdition = Comparator.comparing(Book::getEdition);
		Comparator<Book> comparator = null;
		int i = 0;
		if (orderByAttributeList != null && !orderByAttributeList.isEmpty()) {
			for (Map.Entry<String, String> orderByAttribute : orderByAttributeList.entrySet()) {
				try {
					String validatedOrder = validateOrder(orderByAttribute.getValue());
					String validatedAttribute = validateAttribute(orderByAttribute.getKey());
					if (i == 0) {
						if (validatedOrder.equals("ASC")) {
							if (validatedAttribute.equals("TITLE")) {
								comparator = orderByTitle;
							} else if (validatedAttribute.equals("AUTHOR")) {
								comparator = orderByAutor;
							} else if (validatedAttribute.equals("EDITION")) {
								comparator = orderByEdition;
							}
						} else if (validatedOrder.equals("DESC")) {
							if (validatedAttribute.equals("TITLE")) {
								comparator = orderByTitle.reversed();
							} else if (validatedAttribute.equals("AUTHOR")) {
								comparator = orderByAutor.reversed();
							} else if (validatedAttribute.equals("EDITION")) {
								comparator = orderByEdition.reversed();
							}
						}
					} else {
						if (validatedOrder.equals("ASC")) {
							if (validatedAttribute.equals("TITLE")) {
								comparator = comparator.thenComparing(orderByTitle);
							} else if (validatedAttribute.equals("AUTHOR")) {
								comparator = comparator.thenComparing(orderByAutor);
							} else if (validatedAttribute.equals("EDITION")) {
								comparator = comparator.thenComparing(orderByEdition);
							}
						} else if (validatedOrder.equals("DESC")) {
							if (validatedAttribute.equals("TITLE")) {
								comparator = comparator.thenComparing(orderByTitle.reversed());
							} else if (validatedAttribute.equals("AUTHOR")) {
								comparator = comparator.thenComparing(orderByAutor.reversed());
							} else if (validatedAttribute.equals("EDITION")) {
								comparator = comparator.thenComparing(orderByEdition.reversed());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				i++;
			}
			return comparator;
		}else if(orderByAttributeList != null && orderByAttributeList.isEmpty()) {
			return null;
		}
		throw new SortingServiceException("The order cannot be null");
	}
}
