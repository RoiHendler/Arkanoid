package game.levels;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 4
 * @ since: 11/06/2020
 */

import geometry.Point;
import lnterface.LevelInformation;
import sprite.Block;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Direct hit level.
 */
public class DirectHit implements LevelInformation {

    private static final double START_X = 800 / 2;
    private static final double START_Y = 600 / 3;
    private int numberOfBalls;
    private List<Velocity> velocities = new ArrayList<Velocity>();
    private int paddleSpeed;
    private List<Block> blocks = new ArrayList<>();
    private Color paddleColor;
    private int widthPaddel;
    private String levelName;
    private Color backgroundColorBlock;


    /***
     * Characteristics of Direct hit level.
     */
    public DirectHit() {
        int size = 50;
        this.backgroundColorBlock = Color.BLACK;
        this.paddleSpeed = 11;
        this.widthPaddel = 50;
        this.paddleColor = Color.YELLOW;
        this.levelName = "Direct Hit";
        // set Velocity
        velocities.add(Velocity.fromAngleAndSpeed(0, 10));
        this.numberOfBalls = 1;
        // set Block
        Block block = new Block(new Point(START_X - 20, START_Y), 50, 50, Color.RED);
        blocks.add(block);
    }

    /***
     * Get Function
     * number Of Balls in the Direct hit level.
     * @return number Of Balls
     */
    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /***
     * Get Function
     * list of all the Velocity in the Direct hit level.
     * @return list of all the Velocity
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    /***
     * Get Function
     * the speed of the paddle in the Direct hit level.
     * @return the Speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /***
     * Get Function
     * the width of the paddle in the Direct hit level.
     * @return the width of the paddle
     */
    @Override
    public int paddleWidth() {
        return widthPaddel;
    }

    /***
     * Get Function.
     * @return the level Name - Direct hit
     */
    @Override
    public String levelName() {
        return levelName;
    }

    /***
     * Get Function
     * the Background color in the Direct hit level.
     * @return the Background color
     */
    @Override
    public Color getBackgroundColor() {
        return backgroundColorBlock;
    }


    /***
     * Get Function.
     * @return a list of all the blocks in this level
     */
    @Override
    public List<Block> blocks() {
        return blocks;
    }

    /***
     * Get Function.
     * @return the number of blocks to remove
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }

    /***
     * Get Function.
     * @return the color of the paddle
     */
    @Override
    public Color paddleColor() {
        return paddleColor;
    }

}
