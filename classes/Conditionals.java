public class Conditionals {
    public static void main(String[] args)
    {
        boolean i_need_coffee = true;
        double coffee_card_val = 5.00;
        double cost_of_a_coffee = 2.25;

        boolean i_can_afford_coffee = coffee_card_val >= cost_of_a_coffee;

        if(i_can_afford_coffee && i_need_coffee) {
            System.out.println("Lets grab a cup!");
        }

        double checking_account = 400.00;
        double paycheck = 3300.00;
        double expenses = 2200.00;

        for ( int month=1; month<=12; month++)
        {
            checking_account += paycheck;
            checking_account -= expenses;
        }

        System.out.printf("%.2f%n",checking_account);
    }

}
