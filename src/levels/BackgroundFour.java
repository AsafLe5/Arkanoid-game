package levels;

import game.GameLevel;
import sprite.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * 205543317.
 */

public class BackgroundFour implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        for (int i = 0, j = 0; i < GameLevel.HEIGHT; i += 5, j++) {
            d.setColor(new Color(30 + j, 144, 255));
            d.fillRectangle(0, i, GameLevel.WIDTH, 5);
        }
        for (int i = 0, j = 0; i < 751; i += 50, j += 10) {
            d.setColor(new Color(210 - j, 210 - j, 210 - j));
            d.fillRectangle(GameLevel.WIDTH / 3 + i, GameLevel.HEIGHT - i, 250, 50);
            d.setColor(Color.black);
            d.drawRectangle(GameLevel.WIDTH / 3 + i, GameLevel.HEIGHT - i, 250, 50);
        }
        d.setColor(Color.RED);
        d.fillRectangle(GameLevel.WIDTH - 150, (int) (GameLevel.HEIGHT * 0.1), 10, 300);
        d.drawText(GameLevel.WIDTH - 150, (int) (GameLevel.HEIGHT * 0.1), "^", 12);
        d.fillRectangle(GameLevel.WIDTH - 170, (int) (GameLevel.HEIGHT * 0.1), 10, 30);
        d.drawText(GameLevel.WIDTH - 170, (int) (GameLevel.HEIGHT * 0.1), "^", 12);
        d.fillRectangle(GameLevel.WIDTH - 130, (int) (GameLevel.HEIGHT * 0.1), 10, 30);
        d.drawText(GameLevel.WIDTH - 130, (int) (GameLevel.HEIGHT * 0.1), "^", 12);
        d.fillRectangle(GameLevel.WIDTH - 170, (int) (GameLevel.HEIGHT * 0.1 + 30), 50, 10);
    }

    @Override
    public void timePassed() {
    }
}
