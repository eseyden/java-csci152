/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #2
 * Problem 1
 * (Conditional.java)
 * -------------------------------------------------------------------------------
 * This program examines why a conditional structure
 * is best for a given application
 *
 * Q/A: I prefer not nested if possible
 * If it is nested I pull the complexity of deeper nesting
 * out into functions that can be tested independently.
 *
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-02-01
 * -------------------------------------------------------------------------------
 */
public class Conditional {
    public static void main(String[] args) {

        // Logic block one's output can be changed
        // by only setting one condition to true
        boolean condition_1 = false;
        boolean condition_2 = true;
        boolean condition_3 = false;

        boolean condition_4 = false;
        // The output of conditional block 2 with just condition 5 set as true
        // is the same as if all conditions for this block are false.
        boolean condition_5 = true;
        boolean condition_6 = false;

        // 4 possible outputs
        if (condition_1) {
            System.out.println("output A");
        } else if (condition_2) {
            System.out.println("output B");
        } else if (condition_3) {
            System.out.println("output C");
        } else {
            System.out.println("output D");
        }

        // Nested conditionals are difficult to read.
        if(condition_4){
            if(condition_5) {
                System.out.println("output A");
            } else {
                System.out.println("output B");
            }
        } else {
            if(condition_6) {
                System.out.println("output C");
            } else {
                System.out.println("output D");
            }
        }

        // Second conditional block could be rewritten without nesting
        if(condition_4 && condition_5) {
            System.out.println("output A");
        } else if(condition_4) {
            System.out.println("output B");
        } else if(condition_6) {
            System.out.println("output C");
        } else {
            System.out.println("output D");
        }

        // I prefer un-nested conditional structures for readability

        // also a big fan of switch statements
    }
}
