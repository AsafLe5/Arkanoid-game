package hit;

import ball.Ball;
import block.Block;

/**
 * 205543317.
 */

public interface HitListener {

    /**
     * called whenever the beingHit object is hit.
     *
     * @param beingHit the block that been hit.
     * @param hitter   the Ball.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}