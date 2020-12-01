import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DealsPopUp extends JFrame {
	JPanel popUpPanel = new JPanel();

	public DealsPopUp(){
		setMinimumSize(new Dimension(600, 375));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUpPanel.setLayout(new BoxLayout(popUpPanel, BoxLayout.Y_AXIS));
		popUpPanel.setBackground(Color.RED);
		add(popUpPanel);
		setVisible(true);
	}

	public void displayItems(ArrayList<String> parsedData){
		if (parsedData.size() == 0) {
			System.out.println("No results found");
			this.popUpPanel.add(new JLabel("No results found"));
		} else {
			this.popUpPanel.removeAll();
			JLabel header = new JLabel("Deals found on www.dealsea.com: ");
			this.popUpPanel.add(header);
			System.out.println("Found " + parsedData.size() + " results");
			int count = 0;
			for (String item : parsedData) {
				count++;
				JLabel currItem = new JLabel(count + ".  " + item);
				JLabel space = new JLabel(" ");
				this.popUpPanel.add(space);
				currItem.setFont(new Font("Verdana", Font.PLAIN, 18));
				this.popUpPanel.add(currItem);
				
			}
		}
		pack();
	}
}
