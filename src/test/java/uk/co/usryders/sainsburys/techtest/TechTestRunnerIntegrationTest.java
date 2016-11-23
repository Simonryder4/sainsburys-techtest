package uk.co.usryders.sainsburys.techtest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.usryders.sainsburys.techtest.domain.Results;

/**
 * Test accessing the actual web pages without any mocking
 * @author simon
 *
 */
public class TechTestRunnerIntegrationTest {

	private TechTestRunner systemUnderTest = new TechTestRunner();

	@Test
	public void getResults() throws IOException {
		String resultsAsJson = systemUnderTest.getResultsAsJson();

		// Rebuild the results object from the json.
		Results results = new ObjectMapper().readValue(resultsAsJson, Results.class);

		assertEquals(7, results.getResults().size());
		assertEquals(15.10, results.getTotal().doubleValue(),0);

	}
}
