package levels;

import game.Animation;
import game.GameLevel;
import hit.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * 205543317.
 */

public class EndScreen implements Animation {

    private Counter score; // the counter of the score.
    private boolean winner; // is the player won.

    /**
     * Initialize a EndScreen.
     *
     * @param score  the counter of the score.
     * @param winner is the player won.
     */
    public EndScreen(Counter score, boolean winner) {
        this.score = score;
        this.winner = winner;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.lightGray); //sets color to light gray.
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.setColor(Color.BLACK);
        if (this.winner) {
            d.setColor(Color.BLUE);
            d.drawText(50, GameLevel.HEIGHT / 2, "You Win! Your score is " + this.score.getValue(), 50);
        } else {
            d.setColor(Color.RED);
            d.drawText(50, GameLevel.HEIGHT / 2, "Game Over. Your score is " + this.score.getValue(), 50);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
