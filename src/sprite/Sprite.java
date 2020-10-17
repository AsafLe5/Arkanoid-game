package sprite;

import biuoop.DrawSurface;

/**
 * 205543317.
 */

public interface Sprite {
    /**
     * Draws the sprite to the screen.
     *
     * @param d A draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}