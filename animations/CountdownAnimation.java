package animations;

import biuoop.DrawSurface;
import game.levels.GameFlow;
import lnterface.Animation;
import sprite.SpriteCollection;

import java.awt.Color;

/**
 * @ CountdownAnimation class
 **/
public class CountdownAnimation implements Animation {
    private Integer countFrom;
    private final SpriteCollection gameScreen;
    private long startTime;

    /***
     * @ constructors for the CountdownAnimation class
     * @param countFrom - From which number to start counting back
     * @param gameScreen - SpriteCollection type that get the  game screen
     */
    public CountdownAnimation(int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        //the current time in millis
        this.startTime = System.currentTimeMillis();
    }

    /***
     * Paints the Count down animation.
     * @param d - are screan
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        long t = System.currentTimeMillis() - this.startTime;
        //When a second passed
        if (t >= 1000) {
            this.countFrom--;
            this.startTime = System.currentTimeMillis();
        }
        //Create a background for the counting
        this.gameScreen.drawAllOn(d);
        //Paints the Count - the num
        this.drawCountFrom(d);

    }

    /***
     * Draws the number on the screen.
     * @param d - are screan
     */
    private void drawCountFrom(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.drawText(GameFlow.WIDTH / 2, GameFlow.HEIGHT / 2, this.countFrom.toString(), 49);
    }

    /***
     * if we need to stop?
     * @return true or false
     */
    @Override
    public boolean shouldStop() {
        return countFrom <= 0;
    }
}















