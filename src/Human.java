import java.util.Scanner;

public class Human implements Player{
    public Human() {
    }

    public String get_action(State s) {
        Scanner scn = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter Row: ");
        int r = scn.nextInt();
        //char c = (char)((int)input.charAt(0))-97;

        
        System.out.print("Enter Column: ");
        String str = scn.nextLine();
        char ch = scn.next().charAt(0); 
        int ascii = (int) ch;
        int c = ascii - 97;
        return Integer.toString(r) + Integer.toString(c);
    }
}
