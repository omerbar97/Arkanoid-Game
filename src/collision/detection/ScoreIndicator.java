// 315318766 Omer Bar

package collision.detection;

import biuoop.DrawSurface;
import game.Commons;
import game.GameLevel;
import java.awt.Color;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 10-05-2022
 */
public class ScoreIndicator implements Sprite {

    private GameLevel game;

    /**
     * constructor.
     * @param game - Game
     */
    public ScoreIndicator(GameLevel game) {
        this.game = game;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(Commons.SCORE_STARTING_X, Commons.SCORE_STARTING_Y,
                this.game.getScore().toString(), Commons.TEXT_SIZE);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
