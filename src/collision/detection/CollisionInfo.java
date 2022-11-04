// 315318766 Omer Bar

package collision.detection;

import geometry.primitives.Point;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public class CollisionInfo {

    private Collidable collisionObject;
    private Point collisionPoint;

    /**
     * Constructor for the collision.detection.CollisionInfo.
     *
     * @param collisionObject - collision.detection.Collidable
     * @param collisionPoint  - geometry.primitives.Point
     */
    public CollisionInfo(Collidable collisionObject, Point collisionPoint) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }


    /**
     * the point at which the collision occurs.
     *
     * @return - geometry.primitives.Point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }


    /**
     * the collidable object involved in the collision.
     *
     * @return - collision.detection.Collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}