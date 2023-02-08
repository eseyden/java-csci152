public class MyArrays {
    public static void main(String[] args) {
        int[] ArrA = new int[args.length];

        for(int c=0; c<args.length; c++)
            ArrA[c] = Integer.parseInt(args[c]);

        //Array lengths are immutable
        //Make another copy when needed
        int[] ArrA2 = new int[ArrA.length +1];
        for(int c=0; c<ArrA.length; c++)
            ArrA2[c] = ArrA[c];
        //Alternative: System.arraycopy(ArrA, 0, ArrA2, 0, ArrA.length);

        //Finally get to add one thing to the end of the array.
        ArrA2[ArrA.length] = 800;

        printArray(ArrA);
        printArray(ArrA2);

    }
    public static void demo() {
        //Declare array
        int[] MyLuckyNums;

        //Initialize array with length of 6
        MyLuckyNums = new int[6];

        // All zero!
        printArray(MyLuckyNums);

        //Init on declaration
        int [] MoreLuckyNumbers = {13, 12, 7, 25, 9 ,19};
        printArray(MoreLuckyNumbers);

        //Strings init to null
        String[] StringArray = new String[4];
        printArray(StringArray);
    }

    public static void printArray(int[] numbers){
        System.out.print('[');
        boolean first = true;
        for (int i=0; i<numbers.length; i++) {
            if(!first){
                System.out.print(", ");
            }
            first = false;
            System.out.print(numbers[i]);
        }
        System.out.println(']');
    }

    public static void printArray(String[] strings)
    {
        System.out.print('[');
        boolean first = true;
        for(String string: strings) {
            if(!first){
                System.out.print(", ");
            }
            first = false;
            System.out.print(string);
        }
        System.out.println(']');
    }
}
