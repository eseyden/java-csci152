import java.util.Arrays;

public class Matrix {
    int[][] Matrix;
    public static void main(String[] args) {
        Matrix MyMatrix = new Matrix();
        MyMatrix.Matrix = new int[][] {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 },
                {10,11,12 }
        };
        MyMatrix.print();
        Matrix MyMatrix2 = new Matrix();

        MyMatrix2.Matrix = new int[4][3];
        int value = 1;
        for(int y=0; y<MyMatrix2.Matrix.length; y++) {
            for(int x=0; x<MyMatrix2.Matrix[y].length; x++) {
                MyMatrix2.Matrix[y][x] = value;
                value++;
            }
        }
        MyMatrix2.print();

        Matrix MyMatrix3 = new Matrix();
        int[] ArrayOne = new int[5];
        int[] ArrayTwo = new int[3];
        MyMatrix3.Matrix = new int[][] { ArrayOne, ArrayTwo };
        MyMatrix3.Matrix[0][1] = 4;
        ArrayTwo[2] = ArrayOne[1];
        int [] A3 = ArrayTwo;
        A3[0] = 42;
        MyMatrix3.print();
        MyMatrix3.Matrix[1] = new int[7];
        MyMatrix3.print();

    }

    void print() {
        StringBuilder content = new StringBuilder();
        for(int[] row: Matrix) {
            content.append(Arrays.toString(row)).append("\n");
        }
        System.out.println(content);
    }
}
