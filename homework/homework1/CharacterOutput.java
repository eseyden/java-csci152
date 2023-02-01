/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #1
 * Problem 1
 * (CharacterOutput.java)
 * -------------------------------------------------------------------------------
 * This program demonstrates the underlying integer values of character data types
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-01-30
 * -------------------------------------------------------------------------------
 */
public class CharacterOutput {
    public static void main(String[] args) {

        // 1.1: A
        char A = 65;
        System.out.println(A);
        /*
            Characters are represented by ASCII values
            The ASCII standard uses the 8bit value of 65 as an A
            So in Java printing a char with an integer value of 65 results in "A"
         */

        // 1.2: B
        char B = A;
        B++;
        System.out.println(B);
        /*
            1.2 Increments the value using the type's increment operator,
            so it will stay the same type
         */

        // 1.3: B?
        System.out.println(A+1);
        /*
            1.3 Adds an integer to a char resulting in a integer output
         */

        // We can cast it back to char and get B if we want.
        System.out.println((char)(A+1));
    }
}
