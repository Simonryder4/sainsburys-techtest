package uk.co.usryders.sainsburys.techtest.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import uk.co.usryders.sainsburys.techtest.domain.Result;
import uk.co.usryders.sainsburys.techtest.gateway.MockPageReader;
import uk.co.usryders.sainsburys.techtest.gateway.PageReader;

public class ResultExtractorTest {

	private static final String PRODUCT = "Product";
	Element element;

	private ResultExtractor unitUnderTest = new ResultExtractor();

	@Before
	public void setUp() throws Exception {
		//Use a file representation of the web page 
		URL url = this.getClass().getResource("/html/One Product.html");
		File file = new File(url.toURI());
		Document doc = Jsoup.parse(file, "UTF-8");
		Elements elementsContainingText = doc.getElementsByClass(PRODUCT);
		element = elementsContainingText.first();

		// Mock the page reader so the product page can also be read from a test file
		PageReader mockPageReader = new MockPageReader("/html/Product page.html");
		Class<?> c = unitUnderTest.getClass();
		Field f = c.getDeclaredField("pageReader");
		f.setAccessible(true);
		f.set(unitUnderTest, mockPageReader);
	}

	@Test
	public void extractTitle() throws IOException {
		Result result = unitUnderTest.extractResult(element);
		assertEquals(" Sainsbury's Apricot Ripe & Ready x5 ", result.getTitle());

	}

	@Test
	public void extractUnitPrice() throws IOException {
		Result result = unitUnderTest.extractResult(element);
		assertEquals(3.50, result.getUnitPrice().doubleValue(), 0);

	}

	@Test
	public void extractDescription() throws IOException {
		Result result = unitUnderTest.extractResult(element);
		assertEquals(
				"Buy Sainsbury's Apricot Ripe & Ready x5 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.",
				result.getDescription());

	}

	@Test
	public void extractSize() throws IOException {
		Result result = unitUnderTest.extractResult(element);
		assertEquals("38.3kb", result.getSize());

	}

}
