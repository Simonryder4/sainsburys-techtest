package uk.co.usryders.sainsburys.techtest.service;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import uk.co.usryders.sainsburys.techtest.domain.Results;

public class ResultsBuilder {

	private ResultExtractor resultExtractor = new ResultExtractor();
	private static final String PRODUCT = "product";

	/**
	 * Get the results containing details of all products on the page
	 * represented by the {@link Document}
	 * @param document the document representing the web page
	 * @return the {@link Results}
	 * @throws IOException
	 */
	public Results getResults(Document document) throws IOException {
		Results results = new Results();
		// Get all products of the web page
		Elements elementsContainingText = document.getElementsByClass(PRODUCT);

		// Extract a result object for each product
		// The resultsWrapper maintains the total price
		for (Element element : elementsContainingText) {
			results.addResult(resultExtractor.extractResult(element));
		}

		return results;
	}

}
