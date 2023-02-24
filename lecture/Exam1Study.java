public class Exam1Study {
    public static void main(String[] Args) {
    /*
     * Complete the program below so that it sets the variable is_a_pal
     * to true if the input String is a palindrome (reads the same forwards and backwards),
     * and false if the input is not a palindrome (examples: racecar → true / slowcar → false).
     * You can assume that the input is all lowercase.
     */
        char[] Input = new char[Args[0].length()];
        for (int i=0; i<Args[0].length(); i++)
            Input[i] = Args[0].charAt(i);
        Boolean is_a_pal = true;
        for (int i=0; i<Input.length; i++) {
            if (Input[i] == Input[Input.length - 1 - i]) {
                continue;
            }
            is_a_pal = false;
        }
        int[] Integers = new int[Args.length];
        boolean is_int_pal = true;
        try {
            for (int i = 0; i < Integers.length; i++) Integers[i] = Integer.parseInt(Args[i]);
        } catch (NumberFormatException ignored) { is_int_pal = false; }

        for(int i=0; i<Integers.length; i++) {
            int c = Integers.length - 1 - i;
            if(Integers[i] != Integers[c]) {
                is_int_pal = false;
                break;
            }
        }

        //Reverse in place
        for(int i=0; i<Integers.length/2; i++) {
            int temp = Integers[Integers.length - (1+i)];
            Integers[Integers.length - (1+i)] = Integers[i];
            Integers[i] = temp;
        }

        System.out.println(is_int_pal);
        System.out.println(is_a_pal);


        //Figure out value on paper
        int value = 0;
        for (int i=0; i < 1000000; i++ ) {
            value += i * 2;
            if (value > 5 || i % 3 == 2)
            {
                break;
            }

            value--;
        }
        System.out.println(value);
    }
}
