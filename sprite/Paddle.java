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
import lnterface.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


/**
 * @ class Sprite.Paddle
 * containing:
 * KeyboardSensor keyboard -  the keyboard for move the paddle
 * Sprite.Block block - the color of the block
 * heightPaddle - the height of the paddle
 * widthPaddle - the width of the paddle
 **/
public class Paddle implements Sprite, Collidable {
    private static final double PADDLE_MOVEMENT = 5;

    private KeyboardSensor keyboard;
    private Block block;
    private double heightPaddle;
    private double widthPaddle;
    private double paddleSpeed;

    /**
     * @param keyboard -  the keyboard for move the paddle
     * @param block    - The padel is actually a kind of "block"
     * @ constructors for the Paddle class
     **/
    public Paddle(KeyboardSensor keyboard, Block block) {
        this(keyboard, block, PADDLE_MOVEMENT);
    }

    /***
     * @ constructors for the Paddle class
     * @param keyboard -  the keyboard for move the paddle
     * @param block    - The padel is actually a kind of "block"
     * @param paddleSpeed - the Speed of the paddle
     */
    public Paddle(KeyboardSensor keyboard, Block block, double paddleSpeed) {
        this.keyboard = keyboard;
        this.block = block;
        this.heightPaddle = block.getHeight();
        this.widthPaddle = block.getWidth();
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * @ Moves to the left the paddle
     * In addition, the function checks that the paddle does not exceed the screen boundaries
     **/
    public void moveLeft() {
        boolean legalMove = false;
        Point upperLeft = this.block.getRectangle().getUpperLeft();
        double upperLeftNewX = upperLeft.getX() - this.paddleSpeed;
        Point upperLeftNew;
        if (upperLeftNewX > 25) {
            legalMove = true;
        }
        if (legalMove) {
            upperLeftNew = new Point(upperLeftNewX, upperLeft.getY());
        } else {
            upperLeftNew = new Point(25, upperLeft.getY());
        }
        this.block = new Block(upperLeftNew, widthPaddle, heightPaddle, block.getColor());


    }

    /**
     * @ Moves to the right the paddle
     * In addition, the function checks that the paddle does not exceed the screen boundaries
     **/
    public void moveRight() {
        boolean legalMove = false;
        Point upperLeft = this.block.getRectangle().getUpperLeft();
        Point upperRight = this.block.getRectangle().getUpperRight();

        double upperLeftNewX = upperLeft.getX() + this.paddleSpeed;
        double upperRightNewX = upperRight.getX() + this.paddleSpeed;
        Point upperLeftNew;
        if (upperRightNewX < 775) {
            legalMove = true;
        }
        if (legalMove) {
            upperLeftNew = new Point(upperLeftNewX, upperLeft.getY());
        } else {
            upperLeftNew = new Point(775 - this.widthPaddle, upperLeft.getY());
        }
        this.block = new Block(upperLeftNew, widthPaddle, heightPaddle, block.getColor());
    }

    /**
     * @ time passed - Interface.Sprite
     * Moving the paddle whan the time passed
     **/
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * @param d - Draw Surface
     * @ draw on the paddle
     **/
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    /**
     * Interface.Collidable.
     *
     * @return collision rectangle
     **/
    public Rectangle getCollisionRectangle() {
        return this.block.getRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        return hit(collisionPoint, currentVelocity);
    }

    /***
     * @param collisionPoint  - collision point
     * @param currentVelocity - current velocity of the ball
     * @return - the new velocity - after the hit
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (!this.block.getEdge(Direction.TOP).pointOnLine(collisionPoint)) {
            return this.block.hit(null, collisionPoint, currentVelocity);
        } else {
            return this.topEdgeHit(collisionPoint, currentVelocity);
        }
    }

    /***
     * if the ball hit in the top of the paddle.
     * @param collisionPoint - the collision point with the ball
     * @param currentVelocity - the current velocity of the ball
     * @return the new velocity for the ball
     */
    private Velocity topEdgeHit(Point collisionPoint, Velocity currentVelocity) {
        double sectorSize = this.block.getWidth() / 5;
        double hitSpotX = Math.abs(collisionPoint.getX() - this.block.getUpperLeft().getX());
        Velocity v;
        //leftSection
        if (hitSpotX >= 0 && hitSpotX < sectorSize) {
            v = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        } else if (hitSpotX >= sectorSize && hitSpotX < sectorSize * 2) {
            v = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        } else if (hitSpotX >= sectorSize * 2 && hitSpotX < sectorSize * 3) {
            v = Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
        } else if (hitSpotX >= sectorSize * 3 && hitSpotX < sectorSize * 4) {
            v = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        } else {
            v = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        return v;
    }

    /***
     * @param hitter - the ball that make the hit.
     * @param collisionPoint  - collision point
     * @param currentVelocity - current velocity of the ball
     * @param direction       - the direction that change the ball
     * @return -
     */
    @Override
    public Velocity perimeterHit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Direction direction) {
        return this.hit(collisionPoint, currentVelocity);
    }

    /***
     *@ Add this paddle to the game.
     * @param g - the game that we play on
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /***
     * getthe edge of the paddle.
     * @param edge - the edge
     * @return edge of the paddle
     */
    public Line getEdge(Direction edge) {
        return block.getEdge(edge);
    }
}
