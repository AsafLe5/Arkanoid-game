package game;

import hit.Counter;
import ball.Ball;
import block.Block;
import hit.HitListener;

/**
 * 205543317.
 */

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore; // current score of game.

    /**
     * Initiallize a scoreCounter.
     *
     * @param scoreCounter a counter of score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Increases the score.
     *
     * @param beingHit the block that been hit.
     * @param hitter   the Ball.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}