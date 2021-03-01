package geometry;
import general.Axis;
import general.Utilities;

/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */
public class Point {
    private double x;
    private double y;


    /**
     * constructors for the point class.
     * @param x - score of x for the point
     * @param y - score of y for the point
     **/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance - return the distance of this point to the other point.
     * @param other - The second ball from which the distance is checked
     * @return distance - return the distance grom the 2 ball
     **/
    public double distance(Point other) {
        double distanceX, distanceY, distance = 0;
        distanceX = Math.abs(this.x - other.x);
        distanceY = Math.abs(this.y - other.y);
        distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distance;
    }

    /**
     * equals - return the distance of this point to the other point.
     * @param other - The second ball from which the distance is checked
     * @return false/true - true is the points are equal, false otherwise
     **/
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Utilities.compareDoubles(this.x, other.x) && Utilities.compareDoubles(this.y, other.y);
    }

    /**
     * accessors.
     * @return this.x - the x of this point
     **/
    public double getX() {
        return this.x;
    }

    /**
     * accessors.
     * @return this.y - the  y of this point
     **/
    public double getY() {
        return this.y;
    }

    /***
     * Sliding function
     * The function moves the midrange variable to the required length.
     * @param shiftSize required length to the shift
     * @param axis - Which way to move
     * @return - the new point (after the move)
     */
    public Point shiftPoint(double shiftSize, Axis axis) {
        Point shiftedPoint;
        switch (axis) {
            case X:
                shiftedPoint = new Point(this.x + shiftSize, this.y);
                break;
            case Y:
                shiftedPoint = new Point(this.x, this.y + shiftSize);
                break;
            default:
            case XY:
            case YX:
                shiftedPoint = new Point(this.x + shiftSize, this.y + shiftSize);
                break;
        }
        return shiftedPoint;
    }

}