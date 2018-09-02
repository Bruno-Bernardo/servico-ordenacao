package com.bernardo.servico.ordenacao.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.bernardo.servico.ordenacao.model.Book;

/**
 * @author bbbru
 *
 */
public class ParseFileService {

	/**
	 * Returns a list of books that haves in the csv file, witch must be inside the resources folder of the project.
	 * The CSV file must contain three columns, Title, Author and Edition.
	 * 
	 * @param csvFileName
	 * @return List<Book>
	 * @throws Exception
	 */
	public List<Book> getParseCSV(String csvFileName) throws Exception {

		String line = "";
		String cvsSplitBy = ",";
		List<Book> result = new ArrayList<Book>();

		try (BufferedReader br = new BufferedReader(new FileReader(findFile(csvFileName)))) {
			while ((line = br.readLine()) != null) {
				String[] texto = line.split(cvsSplitBy);
				result.add(new Book(texto[0], texto[1], Integer.parseInt(texto[2])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param csvFileName
	 * @return CSV File
	 * @throws Exception
	 */
	public File findFile(String csvFileName) throws Exception {
		csvFileName = validateCsvFileName(csvFileName);
		try {
			return Paths.get(ClassLoader.getSystemResource(csvFileName).toURI()).toFile();
		} catch (Exception e) {
			throw new Exception("Cannot find file with the name:" + csvFileName);
		}
	}
	
	/**
	 * Validates if the CSV file haves extension and return the correct name text 
	 * @param csvFileName
	 * @return String
	 * @throws Exception
	 */
	public String validateCsvFileName(String csvFileName) throws Exception{
		if (csvFileName != null && !csvFileName.isEmpty()) {
			if (csvFileName.toUpperCase().contains(".CSV")) {
				return csvFileName;
			}else{
				return csvFileName+".csv";
			}
		}
		throw new Exception("CSV file name cannot be null or empty");
	}
}
