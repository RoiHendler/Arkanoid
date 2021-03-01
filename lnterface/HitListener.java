package lnterface;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */
import sprite.Block;
import sprite.Ball;

/***
 * interface for Hit Listener.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Sprite.Ball that's doing the hitting.

    /***
     * @param beingHit -  the bloke that thay do the hit
     * @param hitter - the ball that make the hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}