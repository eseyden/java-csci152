import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int array_size = 10000;
        int max_value = 1000;
        int[] Array = new int[array_size];
        for(int i=0; i<array_size; i++) {
            Array[i] = (int) (Math.random() * max_value);
        }
        int[] copy = Array.clone();

        long start = System.currentTimeMillis();
        Array = Sort(Array,0,Array.length-1);
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));

        start = System.currentTimeMillis();
        Arrays.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));

        for(int i=0; i<Array.length; i++) {
            if(Array[i] != copy[i]) {
                System.out.println("BROKEN!");
            }
        }
        System.out.println(Arrays.toString(Array));
        System.out.println(Arrays.toString(copy));
    }

        public static int[] Sort(int[] Array,int start, int end) {
            if((end - start) < 2) return Array;
            Random myRandom = new Random();
            int pivot = myRandom.nextInt(start,end);
            int[] out = new int[Array.length];
            int low = start;
            int high = end;
            for(int i=start; i<=end; i++){
                if(Array[i] < Array[pivot]) {
                    out[low++] = Array[i];
                } else if(Array[i] > Array[pivot]) {
                    out[high--] = Array[i];
                }
            }
            for(int i = low; i<=high; i++) {
                out[i] = Array[pivot];
            }
            for(int i = 0; i<start; i++){
                out[i] = Array[i];
            }
            for(int i = end+1; i<Array.length; i++){
                out[i] = Array[i];
            }
            out = Sort(out,start,low-1);
            out = Sort(out,high+1,end);
            return out;
        }

}
