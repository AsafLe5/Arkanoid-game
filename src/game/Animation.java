package game;

import biuoop.DrawSurface;

/**
 * 205543317.
 */

public interface Animation {

    /**
     * In charge of the logic of one frame.
     *
     * @param d a draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return whether the animation should stop or not.
     */
    boolean shouldStop();
}