package game.levels;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
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
 * The Green3 level.
 */
public class Green3 implements LevelInformation {

    private int heightOfBlocks = 25;
    private int widthOfBlocks = 50;
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks = new ArrayList<>();
    private Color paddleColor;
    private List<Velocity> velocities = new ArrayList<>();
    private Color backgroundColorBlock;

    /***
     * Characteristics of Green3 level.
     */
    public Green3() {
        this.paddleSpeed = 10;
        this.paddleWidth = 250;
        this.paddleColor = Color.YELLOW;
        this.backgroundColorBlock = Color.green.darker();
        this.levelName = "Green3";
        //  set Velocity
        velocities.add(Velocity.fromAngleAndSpeed(310, 5));
        velocities.add(Velocity.fromAngleAndSpeed(40, 5));
        this.numberOfBalls = velocities.size();
        //  set Block
        for (int i = 0; i < 10; i++) {
            Block b = new Block(725 - i * widthOfBlocks, 140, widthOfBlocks, heightOfBlocks, Color.GRAY);
            blocks.add(b);
        }
        for (int i = 0; i < 9; i++) {
            Block b = new Block(725 - i * widthOfBlocks, 165, widthOfBlocks, heightOfBlocks, Color.RED);
            blocks.add(b);
        }
        for (int i = 0; i < 8; i++) {
            Block b = new Block(725 - i * widthOfBlocks, 190, widthOfBlocks, heightOfBlocks, Color.YELLOW);
            blocks.add(b);
        }
        for (int i = 0; i < 7; i++) {
            Block b = new Block(725 - i * widthOfBlocks, 215, widthOfBlocks, heightOfBlocks, Color.BLUE);
            blocks.add(b);
        }
        for (int i = 0; i < 6; i++) {
            Block b = new Block(725 - i * widthOfBlocks, 240, widthOfBlocks, heightOfBlocks, Color.WHITE);
            blocks.add(b);
        }
    }

    /***
     * Get Function
     * number Of Balls in the Green3 level.
     * @return number Of Balls
     */
    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }
    /***
     * list of all the Velocity in the Green3 level.
     * @return list of all the Velocity
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }
    /***
     * the speed of the paddle in the Green3 level.
     * @return the Speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }
    /***
     * the width of the paddle in the Green3 level.
     * @return  the width of the paddle
     */
    @Override
    public int paddleWidth() {
        return paddleWidth;
    }
    /***
     * Get Function.
     * @return  the level Name - Green3
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

    /***
     * Get Function
     * the Background color in the Green3 level.
     * @return the Background color
     */
    @Override
    public Color getBackgroundColor() {
        return backgroundColorBlock;
    }
}



