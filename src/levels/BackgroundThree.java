package levels;

import game.GameLevel;
import sprite.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * 205543317.
 */

public class BackgroundThree implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        for (int i = 0, j = 0; i < GameLevel.HEIGHT; i += 5, j++) {
            d.setColor(new Color(224 - j, 255 - j, 255 - j));
            d.fillRectangle(0, i, GameLevel.WIDTH, 5);
        }
        for (int i = 0; i < GameLevel.WIDTH; i++) {
            if (i % 2 == 0) {
                d.setColor(new Color(139, 69, 19));
                d.fillRectangle(8 * i, GameLevel.HEIGHT - 30, 8 * i + 20, GameLevel.HEIGHT);
            } else {
                d.setColor(new Color(170, 90, 40));
                d.fillRectangle(8 * i, GameLevel.HEIGHT - 30, 8 * i + 20, GameLevel.HEIGHT);
            }
        }
        d.setColor(new Color(220, 200, 0));
        for (int i = 30; i < GameLevel.WIDTH - 20; i += 70) {
            for (int j = 90; j < GameLevel.HEIGHT - 50; j += 50) {
                d.drawText(i, j, "///", 50);
            }
        }
    }

    @Override
    public void timePassed() {

    }
}
