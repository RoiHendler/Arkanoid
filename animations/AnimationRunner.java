package animations;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 4
 * @ since: 11/06/2020
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import lnterface.Animation;

/**
 * @ AnimationRunner class
 * containing:
 * GUI gui
 * framesPerSecond
 * Sleeper sleeper
 **/
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /***
     * @ constructors for the AnimationRunner class
     * @param gui - are screan
     * @param framesPerSecond - frames Per Second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /***
     * This function run the animation.
     * @param animation - the animation that we want to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            // the current time - in millis
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /***
     * Get Function.
     * @return the key board sensor
     */
    public KeyboardSensor getKeyBoardSensor() {
        return this.gui.getKeyboardSensor();
    }

}