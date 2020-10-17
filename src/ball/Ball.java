package ball;

import sprite.Sprite;
import collidable.CollisionInfo;
import game.GameLevel;
import game.GameEnvironment;
import geometryprimitives.Line;
import geometryprimitives.Point;
import biuoop.DrawSurface;

/**
 * 205543317.
 */

public class Ball implements Sprite {
    private GameEnvironment gameEnvironment;
    private Point center; // center of the point.
    private int r; //radius of ball
    private java.awt.Color color; //color of the ball.
    private Velocity velocity; //velocity of the ball.

    /**
     * set the velocity of the ball for a given velocity.
     *
     * @param v a velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the velocity of the ball for a given velocity x and velocity y.
     *
     * @param dx velocity in x axis.
     * @param dy velocity in y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * gets the velocity of the current ball.
     *
     * @return velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * set the new location of the ball for the next step.
     */
    public void moveOneStep() {
        CollisionInfo info = null;
        Velocity saveVelocity = this.velocity; //keeps velocity current values.
        Line trajectory = new Line(this.center, new Point(this.center.getX()
                + this.velocity.getDx(), this.center.getY() + this.velocity.getDy()));
        if (this.gameEnvironment.getClosestCollision(trajectory) != null) { // case there is a collision.
            info = this.gameEnvironment.getClosestCollision(trajectory);
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
        }
        Line newTrajectory = new Line(this.center, new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy()));
        if (this.gameEnvironment.getClosestCollision(newTrajectory) != null) { // Case ball enters another collidable.
            this.velocity = new Velocity(-saveVelocity.getDx(), -saveVelocity.getDy());
        }
        this.center = this.getVelocity().applyToPoint(this.center); //change the center of the ball to his next step.
    }

    /**
     * create a ball within a given center point, radius and a color.
     *
     * @param ctr the center point of the ball.
     * @param rad the radius of the ball.
     * @param clr the color of the ball.
     */
    public Ball(Point ctr, int rad, java.awt.Color clr) {
        this.center = ctr;
        this.r = rad;
        this.color = clr;
    }

    /**
     * create a ball within a given x and y locations, radius and a color.
     *
     * @param xCenter x location of the ball's center.
     * @param yCenter y location of the ball's center.
     * @param rad     the radius of the ball.
     * @param clr     the color of the ball.
     */
    public Ball(int xCenter, int yCenter, int rad, java.awt.Color clr) {
        this.center = new Point((double) xCenter, (double) yCenter);
        this.r = rad;
        this.color = clr;
    }

    /**
     * gets the x of the center of the ball.
     *
     * @return the x of the center of the ball.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }

    /**
     * gets the y of the center of the ball.
     *
     * @return the y of the center of the ball.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }

    /**
     * gets the size of the ball.
     *
     * @return the size of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * gets the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface a drawing surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) Math.round(this.center.getX()), (int) Math.round(this.center.getY()), this.r);
    }

    @Override
    /**
     * Case time has passed, the ball shall move one step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets the ball's games environment.
     *
     * @param environment A game environment.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Adds the ball as a sprite to the game.
     *
     * @param g A game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove the ball from the game by removing it from the Sprites.
     *
     * @param gameLevel a Game.Game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}

