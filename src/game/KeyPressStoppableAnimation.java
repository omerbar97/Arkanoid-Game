// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class KeyPressStoppableAnimation implements Animation {


    private KeyboardSensor sensor;
    private String key;
    private Animation animation;

    /**
     * Constructor.
     * @param sensor - KeyboardSensor
     * @param key - String
     * @param animation - Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * Constructor.
     * @param sensor - KeyboardSensor
     * @param key - String
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key) {
        this.sensor = sensor;
        this.key = key;
    }

    /**
     * Setter for Animation.
     * @param animation - Animation
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        if (this.sensor.isPressed(this.key)) {
            return true;
        }
        return false;
    }
}
