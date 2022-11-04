// 315318766 Omer Bar

package geometry.primitives;

import game.Commons;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 17-03-2022
 */
public class Line {

    private final Point start;
    private final Point end;

    /**
     * constructor by geometry.primitives.Point.
     *
     * @param start geometry.primitives.Point
     * @param end   geometry.primitives.Point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor by values.
     *
     * @param x1 double
     * @param y1 double
     * @param x2 double
     * @param y2 double
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * calculate the length of the line.
     *
     * @return length in double
     */
    public double length() {
        return this.start().distance(this.end());
    }

    /**
     * calculate the middle point of the line.
     *
     * @return middle geometry.primitives.Point
     */
    public Point middle() {
        double x, y;
        x = (this.start().getX() + this.end().getX()) / 2;
        y = (this.start().getY() + this.end().getY()) / 2;
        return (new Point(x, y));
    }

    /**
     * getter for start geometry.primitives.Point.
     *
     * @return start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * getter for end geometry.primitives.Point.
     *
     * @return end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * check if the incline is defined (not defined when start.x == end.x).
     *
     * @return true when it is defined, otherwise false
     */
    private boolean isInclineDefine() {
        return !(Math.abs(this.start().getX() - this.end().getX()) < Commons.TRASH);
    }


    /**
     * calculate the incline of a line.
     *
     * @return incline - double
     */
    private double getIncline() {
        double m = (this.start().getY() - this.end().getY()) / (this.start().getX() - this.end().getX());
        // bug handle
        /*if (m == -0) {
            return 0;
        }*/
        return m;
    }

    /**
     * check if 2 line are the same line.
     *
     * @param l2 - geometry.primitives.Line
     * @return true when they are the same, otherwise false.
     */
    public boolean equal(Line l2) {
        if (l2 != null) {
            return (this.start().equals(l2.start()) && this.end().equals(l2.end()))
                    || (this.end().equals(l2.start()) && this.start().equals(l2.end()));
        }
        return false;
    }

    /**
     * check if a given geometry.primitives.Point is on the line.
     *
     * @param p geometry.primitives.Point
     * @return true if it is on the line, otherwise false.
     */
    public boolean isPointExist(Point p) {
        // in case the Incline is not define check if the point is in the line
        if (!(this.isInclineDefine())) {
            if (Math.abs(this.start().getX() - p.getX()) > Commons.TRASH) {
                return false;
            }
        }
        //otherwise, when the incline is defined
        // Y = m*X + y1 - m*x1
        double d1 = p.distance(this.start());
        double d2 = p.distance(this.end());
        if ((d1 <= this.length() && d2 <= this.length() && this.isPointInEquation(p)) || d1 + d2 <= this.length()) {
            return true;
        }
        return false;
    }

    /**
     * check if 2 line's intersect.
     *
     * @param l2 geometry.primitives.Line
     * @return true if they are intersecting, otherwise false.
     */
    public boolean isIntersecting(Line l2) {
        if (this.start().getX() != this.end().getX() && l2.start().getX() != l2.end().getX()) {
            double m1 = this.getIncline();
            double m2 = l2.getIncline();
            // in case they are the same line return true otherwise return false
            if (m1 == m2) {
                return this.equal(l2);
            }
        } else if (this.start().getX() == this.end().getX() && l2.start().getX() == l2.end().getX()) {
            //both of the line in the same X
            //when there is overlapping
            return (this.isPointExist(l2.start()))
                    || (this.isPointExist((l2.end())))
                    || (l2.isPointExist(this.start()))
                    || (l2.isPointExist(this.end()));
        }
        // returning true if there is intersection point
        return (this.intersectionWith(l2) != null);
    }

    /**
     * calculate the geometry.primitives.Point of intersection.
     *
     * @param l2 geometry.primitives.Line
     * @return geometry.primitives.Point if they are intersecting, otherwise return null.
     */
    public Point intersectionWith(Line l2) {
        // need to fix it not all cases is correct in the Y AXES.
        if (compDouble(this.start().getX(), this.end().getX()) && !compDouble(l2.start().getX(), l2.end().getX())) {
            // first line incline is not defined and l2 incline is defined line is in form X = number
            // second line equation Y - m2*X + m2*x2 - y2 = 0
            // Y = m2*X - m2*x2 + y2
            double m2 = l2.getIncline();
            double y2 = (m2 * (this.start().getX())) - (m2 * (l2.start().getX())) + l2.start().getY();
            Point p = new Point(this.start().getX(), y2);
            if ((this.isPointExist(p)) && l2.isPointExist(p)) {
                return p;
            }
        } else if (!compDouble(this.start().getX(), this.end().getX())
                && compDouble(l2.start().getX(), l2.end().getX())) {
            // second line incline is not defined and first line incline is defined line is in form X = number
            // first line equation Y - m1*X + m1*x1 - y1 = 0
            // Y = m1*X - m1*x1 + y1
            double m1 = this.getIncline();
            double y1 = (m1 * (l2.start().getX())) - (m1 * (this.start().getX())) + this.start().getY();
            Point p = new Point(l2.start().getX(), y1);
            if (l2.isPointExist(p) && this.isPointExist(p)) {
                return p;
            }
        } else if (compDouble(this.start().getX(), this.end().getX())
                && compDouble(l2.start().getX(), l2.end().getX())) {
            return null;
        } else {
            // if none of the cases above then calculate the intersection by equation
            double m1 = this.getIncline();
            double m2 = l2.getIncline();
            double x1, x2, y1, y2, newX, newY;
            x1 = this.start().getX();
            y1 = this.start().getY();
            x2 = l2.start().getX();
            y2 = l2.start().getY();
            // first line equation Y - m1*X + m1*x1 - y1 = 0
            // second line equation Y - m2*X + m2*x2 - y2 = 0
            // solving it by matrix
            // ( 1  -m1  m1*x1-y1 | 0 )
            // ( 1  -m2  m2*x2-y2 | 0 ) ----> R2 - R1 -> R2
            //****************************************************************************************
            // ( 1    -m1         m1*x1-y1      | 0 )
            // ( 0   -m2+m1   m2*x2-m1*x1+y1-y2 | 0 ) ----> (1/(-m2+m1))*R2 -> R2 in case -m2+m1 != 0
            //****************************************************************************************
            //        ( 1    -m1               m1*x1-y1               | 0 )
            //        ( 0      1     (m2*x2-m1*x1+y1-y2)/(-m2+m1))    | 0 )  ----> R1 + m1*R2 -> R1
            //****************************************************************************************
            //        ( 1        0       (m1*x1-y1) + m1*(m2*x2-m1*x1+y1-y2)/(-m2+m1))      | 0 )
            //        ( 0        1             (m2*x2-m1*x1+y1-y2)/(-m2+m1))                | 0 )
            //****************************************************************************************
            //        newY = -1*(m1*((m2*x2-m1*x1+y1-y2)/(-m2+m1))) - m1*x1 + y1
            //        newX = -1*((m2*x2-m1*x1+y1-y2)/(-m2+m1))
            newY = -1 * (m1 * (((m2 * x2) - (m1 * x1) + y1 - y2) / (-m2 + m1))) - m1 * x1 + y1;
            newX = -1 * (((m2 * x2) - (m1 * x1) + y1 - y2) / (-m2 + m1));
            /*// bugs handle
            if (newY == -0) {
                newY = 0;
            }
            // bugs handle
            if (newX == -0) {
                newX = 0;
            }*/
            Point p = new Point(newX, newY);
            if (this.isPointExist(p) && l2.isPointExist(p)) {
                return p;
            }
        }
        return null;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.

    /**
     * if the line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect - geometry.primitives.Rectangle
     * @return - geometry.primitives.Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List lst = rect.intersectionPoints(this);
        //when the list is empty means there is no intersection with the rectangle.
        if (lst.size() == 0) {
            return null;
        }
        Point temp = (Point) lst.get(0);
        Point result = temp;
        double d = this.start().distance(temp);
        double t;
        for (int i = 1; i < lst.size(); i++) {
            temp = (Point) lst.get(i);
            t = this.start().distance(temp);
            if (t < d) {
                d = t;
                result = temp;
            }
        }
        return result;
    }

    /**
     * compare 2 double numbers with difference of 1E-10, return true if they are the same.
     * @param a - double
     * @param b - double
     * @return - boolean
     */
    private boolean compDouble(double a, double b) {
        return Math.abs(a - b) < Commons.TRASH;
    }

    /**
     * check if a given point is in the line equation.
     * @param p - geometry.primitives.Point
     * @return - boolean
     */
    private boolean isPointInEquation(Point p) {
        if (this.isInclineDefine()) {
            double m, x, y;
            m = this.getIncline();
            x = p.getX();
            y = p.getY();
            //****************************************************************************************
            // y - y1 = m(x - x1)
            // y = mx - mx1 + y1
            //****************************************************************************************
            return compDouble(y, (m * x) - (this.start().getX() * m) + this.start().getY());
        }
        return false;
    }
}

