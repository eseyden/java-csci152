/*
    One array holding keys,
    One array holding values
    Hash functions that convert the key to an integer index

    Understand capacity
    Don't overstep while hashing or probing

    Understand occupancy
    Know when to resize.
 */
public class Hashing {
    static int HashTableSize = 10;
    static Integer[] Keys = new Integer[HashTableSize];
    static String[] Values = new String[HashTableSize];

    static private class ObjectStuff {
        public static void main(String[] args) {
            int capacity = 10;
            int occupancy = 0;

            String[] Keys = new String[capacity];
            String[] Values = new String[capacity];

            String[] Names = {"Sue", "Bob", "Amy"};
            String[] Relations = {"mom","dad","sis"};

            for (int i=0; i<Names.length; i++) {
                int hash_val = calculateHashCode(Names[i]);
                // TODO: Finish implementation
            }
        }
    }

    public static void main(String[] args) {
        put("Bob","Uncle");
        ObjectStuff.main(new String[]{});
    }

    public static void put(String NewKey, String NewValue) {
        int hash = calculateHashCode(NewKey);
        System.out.println(hash);
    }

    /*
     * Avoid always generating even or odd numbers.
     * Always end with a "mod" of the capacity of the hash table
     * Spread things around (big multiplications can help)
     */
    public static int calculateHashCode(String Key) {
        // return Key.hashCode() % HashTableSize;
        int hash = 0;
        for(int i=0; i<Key.length(); i++) {
            hash += Key.charAt(i);
        }
        return Math.abs(hash % HashTableSize);
    }
}
