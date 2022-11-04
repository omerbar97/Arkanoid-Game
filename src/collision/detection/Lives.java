// 315318766 Omer Bar

package collision.detection;

import biuoop.DrawSurface;
import game.Commons;
import game.Counter;
import game.GameLevel;
import java.awt.Color;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class Lives implements Sprite {

    private Counter livesCounter;

    /**
     * Constructor for Lives object.
     * @param livesCounter - Counter
     */
    public Lives(Counter livesCounter) {
        this.livesCounter = livesCounter;
    }

    /**
     * returning if the player is dead.
     * @return - boolean.
     */
    public boolean isDead() {
        return this.livesCounter.getValue() <= 0;
    }

    /**
     * decrasing the live by i.
     * @param i - int
     */
    public void decreaseLive(int i) {
        this.livesCounter.decrease(i);
    }

    /**
     * increasing the live by i.
     * @param i - int
     */
    public void increaseLive(int i) {
        this.livesCounter.increase(i);
    }

    @Override
    public String toString() {
        return "Lives: " + this.livesCounter.getValue();
    }

    /**
     * Getter for the counter of lives.
     * @return - Counter.
     */
    public Counter getLive() {
        return this.livesCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(Commons.LIVE_TEXT_STARTING_X, Commons.LIVE_TEXT_STARTING_Y,
                this.toString(), Commons.TEXT_SIZE);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
