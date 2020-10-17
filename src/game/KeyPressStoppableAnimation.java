package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * 205543317.
 */

public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor; // A keyboard sensor.
    private String key; // the key to release the stop.
    private Animation animation; // An animation to play while stopping.
    private boolean stop; // stops the stop.
    private boolean isAlreadyPressed; // is the key already was press before animation.

    /**
     * Initialize a KeyPressStoppableAnimation.
     *
     * @param sensor    a keyboard sensor.
     * @param key       the key to release the stop.
     * @param animation An animation to play while stopping.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (!this.isAlreadyPressed) {
            this.animation.doOneFrame(d);
            if (this.sensor.isPressed(this.key)) {
                this.stop = true;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}