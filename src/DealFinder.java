import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.util.ArrayList;
import java.util.LinkedList;
public class DealFinder {

	private static final String URL = "https://dealsea.com/";
	private static final String Deals_Content = "div.dealcontent > strong > a";
	private static DealsPanel popUp;
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
				popUp = new DealsPanel();
			}
			popUp.displayItems(resultText);
			search_results.removeAll(search_results);
			long timeElapsed = System.currentTimeMillis() - startTime;
			Thread.sleep(intervalMillis - timeElapsed);
		}
	}
	public static ArrayList<String> WebAddressParser(String keyword) {
		// Get elements from url matching selector (deals content)
		Document document = new Document(keyword);
		try {
			document = Jsoup.connect(URL).get();
		} catch (Exception e){
			System.err.println(e.getLocalizedMessage());
		}
		ArrayList<Element> results = new ArrayList<Element>();
		if (document == null) {
			results = new Elements();
		}else {
			results = document.select(Deals_Content);
		}
		// Search for elements that contain DealsContent and keywords. The search is case insensitive
		for (Element element : results) {
			String elementText = element.text();
			keyword = keyword.toLowerCase();
			elementText = elementText.toLowerCase();
			if (elementText.contains(keyword) && !resultText.contains(elementText)) {
				resultText.add(elementText);
			}
		}
		return resultText;
	}
}
