// 315318766 Omer Bar

package geometry.primitives;

import biuoop.DrawSurface;
import collision.detection.Sprite;
import game.GameLevel;
import java.awt.Color;
import java.util.Random;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 12-04-2022
 */
public class Drop implements Sprite {

    private float x;
    private float y;
    private final float speed;
    private final Color color;
    private int endX;
    private int endY;

    /**
     * Constructor for the Drops.
     *
     * @param x     - float
     * @param y     - float
     * @param speed - float
     * @param c     - Color
     * @param endX  - int
     * @param endY  - int
     */
    public Drop(float x, float y, float speed, Color c, int endX, int endY) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = c;
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * Getter for the X.
     *
     * @return float
     */
    public float getX() {
        return x;
    }

    /**
     * Setter for the X.
     *
     * @param x - float
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Getter for the Y.
     *
     * @return float
     */
    public float getY() {
        return y;
    }

    /**
     * Setter for the Y.
     *
     * @param y - float
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Getter for the speed.
     *
     * @return float
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * fall method, taking a drop object and moving it forward that will look like a fallen drop.
     */
    public void fall() {
        Random rnd = new Random();
        if (this.getY() > this.endY) {
            this.setY(rnd.nextInt(20));
        }
        if (this.getX() > this.getX()) {
            this.setX((rnd.nextInt(100)));
        } else {
            this.setX(this.getSpeed() + this.getX());
            this.setY(this.getSpeed() + this.getY());
        }
    }

    /**
     * Getter for the Color.
     *
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // generating random color once in a while.
        if ((this.getY() + this.getX()) % 3 == 0 && this.getSpeed() > 6) {
            d.setColor(new Color(110, 70, 255));
        } else if ((this.getY() + this.getX()) % 4 == 0 && this.getSpeed() > 6) {
            d.setColor(new Color(150, 70, 200));
        } else {
            d.setColor(this.getColor());
        }
        d.drawLine((int) this.getX(), (int) this.getY(), (int) this.getX() + (int) this.getSpeed(),
                (int) this.getY() + (int) this.getSpeed());

    }

    @Override
    public void timePassed() {
        this.fall();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
