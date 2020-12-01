import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.LinkedList;
public class DealFinder {

	private static final String URL = "https://dealsea.com/";
	private static final String RESULT_SELECTOR = "div.dealcontent > strong > a";
	private static DealsPopUp popUp;
	static ArrayList<String> resultText = new ArrayList<String>();
	
	public static void main(String[] args) throws InterruptedException {
		// Read console input
		UserInput search = new UserInput();
		String[] keyWord = search.getKeyWord();
		ArrayList<ArrayList<String>> search_results = new ArrayList<ArrayList<String>>();
		ArrayList<String> parsedData = new ArrayList<String>();
		int intervalMinutes = search.getInterval();
		long intervalMillis = intervalMinutes * 60 * 1000;
		
		while (true) {
			long startTime = System.currentTimeMillis();
			// Get search results
			for (int i = 0; i < keyWord.length; i ++) {
			WebAddressParser(keyWord[i]);
			}
			// Display Pop-up with search results
			if (popUp == null || !popUp.isDisplayable()) {
				popUp = new DealsPopUp();
			}
			popUp.displayItems(resultText);
			search_results.removeAll(search_results);
			long timeElapsed = System.currentTimeMillis() - startTime;
			Thread.sleep(intervalMillis - timeElapsed);
		}
	}
	public static ArrayList<String> WebAddressParser(String keyword) {
		// Get elements from url matching selector
		Document document = parseUrl(URL);
		ArrayList<Element> results = getItems(document, RESULT_SELECTOR);

		// Get text from elements matching keyword
		for (Element element : results) {
			String elementText = element.text();
			if (matchedSearch(keyword, elementText) && !resultText.contains(elementText)) {
				resultText.add(elementText);
			}
		}
		return resultText;
	}

	private static Document parseUrl(String s) {
		try {
			return Jsoup.connect(s).get();
		} catch (Exception e){
			System.err.println(e.getLocalizedMessage());
			return null;
		}
	}

	private static Elements getItems(Document document, String selector) {
		if (document == null)
			return new Elements();
		else
			return document.select(selector);
	}

	private static boolean matchedSearch(String searchQuery, String searchResult) {
		String query = searchQuery.toLowerCase();
		String result = searchResult.toLowerCase();
		return result.contains(query);
	}
}
