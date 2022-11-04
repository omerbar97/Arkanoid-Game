// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.detection.Collidable;
import collision.detection.Sprite;
import geometry.primitives.Line;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import geometry.primitives.Velocity;


import java.awt.Color;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public class Paddle extends Commons implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Block paddle;
    private int paddlespeed = 10; // defalut
    /**
     * Constructor for the paddle.
     *
     * @param keyboard - KeyboardSensor
     * @param r        - geometry.primitives.Rectangle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle r) {
        this.keyboard = keyboard;
        Block paddle = new Block(r, Commons.DEFAULT_PADDLE_COLOR, null);
        paddle.setBlockID(BlockKIND.PADDLE);
        this.paddle = paddle;
        this.paddle.setBlockID(BlockKIND.PADDLE); // default
    }

    /**
     * Constructor.
     * @param keyboard - KeyboardSensor
     * @param r - Rectangle.
     * @param paddlespeed - int
     */
    public Paddle(KeyboardSensor keyboard, Rectangle r, int paddlespeed) {
        this.keyboard = keyboard;
        Block paddle = new Block(r, Commons.DEFAULT_PADDLE_COLOR, null);
        paddle.setBlockID(BlockKIND.PADDLE);
        this.paddle = paddle;
        this.paddlespeed = paddlespeed;
    }

    /**
     * Setter for paddleID.
     * @param id - BlockKIND
     */
    public void setPaddleID(BlockKIND id) {
        this.paddle.setBlockID(id);
    }

    /**
     * Another constructor for the rectangle.
     *
     * @param r - geometry.primitives.Rectangle
     */
    public Paddle(Rectangle r) {
        Block paddle = new Block(r, Commons.DEFAULT_PADDLE_COLOR, null);
        paddle.setBlockID(BlockKIND.PADDLE);
        this.paddle = paddle;
    }

    /**
     * Setter for the keyboard Sensor.
     *
     * @param keyboard - KeyboardSensor
     */
    public void setKeyboard(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * Getter for the paddleMovement.
     * @return int
     */
    public int getPaddleMovement() {
        return paddlespeed;
    }

    /**
     * Setter for color.
     * @param c - color
     */
    public void setColor(Color c) {
        this.paddle.setColor(c);
    }


    /**
     * move the paddle block left with the bound of 20 pixels from the end of the left screen.
     * moving the paddle at speed of 10 pixels at each press.
     */
    public void moveLeft() {
        // stops when the paddle hit the bound of the screen
        if (this.getCollisionRectangle().getUpperLeft().getX() <= SCREEN_BLOCK_WIDTH) {
            return;
        }
        int x, y;
        x = (int) this.paddle.getCollisionRectangle().getUpperLeft().getX() - paddlespeed;
        y = (int) this.paddle.getCollisionRectangle().getUpperLeft().getY();
        Point p = new Point(x, y);
        Rectangle r = new Rectangle(p, this.paddle.getCollisionRectangle().getWidth(),
                this.paddle.getCollisionRectangle().getHeight());
        this.paddle = new Block(r, this.paddle.getColor(), null);
    }

    /**
     * move the paddle block right with the bound of 20 pixels from the end of the right screen.
     * moving the paddle at speed of 10 pixels at each press.
     */
    public void moveRight() {
        // stops when the paddle hit the bound of the SCREEN
        if (this.getCollisionRectangle().getRightLine().start().getX() >= FRAME_WIDTH - SCREEN_BLOCK_WIDTH) {
            return;
        }
        int x, y;
        x = (int) this.paddle.getCollisionRectangle().getUpperLeft().getX() + paddlespeed;
        y = (int) this.paddle.getCollisionRectangle().getUpperLeft().getY();
        Point p = new Point(x, y);
        Rectangle r = new Rectangle(p, this.paddle.getCollisionRectangle().getWidth(),
                this.paddle.getCollisionRectangle().getHeight());
        this.paddle = new Block(r, this.paddle.getColor(), null);
    }

    /**
     * this function is updating the game if the player pressed the keyboard.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawOn method for the paddle.
     *
     * @param d - DrawSurface
     */
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }


    /**
     * getting the geometry.primitives.Velocity for the ball when it hits the paddle block.
     *
     * @param collisionPoint  - geometry.primitives.Point
     * @param currentVelocity - geometry.primitives.Velocity
     * @return geometry.primitives.Velocity
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // dividing the game paddle to 5 parts.
        double width = this.getCollisionRectangle().getWidth() / 5;
        Line stage1;
        Point pStart = new Point(this.paddle.getCollisionRectangle().getUpperLeft().getX(),
                this.paddle.getCollisionRectangle().getUpperLeft().getY());
        Point pEnd = new Point(this.paddle.getCollisionRectangle().getUpperLeft().getX() + width,
                this.paddle.getCollisionRectangle().getUpperLeft().getY());
        stage1 = new Line(pStart, pEnd);
        // ball get different velocity when it hits different part of the paddle.
        if (this.getCollisionRectangle().getUpperLine().isPointExist(collisionPoint)
                || this.getCollisionRectangle().getRightLine().isPointExist(collisionPoint)
                || this.getCollisionRectangle().getLeftLine().isPointExist(collisionPoint)) {
            if (collisionPoint.getX() <= stage1.end().getX()) {
                return Velocity.fromAngleAndSpeed(LEFT_LEFT_HIT_PADDLE_ANGLE, currentVelocity.getSpeed());
            }
            if (collisionPoint.getX() > stage1.end().getX()
                    && collisionPoint.getX() <= stage1.end().getX() + width) {
                return Velocity.fromAngleAndSpeed(LEFT_HIT_PADDLE_ANGLE, currentVelocity.getSpeed());
            }
            if (collisionPoint.getX() > stage1.end().getX() + width
                    && collisionPoint.getX() <= stage1.end().getX() + 2 * width) {
                return Velocity.fromAngleAndSpeed(MIDDLE_HIT_PADDLE_ANGLE, currentVelocity.getSpeed());
            }
            if (collisionPoint.getX() > stage1.end().getX() + 2 * width
                    && collisionPoint.getX() <= stage1.end().getX() + 3 * width) {
                return Velocity.fromAngleAndSpeed(RIGHT_HIT_PADDLE_ANGLE, currentVelocity.getSpeed());
            }
            if (collisionPoint.getX() > stage1.end().getX() + 3 * width
                    && collisionPoint.getX() <= stage1.end().getX() + 4 * width) {
                return Velocity.fromAngleAndSpeed(RIGHT_RIGHT_HIT_PADDLE_ANGLE, currentVelocity.getSpeed());
            }
        }
        return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * Getter for the rectangle.
     *
     * @return - geometry.primitives.Rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * add method for the paddle.
     *
     * @param g - game.Game
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removeFromGame.
     * @param game GameLevel
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}