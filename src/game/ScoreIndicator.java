package game;

import sprite.Sprite;
import biuoop.DrawSurface;

import hit.Counter;

import java.awt.Color;

/**
 * 205543317.
 */

public class ScoreIndicator implements Sprite {

    private Counter score; //counter of score.

    /**
     * Initialize a Game.ScoreIndicator.
     *
     * @param counter a counter that count the score.
     */
    public ScoreIndicator(Counter counter) {
        score = counter;
    }

    /**
     * Draws the score on the surface.
     *
     * @param surface a given surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.lightGray); //sets color to light gray.
        surface.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.SCORE_SIZE);
        surface.setColor(Color.BLACK); //sets color to black.
        surface.drawText(370, 18, "Score: " + score.getValue(), GameLevel.SCORE_SIZE);
    }

    /**
     * Adds the score to the game as a Sprite.Sprite.
     *
     * @param g a Game.Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void timePassed() {
    }
}
