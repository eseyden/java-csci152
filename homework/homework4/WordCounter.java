import java.util.Arrays;

public class WordCounter {
    public static void main(String[] args) {
        IntHashTable HashTable = new IntHashTable(10);
        IntHashTable HashTable2 = new IntHashTable(10);
        HashTable.put("Hello World!",1);
        HashTable.put("My WAorld!",3);
        HashTable2.put("My World!",3);
        HashTable.put("My ZWorld!",3);
        HashTable2.put("My Worlfd!",3);
        HashTable.printKeys();
        HashTable.printValues();
        HashTable2.printKeys();
        HashTable2.printValues();
    }
    private static class IntHashTable {
        private String[] Keys;
        private int[] Values;
        private int Capacity;
        private int Occupancy;

        public IntHashTable(int Size) {
            this.Occupancy = 0;
            this.Capacity = (int) (Size * 1.6);
            this.Keys = new String[this.Capacity];
            this.Values = new int[this.Capacity];
        }

        public void printKeys() {
            System.out.println(Arrays.toString(this.Keys));
        }
        public void printValues() {
            System.out.println(Arrays.toString(this.Values));
        }

        public void put(String Key, Integer Value) {
            int hash = calculateHashCode(Key);
            boolean FoundKey = false;
            boolean Looped = false;
            while( ! FoundKey ) {
                if(Keys[hash] == null) {
                    this.Keys[hash] = Key;
                    this.Values[hash] = Value;
                    FoundKey = true;
                    this.Occupancy++;
                } else if(Keys[hash].equals(Key)) {
                    this.Values[hash] = Value;
                    FoundKey = true;
                } else {
                    hash++;
                }
                if(hash == this.Capacity && ! Looped) {
                    Looped = true;
                    hash = 0;
                } else if (hash == this.Capacity ){
                    System.out.println("Ran out of hash table space.");
                    System.exit(1);
                }
            }
        }

        public Integer get(String Key) {
            int hash = calculateHashCode(Key);
            int startingHash = hash;

            boolean Looped = false;

            while( true ) {
                if(this.Keys[hash] == null) {
                    return null;
                } else if (this.Keys[hash].equals(Key)) {
                    return this.Values[hash];
                } else {
                    hash++;
                }
                if(hash == this.Capacity && ! Looped) {
                    Looped = true;
                    hash = 0;
                } else if ( Looped && hash == startingHash ){
                    return null;
                }
            }
        }

        private int calculateHashCode(String Key) {
            // return Key.hashCode() % HashTableSize;
            int hash = 0;
            for(int i=0; i<Key.length(); i++) {
                hash += Key.charAt(i);
            }
            return Math.abs(hash % this.Capacity);
        }
    }
}
