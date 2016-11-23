package uk.co.usryders.sainsburys.techtest.gateway;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MockPageReader implements PageReader {

	private String fileUrl;

	public MockPageReader(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * Get the web page indicated by the url as a jsoup {@link Document}
	 * 
	 * @param url
	 * @return a jsoup {@link Document} representation of the web page
	 * @throws IOException
	 */
	public Document getAsJsoupDocument(String url) throws IOException {
		URL mockurl = this.getClass().getResource(fileUrl);
		File file;
		try {
			file = new File(mockurl.toURI());
			return Jsoup.parse(file, "UTF-8");
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Gets the size in kb of the web page indicated by the url (excluding page
	 * assets)
	 * 
	 * @param url
	 * @return The size of the web page (excluding page assets)
	 * @throws IOException
	 */
	public String getWebpageSizeInKb(String url) throws IOException {
		return "38.3kb";
	}

}
