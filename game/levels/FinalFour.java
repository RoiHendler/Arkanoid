package game.levels;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 4
 * @ since: 11/06/2020
 */

import lnterface.LevelInformation;
import sprite.Block;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The FinalFour level.
 */
public class FinalFour implements LevelInformation {

    private List<Block> blocks = new ArrayList<>();
    private int paddleWidth;
    private int numberOfBalls;
    private List<Velocity> velocities = new ArrayList<>();
    private int paddleSpeed;
    private String levelName;
    private Color paddleColor;
    private Color backgroundColorBlock;
    private int heightOfBlocks = 25;
    private int widthOfBlocks = 50;

    /***
     * Characteristics of FinalFour level.
     */
    public FinalFour() {
        this.paddleSpeed = 7;
        this.paddleWidth = 90;
        this.paddleColor = Color.YELLOW;
        this.levelName = "FinalFour";
        this.backgroundColorBlock = Color.BLUE;
        //  set Velocity
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        velocities.add(Velocity.fromAngleAndSpeed(330, 5));
        velocities.add(Velocity.fromAngleAndSpeed(30, 5));
        this.numberOfBalls = 3;
        // set Blocks
        for (int x = 25; x <= 751; x += 50) {
            blocks.add(new Block(x, 110, widthOfBlocks, heightOfBlocks, Color.GRAY));
            blocks.add(new Block(x, 135, widthOfBlocks, heightOfBlocks, Color.RED));
            blocks.add(new Block(x, 160, widthOfBlocks, heightOfBlocks, Color.YELLOW));
            blocks.add(new Block(x, 185, widthOfBlocks, heightOfBlocks, Color.GREEN));
            blocks.add(new Block(x, 210, widthOfBlocks, heightOfBlocks, Color.WHITE));
            blocks.add(new Block(x, 235, widthOfBlocks, heightOfBlocks, Color.pink));
            blocks.add(new Block(x, 260, widthOfBlocks, heightOfBlocks, Color.cyan));
        }
    }

    /***
     * Get Function
     * number Of Balls in the FinalFour level.
     * @return number Of Balls
     */
    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /***
     * list of all the Velocity in the FinalFour level.
     * @return list of all the Velocity
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }

    /***
     * the speed of the paddle in the FinalFour level.
     * @return the Speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /***
     * the width of the paddle in the FinalFour level.
     * @return the width of the paddle
     */
    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    /***
     * Get Function
     * the Background color in the FinalFour level.
     * @return the Background color
     */
    @Override
    public Color getBackgroundColor() {
        return backgroundColorBlock;
    }

    /***
     * Get Function.
     * @return the level Name - FinalFour
     */
    @Override
    public String levelName() {
        return levelName;
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


