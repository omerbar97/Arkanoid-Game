// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import collision.detection.SpriteCollection;
import geometry.primitives.Velocity;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private int decreseingNumber;
    private SpriteCollection gameScreen;
    private StringBuilder countFormat = new StringBuilder();
    private boolean running = false;
    private AnimationRunner runner;
    private final double timeForEachNumber;
    private Velocity velocity;

    /**
     * Constructor for the Object.
     * @param numOfSeconds - double
     * @param countFrom - int
     * @param gameScreen - SpriteCollection
     * @param velocity - Velocity.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Velocity velocity) {
        this.numOfSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.timeForEachNumber = this.numOfSeconds / countFrom;
        this.decreseingNumber = countFrom;
        this.velocity = velocity;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // need to do for example for countFrom 4: 4..3..2..1..Start!
        //first of all we need to draw all the Sprite collection and on top of that we will add the counter
        // total time is the value of numOfSeconds
        Sleeper sleeper = new Sleeper();
        this.runner.setFramesPerSecond((int) this.timeForEachNumber);
        this.gameScreen.drawAllOn(d);
        if (this.decreseingNumber == 0) {
            this.countFormat.append("Start!");
            this.decreseingNumber--;
        } else {
            this.countFormat.append(this.decreseingNumber--);
            this.countFormat.append("..");
        }
        d.setColor(this.velocity.getColor());
        d.drawText(Commons.FRAME_WIDTH / 2 - 140, 150, this.countFormat.toString(), 50);
        if (this.decreseingNumber + 1 != this.countFrom) {
            sleeper.sleepFor((long) this.timeForEachNumber);
        }
    }

    @Override
    public boolean shouldStop() {
        if (this.decreseingNumber >= -1) {
            return this.running;
        }
        return !this.running;
    }

    /**
     * Getter for Runner.
     * @return - AnimationRunner.
     */
    public AnimationRunner getRunner() {
        return runner;
    }

    /**
     * Setter for Runner.
     * @param runner - AnimationRunner.
     */
    public void setRunner(AnimationRunner runner) {
        this.runner = runner;
    }
}