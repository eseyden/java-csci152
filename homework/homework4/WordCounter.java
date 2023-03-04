/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #4
 * Problem 1, 2 & 3
 * (WordCounter.java)
 * -------------------------------------------------------------------------------
 * This program reads a list of words from a file.
 * It reports the frequency count of words
 * and compares frequency count between files.
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-02-23
 * -------------------------------------------------------------------------------
 */

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*
-----------------------------------------------------------------------------------------------------------------------
                                                    Question 1
-----------------------------------------------------------------------------------------------------------------------
If I were writing "the hash library" that balanced memory requirements and time spent probing,
I would first find a good reference to read about the problem.

First is Stack Overflow: https://stackoverflow.com/questions/26349226/size-of-the-hash-table
Then that leads to the Wikipedia article, Hash Table Collision Resolution.

This has a great graph comparing CPU cache misses for hash tables using linear probing.
https://en.wikipedia.org/wiki/Hash_table#/media/File:Hash_table_average_insertion_time.png

According to this graph it looks like linear probing performance drops off at a load factor of 0.8
Given this I would probably have capacity be a parameter for instancing this class
and construct the array pair so with a given capacity it would have a load factor of 0.6 to start with.

So I'd initialize the arrays with a size 1.6 times the request capacity.
Then as items got added to the hash table it would resize itself back to a load factor of 0.6
if it ever hit a load factor of 0.8, that is where load factor is calculated by the occupancy divided by the capacity.

Of course a really fun way to tune the algorithm is to write some unit tests.
I'd probably use this library https://github.com/DiUS/java-faker to generate random data to put in the hash table.

Then collect performance metrics with a suite of unit tests and verify my assumptions before publishing the library.

After checking the stats on this program running it seemed like just resizing it 160% of current capacity
each time it got to 80% occupancy worked well. Just adding 20% seemed to trigger resizes quickly.
Ram is cheap these days.
 */


/**
 * Word Counter
 * -----------------------------
 * Entrypoint for program.
 * Handles command line arguments,
 * instancing classes,
 * and output
 */
public class WordCounter {
    public static void main(String[] args) {
        if(args.length == 1) {
            // Initialize word reader class
            WordReader MyReader = new WordReader(args[0]);
            // Fetch an integer hash table of all the words in the file
            IntegerHashTable MyWords = MyReader.GetWordsHashTable();
            // Print the sorted hash table contents
            PrintSortedHashTable(MyWords);
        } else if (args.length == 2) {
            // Initialize word reader and fetch hash table for common words
            WordReader CommonWordReader = new WordReader(args[0]);
            IntegerHashTable CommonWordsHashTable = CommonWordReader.GetWordsHashTable();

            // Initialize word reader and fetch hash table for a fancy text file
            WordReader FancyTextWordReader = new WordReader(args[1]);
            IntegerHashTable FancyWordHashTable = FancyTextWordReader.GetWordsHashTable();

            // Get an array of all key value objects from the fancy words hash table.
            KeyValue[] PossiblyFancyWords = FancyWordHashTable.GetAllKeyValues();

            double FancyPercentage = getFancyPercentage(CommonWordsHashTable, PossiblyFancyWords);
            // Print a formatted percentage of fancy words
            System.out.format("%.2f%% of words are extra-fancy!%n", FancyPercentage );
        }

    }

    private static double getFancyPercentage(IntegerHashTable CommonWordsHashTable, KeyValue[] PossiblyFancyWords) {
        // Initialize our counts for computing level of fancyness
        int FancyWordCount = 0;
        int AllWordCount = 0;

        // Loop through the key values
        for(KeyValue Item: PossiblyFancyWords) {
            AllWordCount += Item.getValue(); //Increment total unique word count

            // Check to see if word is in our hash table of fancy words
            if(CommonWordsHashTable.get(Item.getKey()) == null) {
                // If it isn't in the common word map add to the count of fancy words
                FancyWordCount += Item.getValue();
            }
        }
        // Calculate percentage of fancy words
        return ( (double) FancyWordCount / (double) AllWordCount) * 100;
    }

    /**
     * Print Sorted Hash Table
     * @param HashTable Integer Hash Table of word frequency
     */
    private static void PrintSortedHashTable(IntegerHashTable HashTable) {
        // Get array of key value objects
        KeyValue[] AllWordValues = HashTable.GetAllKeyValues();
        // Comparator and getter makes sorting by different things easy
        Arrays.sort(AllWordValues, Comparator.comparing(KeyValue::getKey));
        //Print out the necessary info
        System.out.println("Capacity: " + HashTable.getCapacity());
        System.out.println("Occupancy: " + HashTable.getOccupancy() + "\n");
        for(KeyValue Word: AllWordValues) {
            System.out.println(Word.getKey() + ", " + Word.getValue());
        }
    }

    private static void CheckStats(IntegerHashTable HashTable) {
        System.out.println("There were " + HashTable.Overflows + " overflows.");
        System.out.println("There were " + HashTable.Probes + " probes.");
        System.out.println("There were " + HashTable.Upsizes + " upsizes.");
    }
}

/**
 * Word Reader
 * -----------------------------
 * Handles file IO
 * word detection
 * and returning hash tables
 */
class WordReader {
    private final String FileName;
    private Scanner FileScanner;

    /**
     * Word Reader
     * @param FileName File name you would like read
     */
    WordReader(String FileName) {
        this.FileName = FileName;
    }

    /**
     * Get Words Hash Table
     * @return Integer hash table of word frequency.
     */
    public IntegerHashTable GetWordsHashTable() {
        // Instance New Hash Table
        IntegerHashTable HashTable = new IntegerHashTable();
        // Open file and instance scanner
        FileScanner = OpenFile(FileName);
        // Keep reading while there are words left
        while(FileScanner.hasNext()) {
            String Word = readNextWord(); // Read the next word from the file
            if(!Word.isEmpty()) { // Make sure we don't store empty strings
                HashTable.increment(Word); // Store the word in the hash table
            }
        }
        FileScanner.close(); // Close the file
        return HashTable; // Return the hash table
    }

    /**
     * Get Words Hash Table Via Split
     * -------------------------------
     * Used to compare read next
     * to provided word detection code
     * @return Integer hash table of word frequency.
     */
    public IntegerHashTable GetWordsHashTableViaSplit() {
        IntegerHashTable HashTable = new IntegerHashTable();
        FileScanner = OpenFile(FileName);
        FileScanner.reset();
        while(FileScanner.hasNextLine()) {
            String[] Words = split(FileScanner.nextLine());
            for(String Word: Words){
                if(!Word.isEmpty()) {
                    HashTable.increment(Word);
                }
            }
        }
        FileScanner.close();
        return HashTable;
    }

    /**
     * Read Next Word
     * @return Next detected word in the file
     */
    private String readNextWord() {
        /*
         * This regex is supposed to detect when punctuation is used
         * at the beginning or end of words and preserve it otherwise
         * Moby Dick had a lot of weird punctuation, but I don't agree
         * that punctuation makes a word fancy.
         * Fancy words must stand on their own verbosity.
         */
        String wordRegex = "^[’'-]|[’'-]$|[^a-zA-Z0-9'`’-]";
        return FileScanner
                .next()
                .replaceAll(wordRegex, "")
                .replaceAll("^’","")
                .toLowerCase()
                .trim();
    }

    /**
     * Open File
     * -------------------------------------------
     * Open the file and prepare the scanner
     * for a whale of a tale.
     * @param filename File you want to read
     * @return File scanner with custom delimiter
     */
    public Scanner OpenFile(String filename) {
        File FileHandle = new File(filename);
        try {
            Scanner MyScanner = new Scanner(FileHandle);
            //Don't let weird punctuation instead of spaces deter us
            String wordDelimiterRegex = "[ ;.,\n]|!—|\\.-|\\.—|;—";
            MyScanner.useDelimiter(wordDelimiterRegex);
            return MyScanner;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }
        return null;
    }

    /**
     * Provided algo to split lines into words
     * @param line Line of text
     * @return Array of words on that line
     */
    public static String[] split(String line) {

        // Allocate a large-ish array to ensure that we won't
        // risk stepping out-of-bounds while filling it.
        String[] BigAllWords = new String[(line.length() / 2) + 1]; // Added one for single letter line-words
        int word_count = 0;

        // Iterate over each character in the line, building
        // up our next word until we hit whitespace (at which
        // point we plop the word into our array).
        String current_word = null;
        for (int i = 0; i < line.length(); i++) {

            char next_char = line.charAt(i);

            if (next_char == ' ') {

                // Plop!
                if (current_word != null) {
                    BigAllWords[word_count++] = current_word;
                    current_word = null;
                }

            } else if (next_char >= 65 && next_char < 90) {

                // Lowercase-ify
                if (current_word == null)
                    current_word = "" + (next_char + 32);
                else
                    current_word += next_char + 32;

            } else if (next_char >= 97 && next_char < 123) {

                // Nice work being lowercase!
                if (current_word == null)
                    current_word = "" + next_char;
                else
                    current_word += next_char;

            }

        }

        // Did we have a word in-progress when we hit the end
        // of the loop?
        if (current_word != null)
            BigAllWords[word_count++] = current_word;


        // It was fun having an oversized array, but let's
        // tailor it just a bit before the big return
        String[] FinalWords = new String[word_count];
        for (int i = 0; i < word_count; i++)
            FinalWords[i] = BigAllWords[i];

        // Hooray!
        return FinalWords;

    }
}

/**
 * Key Value
 * ---------------------
 * Object representation
 * of a key value pair
 * ---------------------
 */
class KeyValue {
    private final String Key;
    private final int Value;

    /**
     * Key Value
     * Object representation
     * of a hash map's data pair
     * @param Key
     * @param Value
     */
    KeyValue (String Key, int Value) {
        this.Key = Key;
        this.Value = Value;
    }

    public String getKey() {
        return Key;
    }

    public int getValue() {
        return Value;
    }
}

/**
 * Integer Hash Table
 * ---------------------------------
 * This class is an implementation
 * of a hash table where the values
 * are integers
 * ---------------------------------
 */
class IntegerHashTable {

    private String[] Keys;
    private int[] Values;

    private int Capacity;
    private int Occupancy;
    public int Overflows = 0;
    public int Upsizes = 0;
    public int Probes = 0;
    private Integer ActiveHash;

    /**
     * Integer Hash Table
     * Default size 100
     */
    IntegerHashTable() {
        this(100);
    }

    /**
     * Integer hash table
     * @param Size Super size for hungry datasets!
     */
    IntegerHashTable(int Size) {
        Occupancy = 0;
        Capacity = (int) (Size * 1.6);
        Keys = new String[Capacity];
        Values = new int[Capacity];
    }

    /**
     * Resize the hash table
     */
    private void upsize() {

        // Get an array of all key value pairs
        KeyValue[] KeyValues = GetAllKeyValues();

        // Calculate an optimal new capacity
        int NewCapacity = (int) (Capacity * 1.6);

        // If your hash table starts teensy it'll never grow
        if(NewCapacity == Capacity) Capacity++;
        else Capacity = NewCapacity;

        // Resize our internal arrays
        Keys = new String[Capacity];
        Values = new int[Capacity];
        Occupancy = 0;

        // Rehash old things
        for(KeyValue item: KeyValues) {
            put(item.getKey(),item.getValue());
        }
    }

    /**
     * Put
     * @param Key Key to store
     * @param Value Value to save
     */
    private void put(String Key, Integer Value) {

        // See if we are at a good size and resize if necessary
        checkForSpace();

        // Calculate the hash
        int hash = calculateHashCode(Key);

        // Initialize our state
        boolean FoundKey = false;
        boolean Looped = false;

        // Keep probing for empty space or the key
        while( ! FoundKey ) {
            // We found an empty spot
            if(Keys[hash] == null) {
                Keys[hash] = Key; // Populate the key
                Values[hash] = Value; // Store the value
                FoundKey = true; // End the loop
                Occupancy++; // Up the occupancy
            } else if(Keys[hash].equals(Key)) { // Check if this is the key we are looking for
                // It is!
                Values[hash] = Value; // Update the value
                FoundKey = true; // End the loop
            } else {
                Probes++; // Keep track of our probing
                hash++; // Increment the hash . . .
            }
            if(hash == Capacity && ! Looped) { // We didn't find a free spot the first time around
                Overflows++;
                Looped = true; // Let the loop know it reached the capacity once
                hash = 0;
            } else if (hash == Capacity ){ // Hit the end the second time around
                System.out.println("Ran out of hash table space."); // Let the user know
                System.exit(1); // It is time to give up
            }
        }
    }

    /**
     * Check for Space
     */
    private void checkForSpace() {
        // Check to see if we are more than 80% full
        if(((double) (Occupancy + 1) / (double) Capacity) >= 0.8) {
            Upsizes++; // Take a note it happened
            upsize(); // Resize the table
        }
    }

    /**
     * Increment
     * Adds one to existing value or creates it
     * @param Key Key to increment
     */
    public void increment(String Key) {
        Integer PreviousValue = get(Key); // Get the old value
        // Store it in the map
        if( PreviousValue == null ) {
            put(Key, 1); // Must be the first time.
        } else {
            Values[ActiveHash]++; // Increment heavy lifting
            ActiveHash = null; // Just a little spaghetti
        }

    }

    /**
     * Get
     * @param Key key to retrieve
     * @return Value that was stored
     */
    public Integer get(String Key) {
        int hash = calculateHashCode(Key); // Get our handy-dandy hash
        int startingHash = hash; // Keep track of the starting hash value

        boolean Looped = false; // Keep track if we overflowed the table

        while( true ) {
            if(Keys[hash] == null) {
                return null; // Nothing was there return null
            } else if (Keys[hash].equals(Key)) {
                // We found the key
                ActiveHash = hash; // Handy so we don't have to look up locations twice on increment
                return Values[hash]; // Return the value
            } else {
                hash++; // Keep probing
            }
            if(hash == Capacity && ! Looped) { // Reached the end once
                Overflows++; // Went past the end, keep stats for fun
                Looped = true; // Don't keep on looking forever
                hash = 0; // Probe at the beginning
            } else if ( Looped && hash == startingHash ){ // Got back to the start
                return null; // It ain't there
            }
        }
    }

    private int calculateHashCode(String Key) {
        // Almost as good as Java!
        // https://cp-algorithms.com/string/string-hashing.html
        // Polynomial rolling hash!
        // Now if I could get someone to explain why the math on this works I'd be happy
        int p = 31;
        long hash_value = 0;
        long p_pow = 1;
        for(char Char: Key.toCharArray()) {
            hash_value = (hash_value + (Char - 'a' + 1) * p_pow ) % Capacity;
            p_pow = (p_pow * p) % Capacity;
        }
        return (int) Math.abs(hash_value);
        // Maybe I didn't pay attention enough?
        // This introduces a lot of probing.
        /*

        int hash = 0;
        for(int i=0; i<Key.length(); i++) {
            hash += Key.charAt(i) * 5000;
        }
        return Math.abs(hash % Capacity);

        */
        // Java beats anything I could come up with.
        // return Math.abs(Key.hashCode() % Capacity);
    }

    /**
     * Get All Key Values!
     * Value objects rule!
     * @return Array of Integer Hash Table KeyValues
     */
    public KeyValue[] GetAllKeyValues() {
        KeyValue[] All = new KeyValue[Occupancy]; // Make an array of our key/value objects
        int KeyValueIndex = 0; // Init the key/value counter
        ActiveHash = 0; // Let's loop through all the possible hashes
        while(KeyValueIndex < Occupancy) { // Stop once we have full occupancy
            if(Keys[ActiveHash] != null) { // We have a live hash here
                // Instance the value object
                All[KeyValueIndex] = new KeyValue(Keys[ActiveHash], Values[ActiveHash]); // Save it to the array
                KeyValueIndex++; // On to the next key / value
            }
            ActiveHash++; // on to the next hash
        }
        ActiveHash = null; // No longer looping through hashes internally
        return All; // Return the contents of the hash table
    }

    /**
     * Get Capacity
     * @return The size of the internal hash table array
     */
    public int getCapacity() {
        return Capacity;
    }

    /**
     * Get Occupancy
     * @return The amount of items stored in hash table
     */
    public int getOccupancy() {
        return Occupancy;
    }
}
