/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #1
 * Problem 2
 * (UnhelpfulCalculator.java)
 * -------------------------------------------------------------------------------
 * This program finds the product of two characters
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-01-30
 * -------------------------------------------------------------------------------
 */
public class UnhelpfulCalculator {
    /**
     * Run an unhelpful calculation
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        char first_number = '9';
        char second_number = '5';
        int product = first_number * second_number; //
        String output = "9 * 5 = " + product;
        System.out.println(output);
    }
}
