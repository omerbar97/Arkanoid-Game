// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.detection.SpriteCollection;
import java.awt.Color;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class PauseScreen extends KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private GameLevel game;
    private SpriteCollection lst;

    /**
     * Constructor.
     * @param k - KeyboardSensor.
     * @param lst - SpriteCollection.
     * @param game - GameLevel.
     */
    public PauseScreen(KeyboardSensor k, SpriteCollection lst, GameLevel game) {
        super(k, KeyboardSensor.SPACE_KEY);
        super.setAnimation(this);
        this.keyboard = k;
        this.stop = true;
        this.lst = lst;
        this.game = game;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.lst.drawAllOn(d);
        // checking if the balls color are black therefore the background color is white so changeing text to be seen.
        if (game.getBallsVelocity().get(0).getColor() != Color.black) {
            d.setColor(Color.white);
        } else {
            d.setColor(Color.black);
        }
        d.drawText(160, d.getHeight() / 2 + 100, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return super.shouldStop();
    }
}