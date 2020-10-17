package block;
/**
 * 205543317.
 */

import ball.Ball;
import game.GameLevel;
import hit.Counter;
import hit.HitListener;

/**
 * in charge of removing blocks from the game, as well as keeping count
 * // of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel; // a game.
    private Counter remainingBlocks; // Hit Counter of remained blocks.

    /**
     * initialize a block remover.
     *
     * @param gameLevel     a Game.Game.
     * @param removedBlocks Hit.Counter of remained blocks.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Removes the block that been hit.
     *
     * @param beingHit the block that been hit.
     * @param hitter   the Ball.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        remainingBlocks.decrease(1);
    }
}