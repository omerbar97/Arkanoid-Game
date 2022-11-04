// 315318766 Omer Bar

package collision.detection;

import game.Ball;
import game.Block;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 10-05-2022
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the ball that's doing the hitting.
     *
     * @param beingHit - Block
     * @param hitter   - Ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
