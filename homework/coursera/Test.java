public class Test {
    public static void main(String[] args) {
        int n = 123456789;
        int m = 0;
        while(n != 0)
        {
            int term1 = (10 * m);
            int term2 = (n % 10);
            m = term1 + term2;
            n = n /10;
        }
        System.out.println(m);

        int j = 0;
        for (int i = 0; i < 10; i++)
            j += i;
        System.out.println(j);
    }

}
