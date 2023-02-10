/***************
 * GREAT CIRCLE
 * ------------
 * ERIC SEYDEN
 * 2023-01-09
 **************/
public class GreatCircle {
    public static void main(String[] args) {
        double r = 6371.0; // Radius of Earth
        double x1 = Math.toRadians(Double.parseDouble(args[0])); // convert latitude 1 to radians
        double y1 = Math.toRadians(Double.parseDouble(args[1])); // convert longitude 1 to radians
        double x2 = Math.toRadians(Double.parseDouble(args[2]));
        double y2 = Math.toRadians(Double.parseDouble(args[3]));

        //Process using the Haversine formula

        double diff = x2 - x1;
        double halfDiff = diff / 2;
        double sin = Math.sin(halfDiff);
        double xDiffSinSquared = Math.pow(sin,2);

        diff = y2 - y1;
        halfDiff = diff / 2;
        sin = Math.sin(halfDiff);
        double yDiffSinSquared = Math.pow(sin,2);

        double rightProduct = Math.cos(x1) * Math.cos(x2) * yDiffSinSquared;
        double squareRootValue = Math.sqrt(xDiffSinSquared + rightProduct);
        double distance = 2 * r * Math.asin(squareRootValue);

        System.out.println(distance + " kilometers");
    }
}
