package uk.co.usryders.sainsburys.techtest;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.usryders.sainsburys.techtest.domain.Results;
import uk.co.usryders.sainsburys.techtest.gateway.WebPageReader;
import uk.co.usryders.sainsburys.techtest.service.ResultsBuilder;

/**
 * The runner for the Sainsbury's Technical test
 *
 */
public class TechTestRunner {
	private static final Logger logger = LoggerFactory.getLogger(TechTestRunner.class);
	private static final String HIRING_TESTS_S3_WEBSITE = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

	public static void main(String[] args) {
		try {
			// Get the results as json and write to the console
			System.out.println(new TechTestRunner().getResultsAsJson());
		} catch (Exception e) {
			logger.error("Failed to run the technical test", e);
		}
	}

	/**
	 * Get the product details from the web page as json
	 * 
	 * @return
	 * @throws IOException
	 */
	String getResultsAsJson() throws IOException {
		// Get the web page as a jsoup document
		Document document = new WebPageReader().getAsJsoupDocument(HIRING_TESTS_S3_WEBSITE);

		// Extract the results data from the document
		// and build the desired results structure
		Results results = new ResultsBuilder().getResults(document);

		// Convert the results to 'pretty' JSON
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(results);
	}

}
