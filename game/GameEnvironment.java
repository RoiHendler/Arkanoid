package game;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import lnterface.Collidable;
import sprite.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ GameEnvironment class
 * containing:
 **/
public class GameEnvironment {
    private List<Collidable> collidables;

    /***
     *@ constructors for the GameSet.GameEnvironment class
     * Creating a new list
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /***
     * add the given collidable to the environment.
     * @param c - the collidable that we want to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /***
     * remove the given collidable to the environment.
     * @param c - the collidable that we want to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /***
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - The line we want to check with
     * @return Sprite.CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> infoList = new ArrayList<>();
        Point intersection;
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            intersection = trajectory.closestIntersectionToStartOfLine(rect);
            if (intersection != null) {
                infoList.add(new CollisionInfo(intersection, c));
            }
        }
        if (infoList.isEmpty()) {
            return null;
        } else {
            CollisionInfo bestInfo = infoList.get(0);
            infoList.remove(0);
            for (CollisionInfo info : infoList) {
                if (info.collisionPoint().distance(trajectory.start())
                        < bestInfo.collisionPoint().distance(trajectory.start())) {
                    bestInfo = info;
                }
            }
            return bestInfo;
        }
    }
}

