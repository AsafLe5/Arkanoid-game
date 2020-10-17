package ball;

import geometryprimitives.Point;

/**
 * 205543317.
 */

public class Velocity {

    private double dx; // velocity of x axis.
    private double dy; // velocity of y axis.

    /**
     * Set the velocity of the ball.  At least one of dx and
     * dy must be non-zero, so that the speed will be positive.
     *
     * @param xVel velocity of x axis.
     * @param yVel velocity of y axis.
     */
    public Velocity(double xVel, double yVel) {
        if (xVel != 0 || yVel != 0) {
            this.dx = xVel;
            this.dy = yVel;
        }
    }

    /**
     * creates a new point for a given point within the current velocity.
     *
     * @param p a point.
     * @return a new point after applying the velocity on the given one.
     */
    public Point applyToPoint(Point p) {
        //apply the speed on the given point.
        Point point = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return point;
    }

    /**
     * Set and return the velocity of the ball. within a given angle and speed.
     *
     * @param angle angle of the speed.
     * @param speed amount of speed.
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // Get the value of the angle in radians.
        double rAngle = Math.toRadians(angle);
        // Work out the change in pixels/millisecond in x direction.
        double xVel = speed * Math.sin(rAngle);
        // Work out the change in pixels/millisecond in y direction.
        double yVel = speed * -Math.cos(rAngle);
        return new Velocity(xVel, yVel);
    }

    /**
     * gets the velocity of x axis.
     *
     * @return velocity of x axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * gets the velocity of y axis.
     *
     * @return velocity of y axis.
     */
    public double getDy() {
        return this.dy;
    }
}
