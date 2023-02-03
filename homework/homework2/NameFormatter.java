/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #2
 * Problem 3
 * (NameFormatter.java)
 * -------------------------------------------------------------------------------
 * This formats names provided as command line arguments.
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-02-03
 * -------------------------------------------------------------------------------
 */
public class NameFormatter {
    public static void main(String[] args) {

        // Iterate over command line arguments
        for(String name: args) {
            printFormattedString(name); // Call function that normalizes a single input
            System.out.print(' '); // Put space between parts of name
        }

        System.out.println();
    }

    /**
     * Print Formatted String
     * for names with weird capitalization
     * @param name input string
     */
    public static void printFormattedString(String name)
    {
        // initialize variable to keep track of what part of string to capitalize
        boolean first = true;
        char last_char = '?';  // variable for checking if letter appears after a dash
        for(char letter: name.toCharArray()) {
            if(first || last_char == '-') { // check for start of the string or after a dash character
                System.out.print(capitalizeChar(convertChar(letter))); // print a normalized capitol character
                first = false; // we ran this once so don't capitalize anything else
            } else {
                System.out.print(convertChar(letter)); // print a normalized lower case character
            }
            last_char = letter; // keep track of the last char printed
        }
    }

    /**
     * Convert Char
     * Normalizes a single character by
     * substituting letters for numbers and making all
     * letters lower case
     * @param character un-normalized character
     * @return normalized character
     */
    public static char convertChar(char character) {

        // Main switch statement to map numbers to their letter
        switch (character) {
            case '0': // ASCII: 48
                return 'o';
            case '1': // ASCII: 49
                return 'l';
            case '2': // ASCII: 50
                return 'z';
            case '3': // ASCII: 51
                return 'e';
            case '5': // ASCII: 53
                return 's';
            case '6': // ASCII: 54
                return 'g';
            case '7': // ASCII: 55
                return 't';
            case '8': // ASCII: 58
                return 'b';
        }
        //If uppercase make lower case
        if(character > 64 && character < 91) { //ASCII range of uppercase letters
            character = (char)(character + 32); //Add int offset to have character be lowercase
        }
        return character;
    }

    /**
     * Capitalize Char
     * Converts lowercase alphabet chars to uppercase
     * @param character potentially lowercase char
     * @return uppercase character
     */
    public static char capitalizeChar(char character) {
        if(character > 96 && character < 123) { //ASCII range of lowercase characters
            character = (char)(character - 32); //Subtract an int offset to convert to uppercase
        }
        return character;
    }
}
