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

	public File findFile(String csvFileName) throws Exception {
		try {
			return Paths.get(ClassLoader.getSystemResource(csvFileName + ".csv").toURI()).toFile();
		} catch (Exception e) {
			throw new Exception("Cannot find file with the name:" + csvFileName + ".csv");
		}
	}
}
