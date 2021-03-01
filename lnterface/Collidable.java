package lnterface;
import geometry.Point;
import geometry.Rectangle;
import sprite.Velocity;
import general.Direction;
import sprite.Ball;

/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 4
 * @ since: 11/06/2020
 */
public interface Collidable {


    /**
     * @ interface
     * @return collision rectangle
     * Return the "collision shape" of the object.
     **/
    Rectangle getCollisionRectangle();

    /**
     * @param hitter - the ball that make the hit
     * @param collisionPoint  - collision point
     * @param currentVelocity - current velocity of the ball
     * @return is the new velocity expected after the hit (based on the force the object inflicted on us).
     * @ interface
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     **/
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * @param hitter - the ball that make the hit
     * @param collisionPoint  - collision point
     * @param currentVelocity - current velocity of the ball
     * @param direction       - current velocity of the ball
     * @return v - return the new velocity of the ball
     * @ interface
     * The function checks if the rectangle orbiting the ball collides with one of the blocks,
     * If an collides occurs, the speed change proportion to the location of the collides.
     */
    Velocity perimeterHit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Direction direction);

    //Velocity hit(Point collisionPoint, Velocity velocity);
}
