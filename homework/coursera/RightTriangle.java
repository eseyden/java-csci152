/****************
 * RIGHT TRIANGLE
 * --------------
 * ERIC SEYDEN
 * 2023-02-09
 ***************/
public class RightTriangle {
    public static void main(String[] args) {

        //Parse arguments
        int arg1 = Integer.parseInt(args[0]);
        int arg2 = Integer.parseInt(args[1]);
        int arg3 = Integer.parseInt(args[2]);

        //Figure out what side is what.
        int sum = arg1 + arg2 + arg3;
        int c = Math.max(arg1, Math.max(arg2, arg3));
        int b = sum - c - Math.min(arg1, Math.min(arg2, arg3));
        int a = sum - c - b;

        //Run the algo to check if right triangle
        boolean isRightTriangle = (a*a + b*b) == (c*c);

        System.out.println(isRightTriangle);
    }
}
