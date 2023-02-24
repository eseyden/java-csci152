/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #3
 * Problem 3
 * (SecretRevealer.java)
 * -------------------------------------------------------------------------------
 *                             - COINTELAMATEUR -
 *                      TOP SECRET / COFFEE TABLE READING
 * This program, if you choose to run it, decodes secret messages embedded in html
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-02-24
 * -------------------------------------------------------------------------------
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SecretRevealer {

    /*
     * Static variables makes functions fun
     * but can make for some bad spaghetti code.
     *
     * Ideally separate the processing into another class we could instance.
     * But that is beyond the scope of the material we have covered so far.
     */
    static String[] SecretMessages = new String[] {}; // Array of secret messages
    private static boolean isMatch = false; // Flag for being inside a secret XML node
    private static boolean firstLine = true; // Flag if first line of secret message
    private static String CurrentMessage = ""; // Holds message parts spread across multiple lines of secret code

    public static void main(String[] args) throws IOException {
        // Ensure file name provided
        if(args.length != 1) {
            System.out.println("Please specify a file name.");
            System.exit(1);
        }

        Scanner WebPage = openFile(args[0]); // Open file for scanning

        // Main file reading loop
        while(WebPage.hasNextLine()) { // While there is content in file
            String CurrentLine = WebPage.nextLine(); // Read it
            extractSecrets(CurrentLine); // Process file content
        }
        WebPage.close(); // Close file

        // Reveal secrets!
        for(String Secret: SecretMessages) System.out.println("+: " + Secret);
    }

    /**
     * Extract Secrets
     * ----------------------------------------------------
     * This uses static properties of the class to process
     * lines of an html file
     * ----------------------------------------------------
     * @param CurrentLine Current line of file reading loop
     */
    private static void extractSecrets(String CurrentLine) {
        if(CurrentLine.equals("<secret>")) isMatch = true; // Check for start of secret code and flag it

        // Check for end of secret code, store message and reset flags
        else if(isMatch & CurrentLine.equals("</secret>")) {
            isMatch = false; // End of secret, no longer matching same secret
            firstLine = true; // Next time we won't need a space between lines of a secret
            storeSecret(CurrentMessage); // Add formerly multi-line message to static array of secret messages
            CurrentMessage = ""; // Blank out our temp message holder in prep for next secret
        }

        // Check if inside secret code block and build up message string
        else if(isMatch) {
            if(!firstLine) CurrentMessage += " "; // If this isn't the first line add a space
            else firstLine = false; // If it is the first line set flag to false
            CurrentMessage += CurrentLine; // Append secret message part
        }
    }

    /**
     * Store Secret
     * -------------------
     * Creates a new array
     * with one more item
     * Why not ArrayList?
     * -------------------
     * @param string New secret to store
     */
    public static void storeSecret(String string) {
        // Create new array with copy of old array plus one new value
        String[] NewArray = Arrays.copyOf(SecretMessages, SecretMessages.length +1);
        NewArray[SecretMessages.length] = string; // Set new array slot to the new secret string
        SecretMessages = NewArray; // Put new array of secrets into the static variable
    }

    /**
     * Open File
     * @param filename Filename to open
     * @return File scanner instance
     * @throws FileNotFoundException This should never happen but java demands it.
     */
    public static Scanner openFile(String filename) throws FileNotFoundException {
        File FileHandle = new File(filename); // Create file object
        try {
            return new Scanner(FileHandle); // Instance file scanner
        } catch (IOException exception) { // That failed?
            System.out.println(exception.getMessage()); // Tell the user why
            System.exit(1); // Exit program
            throw exception; // Java being picky about returns
        }
    }
}
