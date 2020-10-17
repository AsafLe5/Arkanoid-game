package collidable;

import ball.Ball;
import ball.Velocity;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * 205543317.
 */

public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

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
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}