// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class YouLose extends KeyPressStoppableAnimation implements Animation {

    private AnimationRunner ar;
    private int score;

    /**
     * Constructor.
     *
     * @param ar    - AnimationRunner.
     * @param score - int.
     */
    public YouLose(AnimationRunner ar, int score) {
        super(ar.getGui().getKeyboardSensor(), KeyboardSensor.SPACE_KEY);
        super.setAnimation(this);
        this.ar = ar;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.drawText(200, 300, "Game Over! Your score is " + this.score, 30);
    }

    @Override
    public boolean shouldStop() {
        return super.shouldStop();
    }
}
