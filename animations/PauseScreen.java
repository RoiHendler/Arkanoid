package animations;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 4
 * @ since: 11/06/2020
 */
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import lnterface.Animation;
/**
 * @ PauseScreen class
 **/
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    //Do continue or stop?
    private boolean stop = false;

    /***
     * @ constructors for the PauseScreen class.
     * @param keyboard - the keyboard sensor
     */
    public PauseScreen(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    /***
     * in every frame, when the button is not pressed yet
     * We register on screen: "paused -- press space to continue".
     * @param d - are draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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
