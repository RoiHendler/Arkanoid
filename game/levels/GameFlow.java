package game.levels;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 4
 * @ since: 11/06/2020
 */
import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Point;
import lnterface.LevelInformation;
import sprite.Counter;
import sprite.EndScreen;

import java.util.List;
/**
 * The Game Flow level.
 */
public class GameFlow {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter pointsCounter;
    private boolean won;

    /***
     * @ constructors for the GameFlow class
     * @param ar - the animation runner
     * @param ks - the keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.pointsCounter = new Counter(0);
    }

    /***
     * This function runs all the levels in the list.
     * @param levels -list with all the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        //The current index in the list - numOfLeval
        int numOfLeval = 0;
        //Is the ball still in play?
        boolean ballInd = true;
        for (LevelInformation levelInfo : levels) {
            numOfLeval = numOfLeval + 1;
            GameLevel level = new GameLevel(new Point(0, 0), new Point(800, 600),  levelInfo,
                    this.animationRunner, this.keyboardSensor, this.pointsCounter);
            level.intialize();
            while ((level.getBlocksCounter().getValue() > 0) && (level.getballCounter().getValue() > 0)) {
                level.run();
                //this.animationRunner.run(level);
            }
            if (level.getBlocksCounter().getValue() == 0) {
                this.pointsCounter.increase(100);
            }
            if (level.getballCounter().getValue() == 0) {
                this.won = false;
                ballInd = false;
                break;
            }
        }
        if ((levels.size() == numOfLeval) && (ballInd)) {
            this.won = true;
        }
        EndScreen endScreen = new EndScreen(this.won, this.pointsCounter.getValue(), this.keyboardSensor);
        KeyPressStoppableAnimation key = new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, endScreen);
        this.animationRunner.run(key);
    }
}
