import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        int[] Arr = new int[] {9, 10, 4, 7, 3, 1, 8, 5, 6, 2};

        System.out.println(Arrays.toString(selectionSort(Arr)));
    }

    public static int[] selectionSort(int[] Arr) {
        for(int c=0; c<Arr.length-1; c++){
            int SmallestIndex = c;
            for(int j=c+1; j<Arr.length; j++){
                if(Arr[j] < Arr[SmallestIndex]) {
                    SmallestIndex = j;
                }
            }
            int temp = Arr[c];
            Arr[c] = Arr[SmallestIndex];
            Arr[SmallestIndex] = temp;
        }
        return Arr;
    }

    public static int[] insertionSort(int[] Arr) {
//        for(int i=1; i<Arr.length; i++) {
//            int val = Arr[i];
//            int j = i-1;
//            while(j>=0 && val < Arr[i]) {
//
//            }
//        }
        return Arr;
    }

    public static int[] bubbleSort(int[] Arr) {
        for(int i=Arr.length-1; i >0; i++) {
            for(int j=0; j<i; j++) {
                if(Arr[j] > Arr[j+1]) {
                    int tmp = Arr[j];
                    Arr[j] = Arr[j+1];
                    Arr[j+1] = Arr[j];
                }
            }
        }
        return Arr;
    }
}
