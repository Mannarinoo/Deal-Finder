import java.util.Scanner;

public class UserInput {

    private int frequency;
    private String[] keyWord;
    private Scanner scanner = new Scanner(System.in);

    UserInput(){
        //Input
        System.out.println("Enter item(s) you want to search for! (space delimited)");
        keyWord = scanner.nextLine().split(" ");
        System.out.println("How often would you like to check for deals on:");
        for (int i = 0; i < keyWord.length; i++) {
        	System.out.println(keyWord[i] + " ");
        }
        System.out.println("Please enter a time greater than 30 min!");
        frequency = scanner.nextInt();

        //Output
        System.out.println("Item(s) Searched:");
        for (int i = 0; i < keyWord.length; i++) {
        	System.out.println(keyWord[i]);
        }
        System.out.println("Frequency of Search: " + frequency +" min(s)");
        scanner.close();
    }

    int getInterval() {
        return this.frequency;
    }

    String[] getKeyWord() {
        return this.keyWord;
    }

}
