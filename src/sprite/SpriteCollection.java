package sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * 205543317.
 */

public class SpriteCollection {

    private ArrayList<Sprite> allSprites; // An array list storing all the sprites.

    /**
     * Creates new array list of sprites.
     */
    public SpriteCollection() {
        this.allSprites = new ArrayList<Sprite>();
    }

    /**
     * Gets all sprites.
     *
     * @return the all sprite array.
     */
    public ArrayList<Sprite> getAllSprites() {
        return allSprites;
    }

    /**
     * Adds a sprite to the sprites array.
     *
     * @param s A sprite.
     */
    public void addSprite(Sprite s) {
        this.allSprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesList = new ArrayList<>(this.allSprites);
        for (int i = 0; i < this.allSprites.size(); i++) {
            spritesList.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on each sprite.
     *
     * @param d A draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : allSprites) {
            sprite.drawOn(d);
        }
    }
}