/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
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
 * @ Block Remover class
 * containing:
 * Game game  - Our game
 * Counter counter - counter for how much balls remained in the game
 **/
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * constructors for the Block Remover class.
     * @param game Our game
     * @param removedBlocks counter for how much blocks remained in the game
     **/
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.

    /***
     * Blocks that are hit and reach 0 hit-points should be removed from the game.
     * @param beingHit - the block that  being hit
     * @param hitter - the ball that make the hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }

}
