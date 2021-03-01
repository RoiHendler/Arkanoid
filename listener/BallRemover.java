/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 3
 * @ since: 24/05/2020
 */
package listener;

import game.GameLevel;
import lnterface.HitListener;
import sprite.Ball;
import sprite.Block;
import sprite.Counter;
/**
 * @ Ball Remover class
 * containing:
 * Game game  - Our game
 * Counter counter - counter for how much balls remained in the game
 **/
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter counter;
    /**
     * constructors for the Ball Remover class.
     * @param game Our game
     * @param counter counter for how much balls remained in the game
     **/
    public BallRemover(GameLevel game, Counter counter) {
        this.game = game;
        this.counter = counter;
    }

    /***
     * In case of hit.
     * @param beingHit - the block that  being hit
     * @param hitter - the ball that make the hit
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        counter.decrease(1);
    }
}
