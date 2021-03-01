package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 3
 * @ since: 24/05/2020
 */

import general.Utilities;
import geometry.Point;

/**
 * Sprite.Velocity class contains speed on the X axis and velocity on the Y axis.
 **/
public class Velocity {
    // Sprite.Velocity specifies the change in position on the `x` and the `y` axes.
    private double dx;
    private double dy;

    /**
     * @param dx - Sprite.Velocity on dx
     * @param dy - Sprite.Velocity on dy
     * @ constructors for the ball class.
     **/
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p - The old velocity point
     * @return newPoint - The new point with the new velocity
     **/
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        Point newPoint = new Point(newX, newY);
        return newPoint;
    }

    /**
     * get function.
     *
     * @return VelocityX - return the velocity in X position.
     **/
    public double getVelocityX() {
        return this.dx;
    }

    /**
     * get function.
     *
     * @return VelocityY - return the velocity in Y position
     **/
    public double getVelocityY() {
        return this.dy;
    }

    /**
     * Changes the speed of the ball by changing the direction of the speed ( Y position).
     **/
    public void changeVerticalDirection() {
        this.dy = -1 * this.dy;
    }

    /**
     * Changes the speed of the ball by changing the direction of the speed ( X position).
     **/
    public void changeHorizontalDirection() {
        this.dx = -1 * this.dx;
    }

    /***
     * change the sign - for the velocity.
     * @param x - the velocity (X axis)
     * @param y -  the velocity (Y axis)
     * @return the new velocity
     */
    public Velocity changeSign(double x, double y) {
        if (x == 0) {
            x = 1;
        }
        if (y == 0) {
            y = 1;
        }
        return new Velocity(this.dx * Math.signum(x), this.dy * Math.signum(y));
    }

    /**
     * Changes the speed of the ball by changing the velocity direction, by changing the angle of rotation.
     *
     * @param angle - angle
     * @param speed - speed
     * @return velocity - new speed
     **/
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy;
        dx = Math.sin(Math.toRadians(angle)) * speed;
        dy = Math.cos(Math.toRadians(angle - 180)) * speed;
        Velocity velocity = new Velocity(dx, dy);
        return velocity;
    }

    /***
     * @return specific speed - 5
     */
    public double getSpeed() {
        //Math.hypot(this.dx, this.dy);
        return 5;
    }

    /***
     * @return the angle of the ball.
     */
    public double getAngle() {
        double angle = Math.toDegrees(Utilities.calculateAngle(this.dx, -this.dy));
        return angle;
    }
}
