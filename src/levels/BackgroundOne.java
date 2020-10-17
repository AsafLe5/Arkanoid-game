package levels;

import game.GameLevel;
import sprite.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * 205543317.
 */

public class BackgroundOne implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        for (int i = 0, j = 0; i < GameLevel.HEIGHT; i += 5, j++) {
            d.setColor(new Color(176, 224 - j, 230 - j));
            d.fillRectangle(0, i, GameLevel.WIDTH, 5);
        }
        d.setColor(new Color(240, 240, 0));
        for (int i = 0; i < 700; i += 1) {
            d.drawLine(GameLevel.WIDTH / 2, GameLevel.HEIGHT,
                    (int) (1000 * Math.sin(i)), (int) (1000 * Math.cos(Math.toRadians(i))));
        }
        for (int i = 255, j = 255; i > 200; i--, j -= 4) {
            d.setColor(new Color(i, i, 0));
            d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT, j);
        }
        d.setColor(Color.darkGray);
    }

    @Override
    public void timePassed() {
    }
}
