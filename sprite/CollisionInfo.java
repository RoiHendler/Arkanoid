package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */

import geometry.Point;
import lnterface.Collidable;

/**
 * This program Contains the ball collision data.
 **/
public class CollisionInfo {
    // the point at which the collision occurs.
    private Point collisionPoint;
    private Collidable block;

    /**
     * @param collisionPoint - collision Geometry.Point
     * @param block          - collidable type
     * @ constructors for the block class
     **/
    public CollisionInfo(Point collisionPoint, Collidable block) {
        this.collisionPoint = collisionPoint;
        this.block = block;
    }

    /**
     * @return collisionPoint - return the collision Geometry.Point
     * @ Get
     **/
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return block - return the collision type
     * @ Get the collidable object involved in the collision.
     **/
    public Collidable collisionObject() {
        return block;
    }
}
