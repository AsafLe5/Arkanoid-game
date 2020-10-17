package collidable;

import geometryprimitives.Point;

/**
 * 205543317.
 */

public class CollisionInfo {

    private Point collisionPoint; // GeometryPrimitives.Point of collision.
    private Collidable collisionObject; // The collision's object.

    /**
     * Set a collision info.
     *
     * @param collisionPoint  GeometryPrimitives.Point of collision.
     * @param collisionObject The collision's object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
