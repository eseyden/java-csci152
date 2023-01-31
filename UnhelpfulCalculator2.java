/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #1
 * Problem 3
 * (UnhelpfulCalculator2.class)
 * -------------------------------------------------------------------------------
 * This program finds the product of two characters
 * and elaborates on why their product is 3021
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-01-30
 * -------------------------------------------------------------------------------
 */
public class UnhelpfulCalculator2 {
    public static void main(String[] args) {
        // Declare two characters
        char first_number = '9';
        char second_number = '5';

        //find their product
        int product = first_number * second_number;

        //Assemble string output
        String output = first_number + " * " + second_number + " = ";
        //Cast output strings to int to illustrate product calculation
        output += (int)(first_number) + " * " + (int)(second_number);

        output += " = " + product;
        System.out.println(output);
    }
}
