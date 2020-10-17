package game;

import collidable.Collidable;
import collidable.CollisionInfo;
import geometryprimitives.Line;
import geometryprimitives.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 205543317.
 */

public class GameEnvironment {

    private final double maxSizeLine = Math.pow(10, 9); // Maximum size of line.
    private List<Collidable> collidables; //List of collidables.

    /**
     * Gets the list of all the collidables.
     *
     * @return the list of all the collidables.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Sets the game's environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c A collidable.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Check for every collidable if its intersecting with the given line.
     *
     * @param trajectory the trajectory of a the current ball position
     *                   and the position he's about to be in the next move.
     * @return If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information.
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minDistance; // minimum distance between ball's current position and the collision.
        Collidable closestCollidable = null; // Closest collidable.
        Point closestIntersection = null; // Closest intersection.
        if (collidables == null) {
            return null;
        }
        minDistance = maxSizeLine;
        for (Collidable collidable : collidables) {
            // Case collidable is intersecting with the trajectory.
            if (trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle()) != null) {
                Point intersection =
                        trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
                double distance = trajectory.start().distance(intersection);
                if (minDistance > distance) { //Storing the info of the closest collidable.
                    minDistance = distance;
                    closestIntersection = intersection;
                    closestCollidable = collidable;
                }
            }
        }
        if (closestCollidable == null) { // Case there isn't any collidables that close to the ball.
            return null;
        }
        return new CollisionInfo(closestIntersection, closestCollidable);
    }
}








