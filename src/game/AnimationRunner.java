package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * 205543317.
 */

public class AnimationRunner {
    private GUI gui; // A gui.
    private int framesPerSecond; // how many frames per second.
    private Sleeper sleeper = new biuoop.Sleeper(); // a sleeper.

    /**
     * Initialize a AnimationRunner.
     *
     * @param gui             a gui.
     * @param framesPerSecond how many frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * @return the current gui.
     */
    public GUI getGui() {
        return this.gui;
    }



    /**
     * runs the animation.
     *
     * @param animation the given animation which is needed to run.
     */
    public void run(Animation animation) {
        framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}