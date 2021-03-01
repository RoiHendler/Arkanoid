package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 3
 * @ since: 24/05/2020
 */

import game.GameLevel;
import general.Direction;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import lnterface.Collidable;
import lnterface.HitListener;
import lnterface.HitNotifier;
import lnterface.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static general.Direction.BOTH;
import static general.Direction.NONE;


/**
 * @ Sprite.Block class
 * containing:
 * Geometry.Rectangle rectangle -  Sprite.Block has the same properties as rectangle shape
 * color - the color of the block
 **/
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;


    //-----------------constructors--------------------------------------------

    /**
     * @param rectangle -  Sprite.Block has the same properties as rectangle shape
     * @param color     - the color of the block
     * @ constructors for the block class
     **/
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @param topLeft     - Geometry.Point topLeft of the block (rectangle)
     * @param bottomRight - Geometry.Point bottomRight of the block (rectangle)
     * @param color       - the color of the block
     * @ constructors for the block class
     * Sprite.Block has the same properties as rectangle shape,
     * To create a rectangle you need the point top Left and bottom Right
     **/
    public Block(Point topLeft, Point bottomRight, Color color) {
        this(new Rectangle(topLeft, bottomRight), color);
    }

    /**
     * @param topLeft - Geometry.Point topLeft of the block (rectangle)
     * @param width   - Sprite.Block width (Geometry.Rectangle)
     * @param height  - Sprite.Block height (Geometry.Rectangle)
     * @param color   - the color of the block
     * @ constructors for the block class
     * Sprite.Block has the same properties as rectangle shape,
     * To create a rectangle you need the point top Left, width and height for creat
     **/
    public Block(Point topLeft, double width, double height, Color color) {
        this(new Rectangle(topLeft, width, height), color);
    }

    /**
     * @param bottomRight - Geometry.Point bottomRight of the block (rectangle)
     * @param width       - Sprite.Block width (Geometry.Rectangle)
     * @param height      - Sprite.Block height (Geometry.Rectangle)
     * @param color       - the color of the block
     * @ constructors for the block class
     * Sprite.Block has the same properties as rectangle shape,
     * To create a rectangle you need the point bottom Right, width and height for creat
     **/
    public Block(double width, double height, Point bottomRight, Color color) {
        this(new Point(bottomRight.getX() - Math.abs(width), Math.abs(height) - bottomRight.getY()),
                bottomRight, color);
    }

    /**
     * @param x      - X of the point creation
     * @param y      - Y of the point creation
     * @param width  - Sprite.Block width (Geometry.Rectangle)
     * @param height - Sprite.Block height (Geometry.Rectangle)
     * @param color  - the color of the block
     * @ constructors for the block class
     * Sprite.Block has the same properties as rectangle shape,
     * To create a rectangle you need the point (x,y), width and height for creat
     **/
    public Block(double x, double y, double width, double height, Color color) {
        this(new Rectangle(new Point(x, y), width, height), color);
    }

    //------------------------------Get Functions-------------------------------------------------

    /**
     * @return rectangle - return the rectangle
     * @ Get functions
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * @return color - return the color of the block
     * @ Get functions
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param edge - Which side of the rectangle we need
     * @return getEdge - return the edge line of the block (rectangle)
     * @ Get functions
     */
    public Line getEdge(Direction edge) {
        return rectangle.getEdge(edge);
    }

    /**
     * @return width - return the width of the block (rectangle)
     * @ Get functions
     */
    public double getWidth() {
        return rectangle.width();
    }

    /**
     * @return height - return the height of the block (rectangle)
     * @ Get functions
     */
    public double getHeight() {
        return rectangle.height();
    }

    /**
     * @return getUpperLeft - return the upper Left point of the block (rectangle)
     * @ Get functions
     */
    public Point getUpperLeft() {
        return rectangle.getUpperLeft();
    }

    /**
     * @return rectangle - return the collision rectangle rectangle (block)
     * @ Get functions
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    //-----------------------------------------------------------------------------

    /**
     * @param collisionPoint  - collision point
     * @param currentVelocity - current velocity of the ball
     * @return v - return the new velocity of the ball
     * @ The function checks if the velocity vector of the ball collides with one of the blocks,
     * If an collides occurs, the speed change proportion to the location of the collides.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Direction direction = NONE;
        this.notifyHit(hitter);
        for (Direction edge : this.rectangle.getEdges().keySet()) {
            if (this.rectangle.getEdges().get(edge).pointOnLine(collisionPoint)) {
                if (direction != NONE) {
                    direction = BOTH;
                    break;
                }
                direction = edge;
            }
        }
        Velocity v = currentVelocity.changeSign(1, 1);
        switch (direction) {
            case TOP:
            case BOTTOM:
                v = v.changeSign(1, -1);
                break;
            case LEFT:
            case RIGHT:
                v = v.changeSign(-1, 1);
                break;
            case BOTH:
                v = v.changeSign(-1, -1);
                break;
            default:
            case NONE:
                v = v.changeSign(1, 1);
                break;
        }
        return v;
    }

    /**
     * @param hitter
     * @param collisionPoint  - collision point
     * @param currentVelocity - current velocity of the ball
     * @param direction       - current velocity of the ball
     * @return v - return the new velocity of the ball
     * @ The function checks if the rectangle orbiting the ball collides with one of the blocks,
     * If an collides occurs, the speed change proportion to the location of the collides.
     */
    @Override
    public Velocity perimeterHit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Direction direction) {
        Velocity v;
        //todo check
        this.notifyHit(hitter);
        switch (direction) {
            case TOP:
            case BOTTOM:
                v = currentVelocity.changeSign(-1, 1);
                break;
            case LEFT:
            case RIGHT:
                v = currentVelocity.changeSign(1, -1);
                break;
            case BOTH:
                //
                // Random random = new Random();
                //int sign = random.nextBoolean()?1:-1;
                v = currentVelocity.changeSign(-1, -1);
                break;
            default:
            case NONE:
                v = currentVelocity.changeSign(1, 1);
                break;
        }
        return v;
    }


    /**
     * add To GameSet.Game this block.
     * @param game - are game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * add To GameSet.Game this block.
     * @param drawSurface - the surface that we draw on.
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.color);
        drawSurface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) rectangle.getHeight());
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * timePassed.
     */
    @Override
    public void timePassed() {

    }

    /***
     * remove block from game.
     * @param game - Our game area
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /***
     * add hit listener to the block.
     * @param hl - Hit listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /***
     * remove hit listener from the block.
     * @param hl - Hit listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /***
     * notify hit by the listener.
     * @param hitter - the ball that make the hit
     */
    protected void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


}
