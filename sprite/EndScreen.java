package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 4
 * @ since: 11/06/2020
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import lnterface.Animation;

import java.awt.Color;

/**
 * the End Screen class.
 **/
public class EndScreen implements Animation {
    private boolean won;
    private int score;
    private KeyboardSensor ks;

    /***
     * constructors for the End Screen class.
     * @param won - Did you win the game?
     * @param score - your score
     * @param ks - the KeyboardSensor
     */
    public EndScreen(boolean won, int score, KeyboardSensor ks) {
        this.won = won;
        this.score = score;
        this.ks = ks;
    }

    /***
     Displays the End screen.
     * @param d - the DrawSurface
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        if (this.won) {
            d.setColor(Color.black);
            d.drawText(30, d.getHeight() / 4, "You winner!", 30);
            d.drawText(30, d.getHeight() / 2, "your score is: " + this.score, 30);
        } else {
            d.setColor(Color.black);
            d.drawText(30, d.getHeight() / 4, "Game Over. Your Score is " + this.score, 30);
        }
        //  if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
        //                 this.stop = true;
        //             }
        // }
    }

    /***
     * should Stop the Displays End screen.
     * @return
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}

