package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import lnterface.Animation;

/**
 * @ KeyPressStoppableAnimation class
 **/
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop = false;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed = true;

    /***
     * @ constructors for the KeyPressStoppableAnimation class.
     * @param sensor - the keyboard
     * @param key - the mark - in case you click on the mark
     * @param animation - the type of the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
    }

    /***
     * in every frame, we checking if the button is pressed?
     * @param d - are draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    /***
     * if we need to stop?
     * @return true or false
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
