import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Functions {

    static int ham;

    public static void main(String[] args) {
        System.out.println("bc" + 2 + 3);
        Functions.helloArray();
        System.out.println(GetName());
        System.out.println(TwoNumAdder(40.03, 2));
        System.out.println(Arrays.toString(ArrayCreator(40,30.3)));

        System.out.print(ham);

    }

    public static void addStuff(int a) {
        a += 1;
        ham += 1;
    }

    public static String readLn(){
        try {
            BufferedReader reader = getReader();
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public static double[] ArrayCreator(double a, double b) {
        return new double[]{a, b};
    }

    public static int TwoNumAdder(int a, int b) {
        return (a + b);
    }

    public static double TwoNumAdder(double a, double b) {
        return (a + b);
    }

    public static BufferedReader getReader() {
        InputStreamReader input = new InputStreamReader(System.in);
        return new BufferedReader(input);
    }

    public static String GetName() {
        return "Dave";
    }

    public static void helloArray() {
        char[] HelloArray = {'H','e','l','l','o'};

        for(char letter: HelloArray) {
            System.out.print(letter);
        }
        System.out.print(" ");
        String HelloString = "World";
        for(int c=0; c<HelloString.length(); c++){

            System.out.print(HelloString.charAt(c));
        }
        System.out.println();
    }
}
