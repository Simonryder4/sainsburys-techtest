package uk.co.usryders.sainsburys.techtest.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import uk.co.usryders.sainsburys.techtest.domain.Results;

public class ResultsBuilderTest {
	private ResultsBuilder unitUnderTest = new ResultsBuilder();

	private Document document;

	@Before
	public void setUp() throws IOException, URISyntaxException {
		//Use a file representation of the web page 
		URL url = this.getClass().getResource("/html/One Product.html");
		File file = new File(url.toURI());
		document = Jsoup.parse(file, "UTF-8");
	}

	@Test
	public void getResultsReturnsAResult() throws IOException {
		// Extract the results data from the document
		// and build the desired results structure
		Results results = unitUnderTest.getResults(document);
		assertEquals(1, results.getResults().size());
		assertEquals(" Sainsbury's Apricot Ripe & Ready x5 ", results.getResults().get(0).getTitle());
	}

	@Test
	public void getResultsReturnsTheTotal() throws IOException {
		// Extract the results data from the document
		// and build the desired results structure
		Results results = unitUnderTest.getResults(document);
		assertEquals(3.50, results.getTotal().doubleValue(), 0);
	}

}
