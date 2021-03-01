package geometry;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 3
 * @ since: 24/05/2020
 */

import general.Direction;
import general.Utilities;

import java.util.ArrayList;
import java.util.List;
/**
 * @ Geometry.Line class
 * containing:
 * Geometry.Point start - start point of the line
 * Geometry.Point end - end point of the line
 * Double a, b - from ax+b math
 * isVertical - if the line vertical
 **/
public class Line {
    private Point start;
    private Point end;
    private Double a, b; // y=ax+b
    private boolean isVertical;

    /**
     * @param start get Geometry.Point start and Geometry.Point end and creat a new line
     * @param end   get Geometry.Point start and Geometry.Point end and creat a new line
     * @ constructors for the Geometry.Line class
     **/
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.intializeLine();
    }

    /**
     * @param x1 get values of Geometry.Point start and values of Geometry.Point end
     * @param x2 get values of Geometry.Point start and values of Geometry.Point end
     * @param y1 get values of Geometry.Point start and values of Geometry.Point end
     * @param y2 get values of Geometry.Point start and values of Geometry.Point end
     *           and creat a new points and a new line
     * @ another constructors for the Geometry.Line class
     **/
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return length the length of the line
     **/
    public double length() {
        double length = start.distance(end);
        return length;
    }

    /**
     * @return middlePoint the middle point of the line
     **/
    public Point middle() {
        Point middlePoint = new Point(
                (this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
        return middlePoint;
    }
    //------------------------------Get Functions-------------------------------------------------
    /**
     * @return the start point of the line
     **/
    public Point start() {
        return start;
    }

    /**
     * @return the end point of the line
     **/
    public Point end() {
        return end;
    }
    /**
     * @return the a from ax+b math
     **/
    public Double getA() {
        return a;
    }
    /**
     * @return the b from ax+b math
     **/
    public Double getB() {
        return b;
    }
    /**
     * @return if the line is vertical
     **/
    public boolean isVertical() {
        return isVertical;
    }
    //-------------------------------------------------------------

    /**
     * @return the gradient
     * @ Calculate the gradient - A
     **/
    private double calculateGradient() {
        double x1, y1, x2, y2, gradient;
        x1 = start.getX();
        y1 = start.getY();
        x2 = end.getX();
        y2 = end.getY();
        gradient = (y2 - y1) / (x2 - x1);
        return gradient;
    }

    /**
     * @return the Free variable
     * @ Free variable calculation - B
     **/
    private double freeVariable() {
        double x1, y1, freeVariable;
        x1 = start.getX();
        y1 = start.getY();
        double m = calculateGradient();
        freeVariable = y1 - (m * x1);
        return freeVariable;
    }

    /**
     * @param other - the other line that we check
     * @return true if the lines intersect, false otherwise
     * @ Check if the lines intersect
     **/
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * @param other - the other line that we check
     * @return the intersection point if the lines intersect, and null otherwise.
     * @ Check where the lines intersect
     **/
    public Point intersectionWith(Line other) {
        Point intersection = null;
        double x, y;
        if (this.isVertical && other.isVertical) {
            return null;
        } else if (this.isVertical) {
            x = this.start.getX();
            y = other.a * x + other.b;
            intersection = new Point(x, y);
        } else if (other.isVertical) {
            x = other.start.getX();
            y = this.a * x + this.b;
            intersection = new Point(x, y);
        } else {
            x = (this.b - other.b) / (other.a - this.a);
            y = this.a * x + this.b;
            intersection = new Point(x, y);
        }
        return this.pointOnLine(intersection) && other.pointOnLine(intersection) ? intersection : null;
    }
    /**
     * @param p - point
     * @return true or false
     * @ Check if the point on line
     **/
    public boolean pointOnLine(Point p) {
        double length = this.start.distance(p) + p.distance(this.end);
        return Utilities.compareDoubles(this.length(), length);
    }

    /**
     * @param other - the other line that we check
     * @return true if the lines equals, false otherwise
     * @ Check if the lines equals
     **/
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * @param rect - the rectangle that we check with
     * @return the closest point intersection to start of line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = new ArrayList<>();
        for (Direction edge : rect.getEdges().keySet()) {
            if (this.isIntersecting(rect.getEdge(edge))) {
                pointList.add(this.intersectionWith(rect.getEdge(edge)));
            }
        }
        if (pointList.isEmpty()) {
            return null;
        } else {
            Point bestPoint = pointList.get(0);
            pointList.remove(0);
            for (Point p : pointList) {
                if (p.distance(this.start) < bestPoint.distance(this.start)) {
                    bestPoint = p;
                }
            }
            return bestPoint;
        }
    }

    /***
     * Auxiliary function.
     */
    private void intializeLine() {
        this.calcSlope();
        this.calcB();
    }

    /***
     *Auxiliary function - that Calculates Slope.
     */
    private void calcSlope() {
        if (Utilities.compareDoubles(this.start.getX(), this.end.getX())) {
            this.isVertical = true;
            this.a = null;
        } else {
            this.a = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            this.isVertical = false;
        }
    }
    /***
     *Auxiliary function - that Calculates the free variable
     * b from ax+b.
     */
    private void calcB() {
        if (!this.isVertical) {
            double x1, y1;
            x1 = start.getX();
            y1 = start.getY();
            this.b = y1 - (this.a * x1);
        } else {
            this.b = null;
        }
    }
    }

    // Auxiliary function
/**
    public Geometry.Line shiftLine(double shiftSize, General.Axis axis) {
        return new Geometry.Line(this.start.shiftPoint(shiftSize, axis), this.end.shiftPoint(shiftSize, axis));
    }
}
 **/
