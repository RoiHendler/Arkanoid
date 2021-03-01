/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 4
 * @ since: 11/06/2020
 */
package sprite;

import lnterface.HitListener;

/**
 * @ Score Tracking Listener class
 * containing:
 * HIT_SCORE  - How many points get on each hit
 * Counter counter - the current score
 **/
public class ScoreTrackingListener implements HitListener {
    private static final int HIT_SCORE = 5;
    private Counter currentScore;

    /***
     * constructors for the score counter class.
     * @param scoreCounter - Counter of the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /***
     * hit Event.
     * @param beingHit - the block that being hit
     * @param hitter - the ball that make the hit
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(HIT_SCORE);
    }
}
