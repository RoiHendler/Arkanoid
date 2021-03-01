/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */
package listener;

import lnterface.HitListener;
import sprite.Ball;
import sprite.Block;

/***
 * For testing purposes only.
 */
public class PrintingHitListener implements HitListener {
    /***
     * hit Event Printing Hit Listener.
     * @param beingHit -  the bloke that thay do the hit
     * @param hitter - the ball that make the hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with X points was hit.");
    }

}
