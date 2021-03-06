package game;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * 205543317.
 */

public class PauseScreen implements Animation {

    private KeyboardSensor keyboard; // A keyboard sensor.
    private boolean stop; // whether the pause should stop.

    /**
     * Initialize a PauseScreen.
     *
     * @param k a keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}