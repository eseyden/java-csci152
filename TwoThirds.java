/**
 * -------------------------------------------------------------------------------
 * CSCI 152, Homework #1
 * Problem 4
 * (TwoThirds.class)
 * -------------------------------------------------------------------------------
 * Dollar store sale calculation
 * -------------------------------------------------------------------------------
 * Eric Seyden
 * 2023-01-30
 * -------------------------------------------------------------------------------
 * //Things I wanted to import
 * import java.text.NumberFormat;
 * import java.util.Locale;
 */
public class TwoThirds {
    public static void main(String[] args) {
        int number_of_items = 1000; // Init number of items purchased

        //For real world currency calculations use BigDecimal
        double cost = (number_of_items * 2) / 3.0; // calculate the total

        //Printf is our friendly output formatter that rounds for you
        System.out.printf("$%,.2f\n",cost);

        // Not sure if using libraries is appropriate, should I have used Math.round()?
        // US Standards maybe say use a three decimal representation
        // as the price and just add to BigDecimal and then round?
        // I think that is what gas pumps do. . .
        /*
        Locale locale = Locale.of("en", "US"); //i18n is cool
        NumberFormat my_currency_format = NumberFormat.getCurrencyInstance(locale);
        System.out.println(my_currency_format.format(cost)); //a11y is cooler
        */
        // One can never underestimate the importance of getting monetary
        // calculations right, always ask an accountant.
    }
}
