package geometryprimitives;

/**
 * 205543317.
 */

public class Point {
    private static double epsilon = Math.pow(10, -14); //checks equality with accuracy level 10 to the power of -14.
    private double x; // x position of the point
    private double y; // y position of the point

    /**
     * gets 2 doubles and creates a point with this values.
     *
     * @param xPoint x position of the point
     * @param yPoint y position of the point
     */
    public Point(double xPoint, double yPoint) {
        this.x = xPoint;
        this.y = yPoint;
    }

    /**
     * gets other point and check the distance between the two.
     *
     * @param other a given point
     * @return the distance
     */
    public double distance(Point other) {
        double xDistance = Math.pow(Math.abs(this.getX() - other.getX()), 2); // (x1-x2)^2
        double yDistance = Math.pow(Math.abs(this.getY() - other.getY()), 2); // (y1-y2)^2
        return Math.sqrt(xDistance + yDistance); // square root of the two results above
    }

    /**
     * check whether the point and another given point posses the save values.
     *
     * @param other a given point
     * @return true if they're equal, false if they are not.
     */
    public boolean equals(Point other) {
        if (other == null) { // case other has not initialized
            return false;
        }
        return Math.abs(this.getY() - other.getY()) < epsilon && // y1 = y2
                Math.abs(this.getX() - other.getX()) < epsilon; // x1 = x2
    }

    /**
     * return the value of the x of the current point.
     *
     * @return x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * return the value of the y of the current point.
     *
     * @return y value
     */
    public double getY() {
        return this.y;
    }
}
