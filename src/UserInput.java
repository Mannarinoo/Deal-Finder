import java.util.Scanner;

public class UserInput {

    private int frequency;
    private String[] keyWord;
    private Scanner scanner = new Scanner(System.in);

    public UserInput(){

        System.out.println("Enter item(s) you want to search for! (space delimited)");
        keyWord = scanner.nextLine().split(" ");
        System.out.println("How often would you like to check for deals? (min)");
        System.out.println("Please enter a number greater than 30");
        frequency = scanner.nextInt();

        System.out.println("Item(s) Searched:");
        for (int i = 0; i < keyWord.length; i++) {
        	System.out.println(keyWord[i]);
        }
        System.out.println("Frequency of Search: " + frequency +" min");
        scanner.close();
    }

    public int getInterval() {
        return this.frequency;
    }

    public String[] getKeyWord() {
        return this.keyWord;
    }

}
