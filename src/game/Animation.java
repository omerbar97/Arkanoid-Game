// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public interface Animation {

    /**
     * Getting a DrawSurface object and drawing one frame of the animation object in the DrawSurface.
     * @param d - DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * a boolean value that indicate when the animation should stop.
     * @return - boolean.
     */
    boolean shouldStop();
}