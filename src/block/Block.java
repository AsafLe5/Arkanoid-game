package block;

import ball.Ball;
import ball.Velocity;
import game.GameLevel;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import hit.HitListener;
import hit.HitNotifier;
import sprite.Sprite;
import biuoop.DrawSurface;
import collidable.Collidable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 205543317.
 */

public class Block implements Collidable, Sprite, HitNotifier {

    private Color color; // Color of block.
    private Rectangle block; //GeometryPrimitives.Rectangle represent the block.
    private List<HitListener> hitListeners; //list of hitListeners.

    /**
     * Gets the color.
     *
     * @return The color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Draws the block on the given draw surface by drawing a black frame and then fill it.
     *
     * @param surface A surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        //Draws a black frame.
        surface.drawRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        surface.setColor(this.getColor());
        //Fills the inside with the block's color.
        surface.fillRectangle((int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
    }

    @Override
    public void timePassed() {
    }

    /**
     * Set the block.
     *
     * @param block A block.
     * @param color A color.
     */
    public Block(Rectangle block, java.awt.Color color) {
        this.block = block;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Adds the block to the game.
     *
     * @param g A game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Gets the block.
     *
     * @return The block.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     *
     * @param hitter          the Ball.Ball that's doing the hitting.
     * @param collisionPoint  The collision point of the hit.
     * @param currentVelocity Current velocity of the ball at the moment.
     * @return The new velocity expected after the hit
     * (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        //Case the ball hits exactly the point that between each two lines.
        if (collisionPoint.equals(block.getLeftLine().start())
                || collisionPoint.equals(block.getLeftLine().end())
                || collisionPoint.equals(block.getRightLine().start())
                || collisionPoint.equals(block.getRightLine().end())) {
            return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //Case its any of the other line representing the block.
        if (block.getLeftLine().isBetweenPoints(block.getLeftLine().start(),
                collisionPoint, block.getLeftLine().end())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (block.getRightLine().isBetweenPoints(block.getRightLine().start(),
                collisionPoint, block.getRightLine().end())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (block.getTopLine().isBetweenPoints(block.getTopLine().start(),
                collisionPoint, block.getTopLine().end())) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (block.getBottomLine().isBetweenPoints(block.getBottomLine().start(),
                collisionPoint, block.getBottomLine().end())) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return null;
    }

    /**
     * Removes the block from the game by removing in from the sprites and collidable.
     *
     * @param gameLevel a Game.Game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add the block as a listener.
     *
     * @param hl a hit listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove the block from the listener.
     *
     * @param hl a hit listener list.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all listeners about a hit event.
     *
     * @param hitter the Ball.Ball that's doing the hitting.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
