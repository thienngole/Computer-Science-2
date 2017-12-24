package assignment_04;

import java.util.Scanner;

/**
 * This program uses a stack to test whether an input string is a palindrome. 
 * In this main class will include a main method that will ask user for input
 * string, check the string for being a palindrome, and display the result.
 *
 * @author ThienNgo N. Le
 * @version 20161024
 */
public class TLeHW4 {

    // Default the size of the input string
    private static final int SIZE = 10;

    /**
     * This is main method. This method will: 
     * 1. Ask user for input a string
     * (word, phrase, or sentences) with given size 
     * 2. Check whether the input string is out of range or not. If the input 
     * string is out of range, exit the program.
     * 3. Check whether the input string is empty or not. If the input string 
     * is empty, exit the program.
     * 4. Check the string for being a palindrome 
     * 5. Display the result.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Stack stack = new Stack(SIZE);

        String string = "";
        String rvsString = "";

        Scanner scnr = new Scanner(System.in);

        System.out.println("Please enter a word, phrase, or sentences less than "
                + SIZE + " cheracters" + "\nPress Enter when you finish.");

        string = scnr.nextLine();

        if (string.length() > SIZE) 
        {

            System.err.println("ERROR!!! \nYour input is out of range.\n"
                    + "The maximun size for your input is " + SIZE + " chracters."
                    + "\nThe program is ended.");

            System.exit(0);

        } 
        
        else {
            
            string = string.replaceAll("\\s", "");
            
            if (string.length() <= 0) 
            {
                
            System.err.println("ERROR!!! \nYour input is empty\n"
                    + "The program is ended");

            System.exit(0);
            }

        }

        string = string.toLowerCase();  //Convert all letters to lower case
        string = string.replaceAll("\\s", "");  //Clear all white space

        for (int i = 0; i < string.length(); i++) 
        {

            stack.push(string.charAt(i));
        }

        while (!stack.isEmpty()) 
        {

            rvsString += stack.pop();
        }

        if (string.equals(rvsString)) 
        {

            System.out.println("This is a palindrome.");
        } 
        
        else 
        {

            System.out.println("This is not a palindrome.");
        }

        scnr.close();

    }

}
