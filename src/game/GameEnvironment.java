// 315318766 Omer Bar

package game;

import biuoop.GUI;
import collision.detection.Collidable;
import collision.detection.CollisionInfo;
import geometry.primitives.Line;
import geometry.primitives.Point;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public class GameEnvironment {

    private List<Collidable> lst;
    private GUI gui;
    private Paddle paddle;

    /**
     * Constructor for the game environment.
     */
    public GameEnvironment() {
        this.lst = new ArrayList<>();
    }

    /**
     * Getter for the paddle.
     * @return game.Paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Setter for the paddle.
     * @param paddle
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c
     */
    public void addCollidable(Collidable c) {
        this.lst.add(c);
    }

    /**
     * method to remove a Collidable object from the collidable list.
     * @param c - Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.lst.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory - geometry.primitives.Line
     * @return - collision.detection.CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.lst.size() == 0) {
            return null;
        }
        Point p = null;
        Collidable c;
        int i = 0;
        while (p == null && i < lst.size()) {
            p = trajectory.closestIntersectionToStartOfLine(lst.get(i).getCollisionRectangle());
            i++;
        }
        if (p == null) {
            return null;
        }
        c = lst.get(i - 1);
        double distance = trajectory.start().distance(p);
        double temp = distance;
        for (; i < lst.size(); i++) {
            Point t = trajectory.closestIntersectionToStartOfLine(lst.get(i).getCollisionRectangle());
            if (t != null) {
                temp = t.distance(trajectory.start());
                if (temp < distance) {
                    distance = temp;
                    p = t;
                    c = lst.get(i);
                }
            }
        }
        return new CollisionInfo(c, p);
    }

    /**
     * Setter for GUI.
     * @param gui - GUI
     */
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    /**
     * Getter for GUI.
     * @return GUI
     */
    public GUI getGUI() {
        return this.gui;
    }

}