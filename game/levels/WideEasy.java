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
 * The WideEasy level.
 */
public class WideEasy implements LevelInformation {

    private static final double WIDTHBLOCKBORDER = 25;
    private static final double HEIGHTYBLOCK = 200;
    private int heightOfBlocks = 25;
    private int widthOfBlocks = 50;
    private int numberOfBalls;
    private List<Velocity> velocities = new ArrayList<>();
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Color paddleColor;
    private List<Block> blocks = new ArrayList<>();
    private Color backgroundColorBlock;

    /***
     * Characteristics of WideEasy level.
     */
    public WideEasy() {
        this.paddleSpeed = 6;
        this.paddleWidth = 400;
        this.paddleColor = Color.YELLOW;
        this.levelName = "WideEasy";
        this.backgroundColorBlock = Color.white;
        // set Velocity
        velocities.add(Velocity.fromAngleAndSpeed(310, 5));
        velocities.add(Velocity.fromAngleAndSpeed(320, 5));
        velocities.add(Velocity.fromAngleAndSpeed(330, 5));
        velocities.add(Velocity.fromAngleAndSpeed(340, 5));
        velocities.add(Velocity.fromAngleAndSpeed(350, 5));
        velocities.add(Velocity.fromAngleAndSpeed(10, 5));
        velocities.add(Velocity.fromAngleAndSpeed(20, 5));
        velocities.add(Velocity.fromAngleAndSpeed(30, 5));
        velocities.add(Velocity.fromAngleAndSpeed(40, 5));
        velocities.add(Velocity.fromAngleAndSpeed(50, 5));
        this.numberOfBalls = velocities.size();
        //Create blocks
        int counterInX = 0;
        for (int i = 0; i < 2; i++) {
            Block block = new Block(counterInX * widthOfBlocks + WIDTHBLOCKBORDER, HEIGHTYBLOCK,
                    widthOfBlocks, heightOfBlocks, Color.RED);
            blocks.add(block);
            counterInX++;
        }
        for (int i = 0; i < 2; i++) {
            Block block = new Block(counterInX * widthOfBlocks + WIDTHBLOCKBORDER, HEIGHTYBLOCK,
                    widthOfBlocks, heightOfBlocks, Color.ORANGE);
            blocks.add(block);
            counterInX++;
        }
        for (int i = 0; i < 2; ++i) {
            Block block = new Block(counterInX * widthOfBlocks + WIDTHBLOCKBORDER, HEIGHTYBLOCK,
                    widthOfBlocks, heightOfBlocks, Color.YELLOW);
            blocks.add(block);
            counterInX++;
        }
        for (int i = 0; i < 3; i++) {
            Block block = new Block(counterInX * widthOfBlocks + WIDTHBLOCKBORDER, HEIGHTYBLOCK,
                    widthOfBlocks, heightOfBlocks, Color.GREEN);
            blocks.add(block);
            counterInX++;
        }
        for (int i = 0; i < 2; i++) {
            Block block = new Block(counterInX * widthOfBlocks + WIDTHBLOCKBORDER, HEIGHTYBLOCK,
                    widthOfBlocks, heightOfBlocks, Color.BLUE);
            blocks.add(block);
            counterInX++;
        }
        for (int i = 0; i < 2; i++) {
            Block block = new Block(counterInX * widthOfBlocks + WIDTHBLOCKBORDER, HEIGHTYBLOCK,
                    widthOfBlocks, heightOfBlocks, Color.PINK);
            blocks.add(block);
            counterInX++;
        }
        for (int i = 0; i < 2; i++) {
            Block block = new Block(counterInX * widthOfBlocks + WIDTHBLOCKBORDER, HEIGHTYBLOCK,
                    widthOfBlocks, heightOfBlocks, Color.CYAN);
            blocks.add(block);
            counterInX++;
        }
    }
    /***
     * Get Function.
     * number Of Balls in the WideEasy level
     * @return number Of Balls
     */
    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }
    /***
     * list of all the Velocity in the WideEasy level.
     * @return list of all the Velocity
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return velocities;
    }
    /***
     * the speed of the paddle in the WideEasy level.
     * @return the Speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }
    /***
     * the width of the paddle in the WideEasy level.
     * @return  the width of the paddle
     */
    @Override
    public int paddleWidth() {
        return paddleWidth;
    }
    /***
     * Get Function.
     * @return  the level Name - WideEasy
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
        return blocks().size();
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
     * the Background color in the WideEasy level.
     * @return the Background color
     */
    @Override
    public Color getBackgroundColor() {
        return backgroundColorBlock;
    }

}

