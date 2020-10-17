package game;

import ball.Ball;
import collidable.Collidable;
import ball.Velocity;
import geometryprimitives.Line;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import sprite.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * 205543317.
 */

public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard; //keyboard sensor.
    private Point topLeft; //top left point of paddle.
    private double width; //width of paddle.
    private double height; // height of paddle.
    private double velocity; // velocity of paddle.
    public static final int SPEED = 7; // speed of left and right sensor.

    /**
     * Creates a new paddle.
     *
     * @param gui       A gui.
     * @param upperLeft Upper left point of paddle.
     * @param width     Width of paddle.
     * @param height    Height of paddle.
     * @param speed     speed of the ball.
     */
    public Paddle(GUI gui, Point upperLeft, double width, double height, double speed) {
        this.keyboard = gui.getKeyboardSensor();
        this.topLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.velocity = speed;
    }

    /**
     * Move the paddle left.
     */
    public void moveLeft() {
        if (topLeft.getX() > GameLevel.BORDER_SIZE) { // case there is space for paddle to go left.
            this.topLeft = new Point(topLeft.getX() - SPEED, topLeft.getY());
        }
    }

    /**
     * Move the paddle right.
     */
    public void moveRight() {
        // case there is space for paddle to go right.
        if (topLeft.getX() < GameLevel.WIDTH - GameLevel.BORDER_SIZE - this.width) {
            this.topLeft = new Point(topLeft.getX() + SPEED, topLeft.getY());
        }
    }

    /**
     * Checks whether the user pressed the arrow at the current time.
     * Case he is, the function calls the correct function to move the paddle to the right direction.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle in the given draw surface.
     *
     * @param d A draw surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle((int) this.topLeft.getX(), (int) this.topLeft.getY(), (int) this.width, (int) this.height);
    }

    /**
     * creates a new rectangle represent the paddle.
     *
     * @return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.topLeft, width, height);
    }

    /**
     * Gets the total speed of a given velocity by using Pythagoras's equation.
     *
     * @param vel A velocity holds dx and dy.
     * @return The total speed of the velocity.
     */
    public double getSpeed(Velocity vel) {
        return Math.sqrt(Math.pow(vel.getDx(), 2) + Math.pow(vel.getDy(), 2));
    }

    /**
     * divide the paddle to 5 areas and change the velocity
     * of the ball to a velocity specific for the area it landed.
     *
     * @param hitter          the Ball.Ball that's doing the hitting.
     * @param collisionPoint  The point where there was a collision.
     * @param currentVelocity The current velocity of the ball.
     * @return if the ball hit area 1 - change the angle od the velocity to 300,
     * area 2 -  330 angle, area 3 - just change the y direction to be the opposite,
     * area 4 - 30 angle, area 5 - 60 angle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double yOfPaddle = this.topLeft.getY(); // Y spot of the surface of the paddle.
        // creates 4 lines, each one of them holds 1/5 of the size of the paddle.
        Line region1 = new Line(this.topLeft, new Point(this.topLeft.getX() + this.width / 5, yOfPaddle));
        Line region2 = new Line(region1.end(), new Point(region1.end().getX() + this.width / 5, yOfPaddle));
        Line region3 = new Line(region2.end(), new Point(region2.end().getX() + this.width / 5, yOfPaddle));
        Line region4 = new Line(region3.end(), new Point(region3.end().getX() + this.width / 5, yOfPaddle));
        double speed = getSpeed(currentVelocity);
        if (region1.isBetweenPoints(region1.start(), collisionPoint, region1.end())) { // ball hit region 1.
            return currentVelocity.fromAngleAndSpeed(300, speed);
        }
        if (region2.isBetweenPoints(region2.start(), collisionPoint, region2.end())) { // ball hit region 2.
            return currentVelocity.fromAngleAndSpeed(330, speed);
        }
        if (region3.isBetweenPoints(region3.start(), collisionPoint, region3.end())) { //Middle of paddle.
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (region4.isBetweenPoints(region4.start(), collisionPoint, region4.end())) { // ball hit region 4.
            return currentVelocity.fromAngleAndSpeed(30, speed);
        }
        return currentVelocity.fromAngleAndSpeed(60, speed); // ball hit region 5.
    }

    /**
     * Add this paddle to the given game.
     *
     * @param g A game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}