public class DiamondMaker {
    public static void main(String[] args) {
        int width = 0;
        if(args.length == 1) {
            try {
                width = Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                System.out.println("Number format exception.");
                return;
            }
        } else {
            System.out.println("Please provide a command line argument for width of diamond.");
        }

        int total = 0;
        for(int c=0; c<width; c++) {
            total += c;
            printOffsetAsterisks(width-c, c);
            System.out.println();
        }
        for(int c=width; c>=0; c--) {
            total +=c;
            printOffsetAsterisks(width-c, c);
            System.out.println();
        }
        System.out.println("Value = $" + String.valueOf(total));
    }

    public static void printOffsetAsterisks(int offset, int asterisks)
    {
        for(int c=0; c<offset; c++) {
            System.out.print(" ");
        }
        for(int c=0; c<asterisks; c++) {
            System.out.print("* ");
        }

    }
}
