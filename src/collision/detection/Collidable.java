// 315318766 Omer Bar

package collision.detection;

import game.Ball;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import geometry.primitives.Velocity;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     *
     * @return - geometry.primitives.Rectangle
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us.
     *
     * @param collisionPoint  - geometry.primitives.Point
     * @param currentVelocity - geometry.primitives.Velocity
     * @param hitter          - Ball type
     * @return - geometry.primitives.Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}