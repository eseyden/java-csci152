import java.util.Arrays;

public class MyMergeSort {
    public static void main(String[] args) {
        int[] myArray = new int[] { 5 , 6, 10, -1, 27, 42, 69, 420 , 98};
        IterMergeSort(myArray);
        System.out.println(Arrays.toString(myArray));;
    }

    public static void IterMergeSort(int[] Array) {
        int block_size =1;
        int[] Write = new int[Array.length];
        while(block_size < Array.length){
            int bound = Array.length - block_size;
            int incrementSize = block_size * 2;
            int write_pos = 0;
            for( int far_left = 0; far_left < bound; far_left += incrementSize) {
                int left = far_left;
                int right = left + block_size;
                int mid_pos = right;
                int far_right = right + block_size;
                if(far_right > Array.length) {
                    far_right = Array.length;
                }
                while(left < mid_pos && right < far_right) {
                    if (Array[left] < Array[right]) {
                        Write[write_pos++] = Array[left++];
                    } else {
                        Write[write_pos++] = Array[right++];
                    }
                }
                while(left < mid_pos) {
                    Write[write_pos++] = Array[left++];
                }
                while(right < far_right) {
                    Write[write_pos++] = Array[right++];
                }
            }

            while(write_pos < Array.length) {
                Write[write_pos] = Array[write_pos];
                write_pos++;
            }
            int[] Tmp = Write;
            Write = Array;
            Array =  Tmp;
            block_size += block_size;
        }
    }
}
