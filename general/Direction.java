package general;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */
/**
 * This program Contains the ball collision data.
 **/
public enum Direction {
    LEFT, TOP, RIGHT, BOTTOM, BOTH, NONE;

    /***
     * Checks whether the following two lines are parallel.
     * @param first - the first direction
     * @param second - the second direction
     * @return - false / true - if is parallel directions
     */
    public static boolean parallelDirections(Direction first, Direction second) {
        if (first == NONE || second == NONE) {
            return false;
        }
        if (first == BOTH || second == BOTH) {
            return false;
        }
        if (first == second) {
            return true;
        }
        if (first == LEFT || first == RIGHT) {
            return second == RIGHT || second == LEFT;
        }
        if (first == TOP || first == BOTTOM) {
            return second == BOTTOM || second == TOP;
        }
        return false;
    }
}
