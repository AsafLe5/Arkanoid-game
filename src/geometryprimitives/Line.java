package geometryprimitives;

import java.util.List;

/**
 * 205543317.
 */

public class Line {
    private final double epsilon = Math.pow(10, -14); //checks equality within accuracy level 10 to the power of -14
    private Point start, end; // start of the line and end of the line.

    /**
     * Creates a line within two points.
     *
     * @param starting start of the line.
     * @param ending   end of the line.
     */
    public Line(Point starting, Point ending) {
        this.start = starting;
        this.end = ending;
    }

    /**
     * Creates a line within 4 doubles.
     *
     * @param x1 x1 location of the line.
     * @param y1 y1 location of the line.
     * @param x2 x2 location of the line.
     * @param y2 y2 location of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Gets the length of the current line.
     *
     * @return the length of the current line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Calculate and return the middle location of the current line.
     *
     * @return the middle location of the current line.
     */
    public Point middle() {
        double xMiddle = (end.getX() + start.getX()) / 2; // Middle of x position.
        double yMiddle = (end.getY() + start.getY()) / 2; // Middle of y position.
        Point middle = new Point(xMiddle, yMiddle); // New point by these values.
        return middle;
    }

    /**
     * Gets the starting point of the line.
     *
     * @return the starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Gets the ending point of the line.
     *
     * @return the ending point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * check whether the slope of the line is define,
     * it is'nt when the line is parallel to y axis.
     *
     * @return true - if the line parallel to y axis, false, if not.
     */
    public boolean isSlopeDefined() {
        if (!(Math.abs(this.end.getX() - this.start.getX()) < epsilon)) { // x2 = x1
            return true;
        }
        return false;
    }

    /**
     * caculate a slope of a given line.
     *
     * @param line a line
     * @return the slope of a the line.
     */
    public double slope(Line line) {
        if (Math.abs(line.start.getY() - line.end.getY()) < epsilon) { // case y2 = y1.
            return 0;
        }
        return (line.end.getY() - line.start.getY()) / (line.end.getX() - line.start.getX()); // (y2-y1)/(x2-x1)
    }

    /**
     * calculate the point where two lines intersect when the second line is parallel to y axis.
     *
     * @param other the line that is parallel to y axis.
     * @return point of intersect between the two lines.
     */
    public Point yParallelIntersect(Line other) {
        return new Point(other.start.getX(),
                slope(this) * other.start.getX() + yIntersect(this, slope(this)));
    }

    /**
     * caculate the interception between the given line with the y axis.
     *
     * @param line  a line.
     * @param slope the line's slope.
     * @return the interception between the given line with the y axis.
     */
    public double yIntersect(Line line, double slope) {
        return line.start.getY() - line.start.getX() * slope; // y - mx
    }

    /**
     * caculate the intersect point between two line if they were
     * infinite lines by using their slope and the intersect with y axis.
     *
     * @param m1 first line's slope.
     * @param b1 first line's intersect with y axis.
     * @param m2 second line's slope.
     * @param b2 second line's intersect with y axis.
     * @return the Intersect point between two line if they were infinite lines.
     */
    public Point infiniteIntersect(double m1, double b1, double m2, double b2) {
        if (m1 == m2) { // case the two lines are parallel to each other.
            return null;
        }
        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;
        Point point = new Point(x, y); // intersect point.
        return point;
    }

    /**
     * Checks whether point 2 is between point 1 and point 3.
     *
     * @param point1 GeometryPrimitives.Line's start.
     * @param point2 A point.
     * @param point3 GeometryPrimitives.Line's end.
     * @return true if point 2 is between point 1 and point 3, false otherwise.
     */
    public boolean isBetweenPoints(Point point1, Point point2, Point point3) {
        if (Math.abs(point1.distance(point2) + point3.distance(point2) - point1.distance(point3)) < epsilon) {
            return true; // C is on the line.
        }
        return false;    // C is not on the line.
    }

    /**
     * checks whether a given line is intersecting with the current line.
     *
     * @param other a line
     * @return true if the given line is intersecting with the current line, else otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.isSlopeDefined() && !other.isSlopeDefined()) { // slope of line two not defined.
            Point intersect = this.yParallelIntersect(other);
            if (isBetweenPoints(this.start, intersect, this.end)
                    && isBetweenPoints(other.start, intersect, other.end)) {
                return true;
            }
            return false;
        }
        if (!this.isSlopeDefined() && other.isSlopeDefined()) { // slope of line one not defined.
            Point intersect = other.yParallelIntersect(this);
            if (isBetweenPoints(this.start, intersect, this.end)
                    && isBetweenPoints(other.start, intersect, other.end)) {
                return true;
            }
            return false;
        }
        if (this.start.equals(other.start) ^ this.start.equals(other.end) ^  // case lines are touching
                this.end.equals(other.start) ^ this.end.equals(other.end)) { // each other at the edges.
            return true;
        }
        if (Math.abs((slope(this) - slope(other))) > epsilon) { // case the lines are not parallel to each other.
            Point intersect = infiniteIntersect(slope(this),
                    yIntersect(this, slope(this)), slope(other), yIntersect(other, slope(other)));
            if (isBetweenPoints(this.start, intersect, this.end) && //case the interception point is on the two lines.
                    isBetweenPoints(other.start, intersect, other.end)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the intersection point of the current line and the given one.
     *
     * @param other a line.
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.start.equals(other.start) ^ this.start.equals(other.end) ^  // case lines are touching
                this.end.equals(other.start) ^ this.end.equals(other.end)) { // each other at the edges.
            if (this.start.equals(other.start)) {
                return this.start;
            }
            if (this.start.equals(other.end)) {
                return this.start;
            }
            if (this.end.equals(other.start)) {
                return this.end;
            }
            if (this.end.equals(other.end)) {
                return this.end;
            }
        }
        if (!(this.isIntersecting(other))) { // the lines are not intersecting with each other.
            return null;
        }
        if (!this.isSlopeDefined() || !other.isSlopeDefined()) { //one of the slope is not defined.
            return !this.isSlopeDefined() ? other.yParallelIntersect(this) : this.yParallelIntersect(other);
        }
        return infiniteIntersect(slope(this),
                yIntersect(this, slope(this)), slope(other), yIntersect(other, slope(other)));
    }

    /**
     * checks whether the current line is equal to a given one.
     *
     * @param other a line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.equals(other);
    }

    /**
     * Find The closest point on intersection of the start of the line and a given rectangle.
     *
     * @param rect A rectangle.
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double shortestDistance; // Keeps the smallest distance between the points.
        Point closestPoint; //Closest point.
        List<Point> pointList; // Stores all the intersection points.
        if (rect.intersectionPoints(this) != null) {
            pointList = rect.intersectionPoints(this);
        } else {
            return null;
        }
        // save the first distance as the shortest and point as the closest for the comparison.
        shortestDistance = this.start.distance(pointList.get(0));
        closestPoint = pointList.get(0);
        for (Point point : pointList) {
            if (shortestDistance > this.start.distance(point)) {
                shortestDistance = this.start.distance(point);
                closestPoint = point;
            }
        }
        return closestPoint;
    }
}