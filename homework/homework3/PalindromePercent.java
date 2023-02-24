/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #3
 * Problem 2
 * (PalindromePercent.java)
 * -------------------------------------------------------------------------------
 * This program reads a list of words from a file.
 * It reports the number of words
 * and the percentage of them that are palindromes.
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-02-23
 * -------------------------------------------------------------------------------
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PalindromePercent {

    public static void main(String[] args) throws IOException {
        // Ensure file name provided
        if(args.length != 1) {
            System.out.println("Please specify a file name.");
            System.exit(1);
        }

        Scanner WordList = openFile(args[0]); // Get instance of scanner from specified file.

        // Initialize Counts
        int PalindromeCount = 0;
        int LineCount = 0;

        // Loop for reading file
        while(WordList.hasNextLine()) { // If next line exists process it
            LineCount++; // Increment line count
            String word = WordList.nextLine(); // Read line
            // Increment palindrome count if word is a palindrome
            if(isPalindrome(word)) PalindromeCount++;
        }
        WordList.close(); // Close file for reading

        // Program line count output
        System.out.println("word_list.txt contains " + LineCount + " words" );

        // Calculate palindrome percentage
        double percentage = ((PalindromeCount / (double) LineCount)) * 100;

        //Output formatted palindrome percentage
        System.out.format("%.1f%% are palindromes!%n",percentage);
    }

    /**
     * Is Palindrome
     * @param word Word to check
     * @return True if string is a palindrome
     */
    public static boolean isPalindrome(String word) {
        int WordLength = word.length(); // Get length of word

        for(int c=0; c<WordLength/2; c++) { //Loop through the two halves of string
            // Return false if a beginning half char does not match ending half
            if(word.charAt(c) != word.charAt(WordLength - (c+1))) return false;
        }

        // If we didn't break out of function in loop it must be a palindrome
        return true;
    }

    /**
     * Open File
     * @param filename Filename to open for scanning
     * @return Instance of file scanner
     * @throws FileNotFoundException Should exit program before reaching condition
     */
    public static Scanner openFile(String filename) throws FileNotFoundException {
        File FileHandle = new File(filename); // Create new instance of File object
        try {
            return new Scanner(FileHandle); // Create instance of scanner opening file.
        } catch (IOException exception) {
            //Output exception message and halt program
            System.out.println(exception.getMessage());
            System.exit(1);
            //Should never get here but Java demands it with no final return.
            throw exception;
        }
    }
}
