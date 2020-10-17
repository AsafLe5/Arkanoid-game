package levels;

import game.GameLevel;
import sprite.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * 205543317.
 */

public class BackgroundTwo implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        for (int i = 0, j = 0; i < GameLevel.HEIGHT; i += 5, j++) {
            d.setColor(new Color(123, 104 + j, 238));
            d.fillRectangle(0, i, GameLevel.WIDTH, 5);
        }
        d.setColor(new Color(139, 69, 19));
        d.fillRectangle(100, 0, 130, GameLevel.WIDTH);
        d.fillRectangle(580, 0, 130, GameLevel.WIDTH);

        for (int i = 80; i < 270; i += 30) {
            for (int j = 0; j < 500; j += 50) {
                d.setColor(Color.BLACK);
                d.setColor(new Color((int) (144 + 30 * Math.sin(Math.toRadians(j))), 238, 144));
                d.fillCircle(i, j, 40);
            }
        }
        for (int i = 550; i < 750; i += 30) {
            for (int j = 0; j < 500; j += 50) {
                d.setColor(Color.BLACK);
                d.setColor(new Color(144, 238, (int) (144 + 30 * Math.sin(Math.toRadians(j)))));
                d.fillCircle(i, j, 40);
            }
        }
    }

    @Override
    public void timePassed() {
    }
}
