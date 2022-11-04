// 315318766 Omer Bar

package game;

import collision.detection.Sprite;
import geometry.primitives.Velocity;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public interface LevelInformation {
    /**
     * number of balls for the level.
     * @return - int
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return - List
     */
    List<Velocity> initialBallVelocities();

    /**
     * the paddle speed for this level.
     * @return - int
     */
    int paddleSpeed();

    /**
     * the paddle width for this level.
     * @return - int.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return - String
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return - Sprite
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return List.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return - int.
     */
    int numberOfBlocksToRemove();
}