package general;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */

/***
 * General functions of the project.
 */
public class Utilities {
    public static final double EPSILON = Math.pow(10, -4);

    /***
     * @ Compare function - Epsilon
     * @param first - the first num that we wont to Compare
     * @param second - the second num that we wont to Compare
     * @return - true or false
     */
    public static boolean compareDoubles(double first, double second) {
        return Math.abs(first - second) <= EPSILON;
    }

    /**
     * Calculate an angle based on x's and y's sign value.
     *
     *
     * @param y the y
     * @param x the x
     * @return the double
     */
    public static double calculateAngle(double y, double x) {
        double angle;
        if (x == 0) {
            if (y == 0) {
                angle = 0;
            } else if (y > 0) {
                angle = Math.PI / 2;
            } else {
                angle = -Math.PI / 2;
            }
        } else {
            angle = Math.atan(y / x);
            if (x < 0) {
                if (y > 0) {
                    angle += Math.PI;
                } else if (y < 0) {
                    angle -= Math.PI;
                } else {
                    angle = Math.PI;
                }
            }
        }
        return angle;
    }
}
