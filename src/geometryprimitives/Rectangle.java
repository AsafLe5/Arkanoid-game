package geometryprimitives;

import java.util.ArrayList;
import java.util.List;

/**
 * 205543317.
 */

public class Rectangle {

    private Point source; //Top left vertex spot of the rectangle.
    private double width; //Width of the rectangle.
    private double height; //Height of the rectangle.
    private Line leftLine; // Left line of the rectangle.
    private Line rightLine; // Right line of the rectangle.
    private Line topLine; // Top line of the rectangle.
    private Line bottomLine; // Bottom line of the rectangle.

    /**
     * Gets the left line.
     *
     * @return The left line of the rectangle.
     */
    public Line getLeftLine() {
        return leftLine;
    }

    /**
     * Gets the right line.
     *
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        return rightLine;
    }

    /**
     * Gets the top line.
     *
     * @return the top line of the rectangle.
     */
    public Line getTopLine() {
        return topLine;
    }

    /**
     * Gets the bottom line.
     *
     * @return the bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return bottomLine;
    }

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft Top left vertex spot of the rectangle.
     * @param width     Width of the rectangle.
     * @param height    //Height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.source = upperLeft;
        this.width = width;
        this.height = height;
        this.leftLine = new Line(this.source, new Point(this.source.getX(), this.source.getY() + height));
        this.rightLine = new Line(new Point(this.source.getX() + this.width, this.source.getY()),
                new Point(this.source.getX() + this.width, this.source.getY() + height));
        this.topLine = new Line(this.source, new Point(this.source.getX() + width, this.source.getY()));
        this.bottomLine = new Line(new Point(this.source.getX(), this.source.getY() + this.height),
                new Point(this.source.getX() + this.width, this.source.getY() + height));
    }

    //
    //

    /**
     * Finds all the intersection points with a given line to the rectangle.
     *
     * @param line A line
     * @return Return a (possibly empty) List of intersection points
     * with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<Point>(); // storing all the intersection points.
        if (this.leftLine.isIntersecting(line)) {
            pointList.add(this.leftLine.intersectionWith(line));
        }
        if (this.rightLine.isIntersecting(line)) {
            pointList.add(this.rightLine.intersectionWith(line));
        }
        if (this.topLine.isIntersecting(line)) {
            pointList.add(this.topLine.intersectionWith(line));
        }
        if (this.bottomLine.isIntersecting(line)) {
            pointList.add(this.bottomLine.intersectionWith(line));
        }
        if (pointList.size() == 0) { //Case there isn't any intersects.
            return null;
        }
        return pointList;

    }

    /**
     * @return the width and width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the width and height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.source;
    }
}