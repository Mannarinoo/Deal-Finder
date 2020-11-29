import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WebAddressParser {

	private ArrayList<String> parsedData;

	public WebAddressParser(String url, String selector, String keyword) {
		// Get elements from url matching selector
		Document document = parseUrl(url);
		ArrayList<Element> results = getItems(document, selector);

		// Get text from elements matching keyword
		ArrayList<String> resultText = new ArrayList<String>();
		for (Element element : results) {
			String elementText = element.text();
			if (matchedSearch(keyword, elementText)) {
				resultText.add(elementText);
			}
		}
		this.parsedData = resultText;
	}

	public ArrayList<String> getData() {
		return this.parsedData;
	}

	private Document parseUrl(String s) {
		try {
			return Jsoup.connect(s).get();
		} catch (Exception e){
			System.err.println(e.getLocalizedMessage());
			return null;
		}
	}

	private Elements getItems(Document document, String selector) {
		if (document == null)
			return new Elements();
		else
			return document.select(selector);
	}

	private boolean matchedSearch(String searchQuery, String searchResult) {
		String query = searchQuery.toLowerCase();
		String result = searchResult.toLowerCase();
		return result.contains(query);
	}
}
