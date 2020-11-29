import java.util.ArrayList;
import java.util.List;

public class DealFinder {

	private static final String URL = "https://dealsea.com/";
	private static final String RESULT_SELECTOR = "div.dealcontent > strong > a";
	private static DealsPopUp popUp;

	public static void main(String[] args) throws InterruptedException {
		// Read console input
		UserInput search = new UserInput();
		String[] keyWord = search.getKeyWord();
		ArrayList<ArrayList<String>> search_results = new ArrayList<ArrayList<String>>();
		int intervalMinutes = search.getInterval();
		long intervalMillis = intervalMinutes * 60 * 1000;
		
		while (true) {
			
			long startTime = System.currentTimeMillis();
			// Get search results
			for (int i = 0; i < keyWord.length; i ++) {
			WebAddressParser parser = new WebAddressParser(URL, RESULT_SELECTOR, keyWord[i]);
			if (!parser.getData().isEmpty() && !search_results.contains(parser.getData())) {
			search_results.add((parser.getData()));
			}
			}
			// Display search results
			if (popUp == null || !popUp.isDisplayable()) {
				popUp = new DealsPopUp();
			}
			popUp.displayItems(search_results);
			search_results.removeAll(search_results);
			long timeElapsed = System.currentTimeMillis() - startTime;
			Thread.sleep(intervalMillis - timeElapsed);
		}
	}
}
