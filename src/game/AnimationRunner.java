// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor for the Object.
     * @param gui - GUI
     * @param framesPerSecond - int
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Getter for GUI.
     * @return - GUI
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Getter for the framesPerSecond field.
     * @return - int
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     * Setter for the framesPerSecond field.
     * @param framesPerSecond - int
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * run method. take an animation and run it.
     * @param animation - Animation.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / this.getFramesPerSecond();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.getGui().getDrawSurface();
            animation.doOneFrame(d);
            this.getGui().show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}