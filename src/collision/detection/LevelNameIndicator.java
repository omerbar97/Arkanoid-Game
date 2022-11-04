// 315318766 Omer Bar

package collision.detection;

import biuoop.DrawSurface;
import game.Commons;
import game.GameLevel;

import java.awt.Color;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class LevelNameIndicator implements Sprite {
    private GameLevel game;

    /**
     * constructor.
     *
     * @param game - Game
     */
    public LevelNameIndicator(GameLevel game) {
        this.game = game;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(Commons.LEVEL_NAME_STARTING_X, Commons.LEVEL_NAME_STARTING_Y,
                "Level Name: " + this.game.getLevel().levelName(), Commons.TEXT_SIZE);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
