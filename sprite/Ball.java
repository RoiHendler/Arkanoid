package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 3
 * @ since: 24/05/2020
 */

import game.GameLevel;
import game.GameEnvironment;
import general.Axis;
import general.Direction;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import lnterface.GameItem;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @ ball class
 * containing:
 * Geometry.Point center -  Geometry.Point that indicating the center of the ball
 * r -  radius of the ball
 * color - the color of the ball
 * velocity - velocity to this ball
 **/
public class Ball implements GameItem {
    private static boolean isDebugMode = false; //For testing purposes
    private static final double DIFF_PERCENT = 0.95;
    private GameEnvironment gameEnvironment;
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private boolean trajectoryHit = false;
    private int[] boundaries;
    private int lastHorizontalTouch = -1; // -1 - nothing, 0 - left, 1 - right
    private int lastVerticalTouch = -1; // -1 - nothing, 0 - up, 1 - down

    /**
     * @param center - Geometry.Point that indicating the center of the ball
     * @param r      - radius of the ball
     * @param color  - the color of the ball
     * @ constructors for the ball class
     **/
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * @param x     - Geometry.Point (score of x) that indicating the center of the ball
     * @param y     - Geometry.Point (score of y) that indicating the center of the ball
     * @param r     - radius of the ball
     * @param color - the color of the ball
     * @ constructors for the ball class
     **/
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * @return x - score of x that indicating the center of the ball
     * ceil - rounds a number up to the next largest (0.95 - 1)
     * @ accessors
     **/
    public int getX() {
        int x = (int) Math.ceil(center.getX());
        return x;
    }

    /**
     * @return y - score of x that indicating the center of the ball
     * ceil - rounds a number up to the next largest (0.95 - 1)
     * @ accessors
     **/
    public int getY() {
        int y = (int) Math.ceil(center.getY());
        return y;
    }

    /**
     * @return return the center of the
     * @ accessors
     **/
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return r - return the radius of the ball
     * @ accessors
     **/
    public int getSize() {
        return r;
    }

    /**
     * @return color - return the color of the ball
     * @ accessors
     **/
    public Color getColor() {
        return color;
    }

    /**
     * @param surface - DrawSurface that we wont to draw on
     * @ draw the ball on the given DrawSurface
     **/
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        int x = this.getX();
        int y = this.getY();
        surface.fillCircle(x, y, r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, r);
        //draw the progress vector ball on the given DrawSurface for testing purposes
        if (isDebugMode) {
            this.debugDraw(surface);
        }
    }

    /**
     * @param surface - DrawSurface that we wont to draw on
     *                For testing purposes
     *                Draws the progress vector of the ball.
     **/
    private void debugDraw(DrawSurface surface) {
        surface.setColor(Color.RED);
        Line trajectory = this.calcTrajectory();
        surface.drawLine(((int) trajectory.start().getX()), (int) trajectory.start().getY(),
                ((int) trajectory.end().getX()), ((int) trajectory.end().getY()));
        Rectangle.drawRect(this.getBallRect(), surface, Color.GREEN);
    }

    @Override
    public void timePassed() {
        this.moveOneStep(true);
    }

    /**
     * @param v - Associates the velocity to this ball
     * @ set function
     **/
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx - Associates the velocity (score of dx) to this ball
     * @param dy - Associates the velocity (score of dy) to this ball
     * @ set function
     **/
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        setVelocity(v);
    }

    /**
     * @return velocity - return the velocity of the ball
     * @ accessors
     **/
    public Velocity getVelocity() {
        return velocity;
    }


    //-------------------------------------------------------------------------
    //                      moveOneStep functions
    //-------------------------------------------------------------------------

    /**
     * moveOneStep Without accepting variables
     * moveOneStep functions moves the center of the ball to the next point in the run.
     **/
    public void moveOneStep() {
        if (this.velocity != null) {
            this.center = this.velocity.applyToPoint(this.center);
        }

    }

    /**
     * @param b - for differentiate it from the previous function.
     *          moveOneStep Without accepting variables , I send a boolean variable
     *          to differentiate it from the previous function.
     *          This moveOneStep functions moves the center of the ball to the next point in the run,
     *          by checking the progress vector of the ball, and by "drawing" a rectangle around the ball.
     *          When one of the tests returns a collision point it changes the ball speed accordingly.
     **/
    public void moveOneStep(boolean b) {
        Line trajectory = this.calcTrajectory();
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        if (info != null) {
            Velocity v = Velocity.fromAngleAndSpeed(this.velocity.getAngle(), this.r);
            v = v.changeSign(-1, -1);
            Point newCenter = v.applyToPoint(info.collisionPoint());
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
            this.center = newCenter;
            this.trajectoryHit = true;
        } else if (this.trajectoryHit) {
            this.center = this.velocity.applyToPoint(this.center);
            this.trajectoryHit = false;
        } else {
            this.velocity = this.checkPerimeter();
            this.center = this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * For the purpose of checking the ball next position.
     *
     * @return velocity - the new velocity of the ball
     **/
    private Point nextStep() {
        if (this.velocity.getSpeed() < this.r) {
            //When the velocity is less than the radius, we will add the radius of the ball
            // to the velocity for the test
            Velocity v = Velocity.fromAngleAndSpeed(this.velocity.getAngle(), this.velocity.getSpeed() + this.r);
            return v.applyToPoint(this.center);
        } else {
            return this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * Calculate and return of the ball progression vector.
     *
     * @return Geometry.Line - progression vector
     **/
    private Line calcTrajectory() {
        return new Line(this.center, this.nextStep());
    }

    /**
     * @param width  - width of the surface window
     * @param height - height of the surface window
     *               moveOneStep that gat the width and height of the surface window
     *               moveOneStep functions moves the center of the ball to the next point in the run
     **/
    public void moveOneStep(int width, int height) {
        if (this.velocity != null) {
            //The next point the center of the ball will come
            Point nextPoint = this.velocity.applyToPoint(this.center);
            double xCenter = nextPoint.getX();
            double yCenter = nextPoint.getY();
            //Check that the ball does not come off the board
            if (!(xCenter - this.r > 0 && xCenter + r <= width)) {
                this.velocity.changeHorizontalDirection();
            }
            if (!(yCenter - this.r > 0 && yCenter + r <= height)) {
                this.velocity.changeVerticalDirection();
            }
            this.moveOneStep();
        }
    }

    /**
     * @param width                - width of the surface window
     * @param height               - height of the surface window
     * @param relativeLeftTopPoint - relative Left Top Geometry.Point for cases that the surface
     *                             window is not start at immediately
     *                             moveOneStep functions moves the center of the ball to the next point in the run
     **/
    public void moveOneStep(int width, int height, Point relativeLeftTopPoint) {
        int boardStartX = (int) relativeLeftTopPoint.getX();
        int boardStartY = (int) relativeLeftTopPoint.getY();
        if (this.velocity != null) {
            //The next point the center of the ball will come
            Point nextPoint = this.velocity.applyToPoint(this.center);

            int xCenter = (int) nextPoint.getX();
            int yCenter = (int) nextPoint.getY();

            // if ball exceeding from surface, reset
            if (!(xCenter - this.r >= boardStartX && xCenter + r <= width + boardStartX)) {
                this.velocity.changeHorizontalDirection();
            }

            if (!(yCenter - this.r >= boardStartY && yCenter + r <= height + boardStartY)) {
                this.velocity.changeVerticalDirection();
            }
            this.center = this.velocity.applyToPoint(this.center);
            System.out.print(this.center.getX());
            System.out.println("," + this.center.getY());

        }
    }

    /**
     * Check if is exceeding from board.
     *
     * @param boardWidth        -Width of the board
     * @param boardHeight       - Height of the board
     * @param boardTopLeftPoint - board top left point
     * @return true / false
     **/
    private boolean isExceedingFromBoard(int boardWidth, int boardHeight, Point boardTopLeftPoint) {
        int ballCenterX = this.getX();
        int ballCenterY = this.getY();
        int radius = this.r;

        if (isExceedingFromBoardHorizontaly(boardWidth, boardHeight, boardTopLeftPoint)) {
            return true;
        }

        if (isExceedingFromBoardVerticaly(boardWidth, boardHeight, boardTopLeftPoint)) {
            return true;
        }
        return false;
    }

    /**
     * Check if is exceeding from board horizontaly.
     *
     * @param boardWidth        -Width of the board
     * @param boardHeight       - Height of the board
     * @param boardTopLeftPoint - board top left point
     * @return true / false
     * ------------------------------
     **/
    private boolean isExceedingFromBoardHorizontaly(int boardWidth, int boardHeight, Point boardTopLeftPoint) {
        int ballCenterX = this.getX();
        int radius = this.r;
        if (ballCenterX - radius <= boardTopLeftPoint.getX() || ballCenterX + radius >= boardTopLeftPoint.getX()
                + boardWidth) {
            return true;
        }
        return false;
    }

    /**
     * Check if is exceeding from board verticaly.
     *
     * @param boardWidth        -Width of the board
     * @param boardHeight       - Height of the board
     * @param boardTopLeftPoint - board top left point
     * @return true / false
     * -------------------------------------------
     **/
    private boolean isExceedingFromBoardVerticaly(int boardWidth, int boardHeight, Point boardTopLeftPoint) {
        int ballCenterY = this.getY();
        int radius = this.r;
        if (ballCenterY - radius <= boardTopLeftPoint.getY() || ballCenterY + radius >= boardTopLeftPoint.getY()
                + boardHeight) {
            return true;
        }
        return false;
    }

    /**
     * @param isDM - true / false
     *             depends on whether we want to run the drawing (of the test)
     *             Starting the Debug Mode that draw the progress vector ball on the given DrawSurface
     *             for testing purposes.
     **/
    public static void setDebugMode(boolean isDM) {
        Ball.isDebugMode = isDM;
    }

    /**
     * @param start - the position obtained from the point
     * @param dx    - given speed
     * @param dy    - given speed
     *              Draws the animation according to the position obtained from the point
     *              And moves it during the run at the given speed
     **/
    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        int surfaceWidth = gui.getDrawSurface().getWidth();
        int surfaceHeight = gui.getDrawSurface().getHeight();
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep(surfaceWidth, surfaceHeight);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Set function
     * Shows the ball the overall game.
     *
     * @param gameEnviron - the gameEnvironment to are game
     **/
    public void setGameEnvironment(GameEnvironment gameEnviron) {
        this.gameEnvironment = gameEnviron;
    }

    /**
     * Get function
     * return the gameEnvironment of the ball.
     *
     * @return gameEnvironment - the gameEnvironment that we are game
     **/
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * add the ball to the game.
     *
     * @param game - the game
     **/
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.giveEnvToBall(this);
    }

    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /***
     * A function that checks the ball collision with objects.
     * @return A rectangle is drawn around the ball
     */
    private Rectangle getBallRect() {
        double diff = this.r * DIFF_PERCENT;
        return new Rectangle(this.center.shiftPoint(-diff, Axis.XY), this.center.shiftPoint(diff, Axis.XY));
    }

    /***
     * check Perimeter of the ball.
     * @return - new velocity - When the ball collides
     */
    private Velocity checkPerimeter() {
        Rectangle ballRect = this.getBallRect();
        Direction direction = Direction.NONE;
        List<CollisionInfo> collisionInfoList = new ArrayList<>();
        for (Direction side : ballRect.getEdges().keySet()) {
            Line edge = ballRect.getEdge(side);
            CollisionInfo info = this.gameEnvironment.getClosestCollision(edge);
            if (info != null) {
                if (direction != Direction.NONE && !Direction.parallelDirections(direction, side)) {
                    direction = Direction.BOTH;
                    break;
                }
                collisionInfoList.add(info);
                direction = side;
            }
        }
        Velocity v;
        if (collisionInfoList.isEmpty()) {
            v = this.velocity.changeSign(1, 1);
        } else {
            CollisionInfo info = collisionInfoList.get(0);
            v = info.collisionObject().perimeterHit(this, info.collisionPoint(), this.velocity, direction);
        }
        return v;
    }
}
