/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #2
 * Problem 2
 * (DiamondMaker.java)
 * -------------------------------------------------------------------------------
 * This program renders a diamond of arbitrary width.
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-02-03
 * -------------------------------------------------------------------------------
 */
public class DiamondMaker {
    public static void main(String[] args) {
        //Initialize width to zero
        int width = 0;

        //Check to see if we have a command line argument
        if(args.length == 1) {
            //Try and handle parse errors
            try {
                //Parse first command line argument as integer
                width = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                //Provide a friendly message when a non number is provided
                System.out.println("Number format exception.");
                return;
            }
        } else {
            //Provide a friendly message when arguments do not match our expectations.
            System.out.println("Please provide a command line argument for width of diamond.");
        }

        //Initialize total * counter.
        int total = 0;

        //Generate top half of diamond
        for(int c=0; c<width; c++) {
            //Increment total with * for row
            total += c;

            //Print out the row's *s with an offset
            printOffsetCharacters(width-c, c);
            //Finish line of * rendering
            System.out.println();
        }
        //Generate bottom half of diamond
        for(int c=width; c>=0; c--) { //Use decrement and start at width to mirror top half
            total +=c; //Increment total with * for row
            printOffsetCharacters(width-c, c); //Print out the row's *s with an offset
            System.out.println(); //Finish line of * rendering
        }
        System.out.println("Value = $" + String.valueOf(total));
    }

    /**
     * printOffsetCharacters
     * This method renders a line of the diamond using * as default
     *
     * @param offset the amount of spaces before first *
     * @param character_count the number of * to print
     */
    public static void printOffsetCharacters(int offset, int character_count)
    {
        //Make it easy to change what is printed
        final char my_asterisk = '*';
        printOffsetCharacters(offset, character_count, my_asterisk);
    }

    /**
     * printOffsetCharacters
     * This method renders a line of the diamond using character specified
     * I really missed not having overloading in PHP
     *
     * @param offset the amount of spaces before first character
     * @param character_count the number of characters to print
     * @param character the character to print
     */
    public static void printOffsetCharacters(int offset, int character_count, char character) {
        //Loop for printing specified number of offset spaces
        for(int c=0; c<offset; c++) {
            System.out.print(" ");
        }
        //Loop for printing specified number of characters
        for(int c=0; c<character_count; c++) {
            System.out.print(character);
            System.out.print(" ");
        }
    }
}
