import java.util.Arrays;

public class Arguments {
    public static void main(String[] args) {

        int args_length = args.length;
        for (String my_arg : args) {
            System.out.println(my_arg);
        }

    }

    public static void playWithArgs(String[] args) {
        System.out.println(Arrays.toString(args));
        String a1 = args[0];
        String a2 = args[1];
        System.out.println(a1 + " " + a2);

        // Cast to int
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        System.out.println(a + b);

        double da = Double.parseDouble(args[0]);
        double db = Double.parseDouble(args[1]);

        System.out.println(da / db);
    }
}
