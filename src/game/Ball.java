// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.detection.CollisionInfo;
import collision.detection.HitListener;
import collision.detection.Sprite;
import geometry.primitives.Line;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import geometry.primitives.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 17-03-2022
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment game;
    private List<Point> trails; // array to save the ball trails for the ball trails effect.
    private List<HitListener> hitListeners;

    // constructor

    /**
     * constructor for game.Ball object.
     *
     * @param center - Beginning geometry.primitives.Point
     * @param r      - int, Radios of the game.Ball
     * @param color  - Color of the game.Ball
     * @param l      - List of HitListeners.
     */
    public Ball(Point center, int r, Color color, List<HitListener> l) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = null;
        this.trails = new ArrayList<>();
        this.hitListeners = l;
    }

    /**
     * different constructor.
     *
     * @param x     - x for the geometry.primitives.Point
     * @param y     - y for the geometry.primitives.Point
     * @param r     - int, Radios of the game.Ball
     * @param color - Color of the game.Ball
     * @param l     - List of HitListeners.
     */
    public Ball(double x, double y, int r, Color color, List<HitListener> l) {
        this(new Point(x, y), r, color, l);
    }

    /**
     * Getter for X.
     *
     * @return int
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Getter for Y.
     *
     * @return int
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Getter for r.
     *
     * @return int
     */
    public int getSize() {
        return r;
    }

    /**
     * Getter for the game.Game Environment.
     *
     * @return game.GameEnvironment
     */
    public GameEnvironment getGame() {
        return game;
    }

    /**
     * Setter for the game.GameEnvironment.
     *
     * @param game - game.GameEnvironment
     */
    public void setGame(GameEnvironment game) {
        this.game = game;
    }

    /**
     * Getter for color.
     *
     * @return Color
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface - DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        int size = this.getSize();
        int colorSetter = 25;
        // drawing a trail the follows the ball
        if (this.trails != null && this.trails.size() >= 6) {
            for (int i = this.trails.size() - 1; i >= trails.size() - 6; i = i - 1) {
                surface.setColor(new Color(150 - colorSetter, 110 + colorSetter, 220 - colorSetter));
                colorSetter += 10;
                surface.fillCircle((int) this.trails.get(i).getX(), (int) this.trails.get(i).getY(), size);
                size -= 1;
            }
            this.trails.remove(trails.size() - 6);
        }
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Setter for geometry.primitives.Velocity.
     *
     * @param v - geometry.primitives.Velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Setter for geometry.primitives.Velocity.
     *
     * @param dx - double
     * @param dy - double
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Getter for velocity.
     *
     * @return geometry.primitives.Velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move the ball around with the velocity that it is defined.
     */
    public void moveOneStep() {
        Point end = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, end);
        this.trails.add(this.center);
        double newX, newY;
        boolean flag = false;
        // when there is no collision then just forward the ball
        CollisionInfo c = this.getGame().getClosestCollision(trajectory);
        if (c == null) {
            // need to check next move of that ball
            // if there is going to be collision in the next move in the paddle then update the location
            Point nextMove = this.getVelocity().applyToPoint(end);
            Line nextTrajectory = new Line(end, nextMove);
            CollisionInfo nextCollision = this.getGame().getClosestCollision(nextTrajectory);
            if (nextCollision == null || !nextCollision.collisionObject().getCollisionRectangle()
                    .isSameRectangle(this.getGame().getPaddle().getCollisionRectangle())) {
                this.center = this.getVelocity().applyToPoint(this.center);
            } else {
                if (this.getGame().getGUI().getKeyboardSensor().isPressed(KeyboardSensor.RIGHT_KEY)
                        && this.getGame().getPaddle().getCollisionRectangle().getRightLine().start().getX()
                        < Commons.FRAME_WIDTH - Commons.SCREEN_BLOCK_WIDTH) {
                    // need to check if the collision point is inside the paddle
                    // the next code is going to handle the bug that happen when the player is updating the paddle to
                    // the right and the next move supposed to be inside the paddle, therefore we are checking
                    // the next paddle rectangle collision of the next ball movement if there is a collision
                    // then update the ball as it should be.
                    // if the player is pressing the RIGHT KEY and the paddle is at the edge then this code will not
                    // be executed.
                    double x, y;
                    x = this.getGame().getPaddle().getCollisionRectangle().getUpperLeft().getX()
                            + Commons.PADDLE_MOVEMENT;
                    y = this.getGame().getPaddle().getCollisionRectangle().getUpperLeft().getY();
                    Point p = new Point(x, y);
                    Rectangle paddleR = new Rectangle(p, this.getGame().getPaddle().getCollisionRectangle().getWidth(),
                            this.getGame().getPaddle().getCollisionRectangle().getHeight());
                    Point close = nextTrajectory.closestIntersectionToStartOfLine(paddleR);
                    if (close != null && close.getX() <= paddleR.getRightLine().start().getX()) {
                        newX = close.getX() + Commons.PADDLE_MOVEMENT + 2;
                        newY = close.getY();
                        this.center = new Point(newX, newY);
                        this.setVelocity(nextCollision.collisionObject().hit(this, nextCollision.collisionPoint(),
                                this.getVelocity()));
                        flag = true;
                    }
                } else if (this.getGame().getGUI().getKeyboardSensor().isPressed(KeyboardSensor.LEFT_KEY)
                        && this.getGame().getPaddle().getCollisionRectangle().getUpperLeft().getX()
                        > Commons.SCREEN_BLOCK_WIDTH) {
                    // need to check if the collision point is inside the paddle
                    // the next code is going to handle the bug that happen when the player is updating the paddle to
                    // the left and the next move supposed to be inside the paddle, therefore we are checking
                    // the next paddle rectangle collision of the next ball movement if there is a collision
                    // then update the ball as it should be.
                    // if the player is pressing the LEFT KEY and the paddle is at the edge then this code will not
                    // be executed.
                    double x, y;
                    x = this.getGame().getPaddle().getCollisionRectangle().getUpperLeft().getX()
                            + Commons.PADDLE_MOVEMENT;
                    y = this.getGame().getPaddle().getCollisionRectangle().getUpperLeft().getY();
                    Point p = new Point(x, y);
                    Rectangle paddleL = new Rectangle(p, this.getGame().getPaddle().getCollisionRectangle().getWidth(),
                            this.getGame().getPaddle().getCollisionRectangle().getHeight());
                    Point close = nextTrajectory.closestIntersectionToStartOfLine(paddleL);
                    if (close != null && close.getX() >= paddleL.getLeftLine().start().getX()) {
                        newX = close.getX() - Commons.PADDLE_MOVEMENT - 2;
                        newY = close.getY();
                        this.center = new Point(newX, newY);
                        this.setVelocity(nextCollision.collisionObject().hit(this, nextCollision.collisionPoint(),
                                this.getVelocity()));
                        flag = true;
                    }
                }
                if (!flag) {
                    // in case none of the other if executed then do this code
                    // when the next collision is not null but there is no keyboard pressed.
                    // means there is going to be a hit in the paddle but the paddle won't move.
                    this.center = nextCollision.collisionPoint();
                    this.setVelocity(nextCollision.collisionObject().hit(this, nextCollision.collisionPoint(),
                            this.getVelocity()));
                }
            }
        } else {
            // taking the average length of the trajectory to modify the ball within the hit
            double avg = trajectory.length() / 2;
            // each collision set the ball location differently.
            if (c.collisionObject().getCollisionRectangle().getUpperLine().isPointExist(c.collisionPoint())) {
                newX = c.collisionPoint().getX();
                newY = c.collisionPoint().getY() - avg;
            } else if (c.collisionObject().getCollisionRectangle().getLowerLine().isPointExist(c.collisionPoint())) {
                newX = c.collisionPoint().getX();
                newY = c.collisionPoint().getY() + avg;
            } else if (c.collisionObject().getCollisionRectangle().getLeftLine().isPointExist(c.collisionPoint())) {
                newX = c.collisionPoint().getX() - avg;
                newY = c.collisionPoint().getY();
            } else {
                newX = c.collisionPoint().getX() + avg;
                newY = c.collisionPoint().getY();
            }
            // check if the ball went over the edge in the X
            // this is bug handle when the ball escape the screen.
            if (newX > Commons.FRAME_WIDTH - Commons.SCREEN_BLOCK_WIDTH) {
                newX = Commons.FRAME_WIDTH - Commons.SCREEN_BLOCK_WIDTH - 1;
            } else if (newX < Commons.SCREEN_BLOCK_WIDTH) {
                newX = Commons.SCREEN_BLOCK_WIDTH + 1;
            }
            // check if the ball went over the edge in the Y
            if (c.collisionObject().getCollisionRectangle().getRecID() == Commons.BlockKIND.BALL_DEATH_BLOCK) {
                this.notifyHit((Block) c.collisionObject());
            } else if (newY < Commons.SCREEN_BLOCK_HEIGHT) {
                newY = Commons.SCREEN_BLOCK_HEIGHT + 1;
            }
            this.center = new Point(newX, newY);
            this.setVelocity(c.collisionObject().hit(this, c.collisionPoint(), this.getVelocity()));
        }
    }

    protected void notifyHit(Block gotHit) {
        // Make a copy of the hitListeners before iterating over them.
        if (gotHit == null) {
            return;
        }
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(gotHit, this);
        }
    }

    /**
     * removing the ball object from the game.
     *
     * @param game - Game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
