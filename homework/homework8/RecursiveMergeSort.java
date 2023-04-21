/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #8
 * Problem 1
 * (RecursiveMergeSort.java)
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-04-21
 * -------------------------------------------------------------------------------
 * Problem 1 - The wild mustang of CSCI 152. Recursive Merge Sort
 * -------------------------------------------------------------------------------
 */
public class RecursiveMergeSort {

    /**
     * Main
     * Entry point for program.
     * @param args Array size
     */
    public static void main(String[] args) {
        /*
            If we didn't provide an argument just run part two of the homework.

            PROBLEM 2
            ------------------------------------------------------------------------------------------------------------

            Output looks a little less steep than what wolfram alpha tells me the graph for N log2 N should be.
            Could be random just isn't random enough or something else. Seems like the time should be scaling up faster.

            Sorting 80000 elements.
            80000 elements: 27 milliseconds
            Sorting 160000 elements.
            160000 elements: 36 milliseconds
            Sorting 320000 elements.
            320000 elements: 50 milliseconds
            Sorting 640000 elements.
            640000 elements: 101 milliseconds
            Sorting 1280000 elements.
            1280000 elements: 181 milliseconds

            ------------------------------------------------------------------------------------------------------------
         */
        if(args.length == 0 ) {
            int size = 80000;
            for(int c=1; c<=5; c++) {
                System.out.println("Sorting "+ size +" elements.");
                testSort(size);
                size *= 2;
            }
            System.exit(0);
        }

        // Check for nonsensical input
        if(!isInteger(args[0]) || args.length != 1){
            System.out.println("Error: invalid array size.");
            System.exit(1);
        }

        // Run problem 1 if we have good input
        int size = Integer.parseInt(args[0]);
        testSort(size);
    }

    /**
     * Test Sort
     * Generates a random array of given size
     * then times the operation to sort it
     * via a recursive merge sort.
     *
     * @param size Size of array
     */
    public static void testSort(int size) {
        int[] array = new int[size]; // Create array
        for(int c=0; c<size; c++) {
            array[c] = (int)(1000000 * Math.random()); // Fill it with random values between 0 and a close to a million.
        }
        long start = System.currentTimeMillis(); // start timing
        array = sort(array); // sort the thing
        long end = System.currentTimeMillis(); // stop timing
        long SortTime = end - start; // calculate the difference
        boolean sorted = true; // Check to see if actually sorted
        for(int c=1; c<size; c++)
            if (array[c - 1] > array[c]) { // the last value wasn't lower than the next
                sorted = false; // this array wasn't actually sorted!
                break;
            }
        if(sorted) {
            System.out.println(size + " elements: " + SortTime + " milliseconds");
        } else {
            System.out.println("Not properly sorted.");
        }
    }

    /**
     * Is integer?
     * @param input Input argument as string
     * @return ture if parses as integer
     */
    public static boolean isInteger(String input) {
        if (input == null) {
            return false;
        }
        try {
            int value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Sort
     * Sorts an array via recursive quick sort
     * @param myArray Array to sort
     * @return Sorted array
     */
    private static int[] sort(int[] myArray) {
        if(myArray.length < 2 ) return myArray; // Arrays of single item exit condition.
        int[][] splits = split(myArray); // divide array into an array of two arrays
        int[] left = sort(splits[0]); // sort the left half
        int[] right = sort(splits[1]); // sort the right half
        return merge(left,right); // put the results together
    }

    private static int[][] split(int[] myArray) {
        int aLength = myArray.length / 2; // find center point
        int bLength = myArray.length - aLength; // get the rest of the values
        int[] aArray = new int[aLength]; // init array for left half
        int[] bArray = new int[bLength]; // init array for right half
        int[][] out = new int[][] {aArray, bArray}; // Use a matrix for our output of two arrays
        for(int c=0; c<aLength; c++) { // copy the left half over
            aArray[c] = myArray[c];
        }
        for(int c=0; c<bLength; c++) { // copy the right half over
            bArray[c] = myArray[aLength+c];
        }
        return out; // return the two arrays
    }

    /**
     * Merge
     * Smushes two arrays together,
     * putting the items in order as it goes.
     * @param aArray Left array
     * @param bArray Right array
     * @return Combined array
     */
    private static int[] merge(int[] aArray, int[] bArray) {
        int aIndex = 0; // Keep track of where we are on the left array
        int bIndex = 0; // Same for the right
        int[] out = new int[aArray.length + bArray.length]; // Init the output array to hold all the values
        int index = 0; // Output array writing index.
        while(aIndex < aArray.length && bIndex < bArray.length) { // Loop through while both arrays have values
            // Write the lowest value of the two arrays to the output array
            if(aArray[aIndex] < bArray[bIndex]) {
                out[index++] = aArray[aIndex++];
            } else {
                out[index++] = bArray[bIndex++];
            }
        }
        // Append the leftovers after one of the arrays runs out of values.
        while(aIndex < aArray.length) {
            out[index++] = aArray[aIndex++];
        }
        while(bIndex < bArray.length) {
            out[index++] = bArray[bIndex++];
        }
        return out;
    }

}