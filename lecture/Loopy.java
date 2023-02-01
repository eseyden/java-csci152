public class Loopy {
    public static void main(String[] args) {
        for (int i=19; i<20; i++) System.out.println(i);
        if(false) System.out.println("One Line!");
        else if (true) System.out.println("Twoelse");

        int i = 0;
        while(i<10)
            if(++i % 2 == 1)
                System.out.println(i);
    }
}
