package lnterface;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */
import sprite.Block;
import sprite.Velocity;
import java.awt.Color;
import java.util.List;
/***
 * LevelInformation interface.
 */
public interface LevelInformation {
    /***
     * Get Function
     * number Of Balls in the levels.
     * @return number Of Balls
     */
    int numberOfBalls();

    /***
     * The initial velocity of each ball.
     * list of all the Velocity in the levels.
     * @return list of all the Velocity
     */
    List<Velocity> initialBallVelocities();

    /***
     * the speed of the paddle in the levels.
     * @return the Speed of the paddle
     */
    int paddleSpeed();
    /***
     * the width of the paddle in the Green3 level.
     * @return  the width of the paddle
     */
    int paddleWidth();
    /***
     * the level name will be displayed at the top of the screen.
     * @return  the level Name - Green3
     */
    String levelName();

    /***
     *  The Blocks that make up this level, each block contains its size, color and location.
     * @return a list of all the blocks in this level
     */
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /***
     * Get Function.
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
    /***
     * Get Function.
     * @return the color of the paddle
     */
    Color paddleColor();
    /***
     * Get Function
     * the Background color in the Green3 level.
     * @return the Background color
     */
     Color getBackgroundColor();


}