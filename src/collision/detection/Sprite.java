// 315318766 Omer Bar

package collision.detection;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public interface Sprite {

    /**
     * drawOn method for all of the sprite objects.
     * @param d - DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify all the sprite collection that they need to updated.
     */
    void timePassed();

    /**
     * add the given collision.detection.Sprite to the game object.
     * @param g - game.Game
     */
    void addToGame(GameLevel g);
}