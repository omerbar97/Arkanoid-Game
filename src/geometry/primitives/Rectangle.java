// 315318766 Omer Bar

package geometry.primitives;

import game.Commons;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public class Rectangle {

    private final Point upperLeft;
    private final double width;
    private final double height;
    private Line upperLine;
    private Line lowerLine;
    private Line leftLine;
    private Line rightLine;
    private Commons.BlockKIND recID = Commons.BlockKIND.NORMAL_BLOCK; // default

    /**
     * Constructor for the geometry.primitives.Rectangle Object.
     *
     * @param upperLeft - geometry.primitives.Point
     * @param width     - double
     * @param height    - double
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        // get all the needed point for the rectangle:
        Point upperRight = new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + this.getHeight());
        Point lowerRight = new Point(upperLeft.getX() + this.getWidth(), upperLeft.getY() + this.getHeight());
        // create the rectangle lines:
        this.setUpperLine(upperLeft, upperRight);
        this.setLowerLine(lowerLeft, lowerRight);
        this.setLeftLine(upperLeft, lowerLeft);
        this.setRightLine(upperRight, lowerRight);
    }

    /**
     * Setter for the Rectangle ID.
     * @param id - BlockKIND.
     */
    public void setRecID(Commons.BlockKIND id) {
        this.recID = id;
    }

    /**
     * Getter for the Rectangle ID.
     * @return = BlockKIND.
     */
    public Commons.BlockKIND getRecID() {
        return this.recID;
    }
    /**
     * setting the upper-line of the rectangle.
     *
     * @param upperLeft  - geometry.primitives.Point start
     * @param upperRight - geometry.primitives.Point end
     */
    private void setUpperLine(Point upperLeft, Point upperRight) {
        this.upperLine = new Line(upperLeft, upperRight);
    }

    /**
     * setting the lower-line of the rectangle.
     *
     * @param lowerLeft  - geometry.primitives.Point start
     * @param lowerRight - geometry.primitives.Point end
     */
    private void setLowerLine(Point lowerLeft, Point lowerRight) {
        this.lowerLine = new Line(lowerLeft, lowerRight);
    }

    /**
     * setting the left-line of the rectangle.
     *
     * @param upperLeft  - geometry.primitives.Point start
     * @param lowerRight - geometry.primitives.Point end
     */
    private void setLeftLine(Point upperLeft, Point lowerRight) {
        this.leftLine = new Line(upperLeft, lowerRight);
    }

    /**
     * setting the right-line of the rectangle.
     *
     * @param upperRight - geometry.primitives.Point start
     * @param lowerLeft  - geometry.primitives.Point end
     */
    private void setRightLine(Point upperRight, Point lowerLeft) {
        this.rightLine = new Line(upperRight, lowerLeft);
    }

    /**
     * Getter for upper-line.
     *
     * @return geometry.primitives.Line
     */
    public Line getUpperLine() {
        return this.upperLine;
    }

    /**
     * Getter for the lower-line.
     *
     * @return geometry.primitives.Line
     */
    public Line getLowerLine() {
        return this.lowerLine;
    }

    /**
     * Getter for the left-line.
     *
     * @return geometry.primitives.Line
     */
    public Line getLeftLine() {
        return this.leftLine;
    }

    /**
     * Getter for the right-line.
     *
     * @return geometry.primitives.Line
     */
    public Line getRightLine() {
        return this.rightLine;
    }

    /**
     * Given a line check al the intersection point with the rectangle and return a list of the point.
     * can return null if there is no intersection.
     *
     * @param line - geometry.primitives.Line
     * @return - ArrayList of points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> lst = new ArrayList<Point>();
        Point p = line.intersectionWith(this.getLeftLine());
        if (p != null) {
            lst.add(p);
        }
        p = line.intersectionWith(this.getRightLine());
        if (p != null) {
            lst.add(p);
        }
        p = line.intersectionWith(this.getUpperLine());
        if (p != null) {
            lst.add(p);
        }
        p = line.intersectionWith(this.getLowerLine());
        if (p != null) {
            lst.add(p);
        }
        return lst;
    }

    /**
     * Getter for the width of the rectangle.
     *
     * @return double
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter for the height of the rectangle.
     *
     * @return double
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getter for the upper-left-point of the rectangle.
     *
     * @return - geometry.primitives.Point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * check if 2 rectangle is the same.
     * @param r1 - geometry.primitives.Rectangle
     * @return true if they are the same, false otherwise.
     */
    public boolean isSameRectangle(Rectangle r1) {
        return this.getLeftLine().start().equals(r1.getLeftLine().start())
                && this.getLeftLine().end().equals(r1.getLeftLine().end())
                && this.getRightLine().start().equals(r1.getRightLine().start())
                && this.getRightLine().end().equals(r1.getRightLine().end())
                && this.getLeftLine().start().equals(r1.getLeftLine().start())
                && this.getLeftLine().end().equals(r1.getLeftLine().end())
                && this.getLowerLine().start().equals(r1.getLowerLine().start())
                && this.getLowerLine().end().equals(r1.getLowerLine().end());
    }
}