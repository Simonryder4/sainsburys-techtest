package uk.co.usryders.sainsburys.techtest.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.usryders.sainsburys.techtest.domain.Result;
import uk.co.usryders.sainsburys.techtest.gateway.PageReader;
import uk.co.usryders.sainsburys.techtest.gateway.WebPageReader;

public class ResultExtractor {
	private static final Logger logger = LoggerFactory.getLogger(ResultExtractor.class);
	private PageReader pageReader = new WebPageReader();

	/**
	 * Extract a result containing details about the product from the {@link Element}
	 * that contains details of a product  
	 * @param element the {@link Element} that contains details of a product
	 * @return a {@link Result} with key details of the product
	 * @throws IOException
	 */
	public Result extractResult(Element element) throws IOException {

		Result result = new Result();

		// Get the title and unit price
		result.setTitle(getTitle(element));
		result.setUnitPrice(getUnitPrice(element));

		// Get the url for the products page, retrieve the page, and get the
		// description and page size from it.
		String url = getProductUrl(element);
		if (url != null) {
			Document productPage = pageReader.getAsJsoupDocument(url);
			result.setDescription(getDescription(productPage));
			result.setSize(pageReader.getWebpageSizeInKb(url));
		}

		return result;
	}

	private String getProductUrl(Element element) {
		try {
			Element a = element.getElementsByClass("productInfo").first().getElementsByTag("a").first();
			return a.attr("href");
		} catch (Exception e) {
			// If the title is not found, log the exception and return null
			// rather than fail.
			logger.error("Failed to find url", e);
			return null;
		}
	}

	private String getTitle(Element element) {
		try {
			Element a = element.getElementsByClass("productInfo").first().getElementsByTag("a").first();
			List<TextNode> textNodes = a.textNodes();
			return textNodes.get(0).text();
		} catch (Exception e) {
			// If the title is not found, log the exception and return null
			// rather than fail.
			logger.error("Failed to find title", e);
			return null;
		}
	}

	private BigDecimal getUnitPrice(Element element) {
		try {
			Element a = element.getElementsByClass("pricePerUnit").first();
			List<TextNode> textNodes = a.textNodes();
			String text = textNodes.get(0).text();
			String number = text.replaceAll("[&pound, ]", "");
			BigDecimal price = new BigDecimal(number);
			return price;
		} catch (Exception e) {
			// If the Unit Price is not found, log the exception and return null
			// rather than fail.
			logger.error("Failed to find Unit Price", e);
			return null;
		}
	}

	private String getDescription(Document document) {
		Elements elements = document.getElementsByTag("meta").attr("name", "description");
		for (Element element : elements) {
			Element attr = element.attr("name", "description");
			if (attr != null) {
				String description = element.attr("content");
				return description;
			}
		}
		return "Great to eat now - refrigerate at home, fresh fruit counts as 1 of your 5 a day";
	}

}
