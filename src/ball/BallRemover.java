package ball;

import hit.HitListener;
import block.Block;
import game.GameLevel;

import hit.Counter;

/**
 * 205543317.
 */

public class BallRemover implements HitListener {

    private GameLevel gameLevel; // a Game.Game.
    private Counter remainingBalls; // Hit.Counter of remained balls.

    /**
     * Initialize a ball remover.
     *
     * @param gameLevel    a Game.Game.
     * @param removedBalls counter of remained balls.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * Removes the ball that's doing the hitting.
     *
     * @param beingHit the block that been hit.
     * @param hitter   the Ball.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(1);
    }
}
