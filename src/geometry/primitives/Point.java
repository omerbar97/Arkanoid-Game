// 315318766 Omer Bar

package geometry.primitives;

import game.Commons;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 17-03-2022
 */
public class Point {

    private double x, y;

    /**
     * constructor.
     *
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculate the distance between 2 points.
     *
     * @param p2 - geometry.primitives.Point
     * @return the distance
     */
    public double distance(Point p2) {
        double x = Math.pow((this.getX() - p2.getX()), 2);
        double y = Math.pow((this.getY() - p2.getY()), 2);
        return Math.sqrt(x + y);
    }

    /**
     * check compare 2 points.
     *
     * @param p2 - geometry.primitives.Point
     * @return true if they are the same point, false otherwise
     */
    public boolean equals(Point p2) {
        return Math.abs(this.getX() - p2.getX()) < Commons.TRASH && Math.abs(this.getY() - p2.getY()) < Commons.TRASH;
    }

    /**
     * getter for X.
     *
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * setter for X.
     *
     * @param x double
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * getter for Y.
     *
     * @return x
     */
    public double getY() {
        return y;
    }

    /**
     * setter for Y.
     *
     * @param y double
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * print format for a geometry.primitives.Point.
     *
     * @return String format geometry.primitives.Point (x , y)
     */
    public String getPoint() {
        return "(" + getX() + " , " + getY() + ")";
    }
}
