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

	public void displayItems(ArrayList<ArrayList<String>> items){
		if (items.size() == 0) {
			System.out.println("No results found");
			this.popUpPanel.add(new JLabel("No results found"));
		} else {
			this.popUpPanel.removeAll();
			JLabel header = new JLabel("Deals found on www.dealsea.com: ");
			this.popUpPanel.add(header);
			System.out.println("Displaying " + items.size() + " results");
			int count = 0;
			for (ArrayList<String> list : items) {
				for (String item: list) {
				count++;
				JLabel currItem = new JLabel(count + ".  " + item);
				JLabel space = new JLabel(" ");
				this.popUpPanel.add(space);
				currItem.setFont(new Font("Verdana", Font.PLAIN, 18));
				this.popUpPanel.add(currItem);
				
				}
			}
		}
		pack();
	}
}
