package geometry;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 3
 * @ since: 24/05/2020
 */

import general.Direction;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;


/**
 * @ Geometry.Rectangle class
 * containing:
 * width -rectangle width
 * height-rectangle height
 * upperLeft/upperRight/bottomLeft/bottomRight - point of the rectangle
 * edges - The edges of the rectangle (Arranged in map)
 * upper/bottom/left/right - the line of the rectangle
 **/
public class Rectangle {
    private double width;
    private double height;

    private Point upperLeft;
    private Point upperRight;
    private Point bottomLeft;
    private Point bottomRight;

    private Map<Direction, Line> edges;

    private Line upper;
    private Line bottom;
    private Line left;
    private Line right;

    //constructors

    /**
     * @param topLeft     -  top left point
     * @param bottomRight - bottom right point
     * @ constructors for the rectangle class
     **/
    public Rectangle(Point topLeft, Point bottomRight) {
        this.upperLeft = topLeft;
        this.bottomRight = bottomRight;
        this.edges = calcEdges(topLeft, bottomRight);
    }

    /***
     * @param upperLeft - upper left point
     * @param width - width rectangle
     * @param height - height rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.width = width;
        this.height = height;

        this.upperLeft = upperLeft;
        Point upperRight1 = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.upperRight = upperRight1;
        Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.bottomLeft = downLeft;
        Point downRight = new Point(upperRight.getX(), upperRight.getY() + height);
        this.bottomRight = downRight;

        Line upper1 = new Line(upperRight, upperLeft);
        this.upper = upper1;
        Line down = new Line(downRight, downLeft);
        this.bottom = down;
        Line left1 = new Line(upperLeft, downLeft);
        this.left = left1;
        Line right1 = new Line(upperRight, downRight);
        this.right = right1;
    }

    //------------------------------Get Functions-------------------------------------------------

    /***
     * @return the width of the rectangle
     */
    public double width() {
        return this.edges.get(Direction.TOP).length();
    }

    /***
     * @return the height of the rectangle
     */
    public double height() {
        return this.edges.get(Direction.RIGHT).length();
    }

    /***
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /***
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /***
     * @return the width and height of the rectangle
     */
    public double getWidth() {
        return Math.abs(this.upperLeft.getX() - this.bottomRight.getX());
    }

    /***
     * @return the height of the rectangle
     */
    public double getHeight() {
        return Math.abs(this.upperLeft.getY() - this.bottomRight.getY());
    }

    /***
     * @return the upper line of the rectangle
     */
    public Line getUpper() {
        return upper;
    }

    /***
     * @return the bottom line of the rectangle
     */
    public Line getBottom() {
        return bottom;
    }

    /***
     * @return the left line of the rectangle
     */
    public Line getLeft() {
        return left;
    }

    /***
     * @return the right line of the rectangle
     */
    public Line getRight() {
        return right;
    }

    /***
     * @param line -
     * @return (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        for (Line edge : this.edges.values()) {
            if (edge.isIntersecting(line)) {
                intersectionPoints.add(edge.intersectionWith(line));
            }
        }
        return intersectionPoints;
    }

    /***
     * @return the edges of the rectangle
     */
    public Map<Direction, Line> getEdges() {
        return new TreeMap<>(this.edges);
    }

    /***
     * @param edge - the specific edges that requested
     * @return specific edges of the rectangle (Arranged in map)
     */
    public Line getEdge(Direction edge) {
        return this.edges.get(edge);
    }

    /***
     * calc the edges of the rectangle.
     * @param topLeft - top left point of the rectangle
     * @param bottomRight - bottom right point of the rectangle
     * @return map that contains the edges of the rectangle
     */
    private static Map<Direction, Line> calcEdges(Point topLeft, Point bottomRight) {
        Map<Direction, Line> edges = new TreeMap<>();
        Point topRight = new Point(bottomRight.getX(), topLeft.getY());
        Point bottomLeft = new Point(topLeft.getX(), bottomRight.getY());
        edges.put(Direction.TOP, new Line(topLeft, topRight));
        edges.put(Direction.BOTTOM, new Line(bottomRight, bottomLeft));

        edges.put(Direction.LEFT, new Line(topLeft, bottomLeft));
        edges.put(Direction.RIGHT, new Line(topRight, bottomRight));
        return edges;
    }

    /***
     * draw and fill rectangle.
     * @param rect - the rectangle that we wont to draw
     * @param d - Draw surface for the drawing
     * @param color - for the rectangle
     */
    public static void fillRect(Rectangle rect, DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle(((int) rect.getUpperLeft().getX()), ((int) rect.getUpperLeft().getY()),
                ((int) rect.width), ((int) rect.height));
    }

    /***
     * draw rectangle.
     * @param rect - the rectangle that we wont to draw
     * @param d - Draw surface for the drawing
     * @param color - for the rectangle
     */
    public static void drawRect(Rectangle rect, DrawSurface d, Color color) {
        d.setColor(color);
        d.drawRectangle(((int) rect.getUpperLeft().getX()), ((int) rect.getUpperLeft().getY()),
                ((int) rect.width()), ((int) rect.height()));
    }

}
