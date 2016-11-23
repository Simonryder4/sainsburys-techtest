package uk.co.usryders.sainsburys.techtest.gateway;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class WebPageReaderTest {

	private static final String HIRING_TESTS_S3_WEBSITE = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
	private static final String INVALID_URL = "http://invalidurl.com/5_products.html";

	@Test
	public void testValidConnectionURLReturnsDom() throws IOException {
		PageReader webPageReader = new WebPageReader();
		Document document = webPageReader.getAsJsoupDocument(HIRING_TESTS_S3_WEBSITE);
		assertNotNull("Document not returned", document);
	}

	@Test(expected = IOException.class)
	public void testInValidURLThrowsException() throws IOException {
		PageReader webPageReader = new WebPageReader();
		webPageReader.getAsJsoupDocument(INVALID_URL);
	}

}
