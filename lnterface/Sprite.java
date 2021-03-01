package lnterface;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */
import biuoop.DrawSurface;

/***
 * Sprite interface.
 */
public interface Sprite {

    /***
     * draw the sprite to the screen.
     * @param d - the draw surface
     */
    void drawOn(DrawSurface d);

    /***
     *  notify the sprite that time has passed.
     */
    void timePassed();
}