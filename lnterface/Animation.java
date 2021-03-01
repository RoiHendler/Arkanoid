package lnterface;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 4
 * @ since: 11/06/2020
 */
import biuoop.DrawSurface;

/***
 /**
 * @ interface Animation
 **/
public interface Animation {
    /***
     * doOneFrame.
     * @param d - the DrawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop?
     * @return true or false
     */
    boolean shouldStop();
}
