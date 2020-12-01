import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DealsPanel extends JFrame {
	JPanel popUpPanel = new JPanel();

	public DealsPanel(){
		setMinimumSize(new Dimension(600, 475));
		popUpPanel.setBackground(Color.RED);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popUpPanel.setLayout(new BoxLayout(popUpPanel, BoxLayout.Y_AXIS));
		add(popUpPanel);
		setVisible(true);
	}

	public void displayItems(ArrayList<String> parsedData){
		if (parsedData.size() == 0) {
			System.out.println("No results were found");
			this.popUpPanel.add(new JLabel("No results were found"));
		}
		else {
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
