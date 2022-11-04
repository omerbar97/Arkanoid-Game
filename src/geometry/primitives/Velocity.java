// 315318766 Omer Bar

package geometry.primitives;
import java.awt.Color;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 17-03-2022
 */
public class Velocity {

    private double dx;
    private double dy;
    private Point startingPoint = null; // defalut
    private Color color = null; // defalut


    /**
     * Constructor build.
     *
     * @param dx - double
     * @param dy - double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Getter for dx.
     *
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Getter for dy.
     *
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * Setter for dx.
     *
     * @param dx - the speed on the X
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Setter for dy.
     *
     * @param dy = the speed on the Y
     */
    public void setDy(double dy) {
        this.dy = dy;
    }


    /**
     * Given a point, changing the point by the value of the geometry.primitives.Velocity.
     *
     * @param p geometry.primitives.Point
     * @return new geometry.primitives.Point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }

    /**
     * returning the speed of the given object. (calculating pitagoras).
     *
     * @return double
     */
    public double getSpeed() {
        return Math.sqrt(this.getDx() * this.getDx() + this.getDy() * this.getDy());
    }

    /**
     * Getter for startingPoint.
     * @return - Point
     */
    public Point getStartingPoint() {
        return startingPoint;
    }

    /**
     * Setter for startingPoint.
     * @param startingPoint - Point
     */
    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    /**
     * Getter for Color.
     * @return - Color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter for Color.
     * @param color - Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * turning angle by Degree and speed to x, y coordinate.
     *
     * @param angle - double
     * @param speed - double
     * @return geometry.primitives.Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle % 360;
        angle -= 90; // to change the direction of the x and y.
        angle = Math.toRadians(angle);
        // trigonometry to calculate the dx and dy.
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }
}
