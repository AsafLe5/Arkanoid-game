package game;

import sprite.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * 205543317.
 */


/**
 * A countdown for the animation.
 */
public class CountdownAnimation implements Animation {

    private double seconds; // number of seconds.
    private int startCountFrom; // which number to countdown from.
    private SpriteCollection screen; // the given screen.
    private final long createdMillis = System.currentTimeMillis(); // the current time if the system.

    /**
     * Initialize a CountdownAnimation.
     *
     * @param numOfSeconds number of seconds.
     * @param countFrom    which number to countdown from.
     * @param gameScreen   the given screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.startCountFrom = countFrom;
        this.screen = gameScreen;
    }

    /**
     * display the given gameScreen,
     * for numOfSeconds seconds, and on top of them it will show
     * a countdown from countFrom back to 1, where each number will
     * appear on the screen for (numOfSeconds / countFrom) seconds, before
     * it is replaced with the next one.
     *
     * @param surface a draw surface.
     */
    public void doOneFrame(DrawSurface surface) {
        long nowMillis;
        this.screen.drawAllOn(surface);
        surface.setColor(Color.lightGray); //sets color to light gray.
        surface.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.SCORE_SIZE);
        Sleeper sleeper = new Sleeper();
        surface.setColor(Color.BLUE);

        nowMillis = System.currentTimeMillis();
        double timePasses = ((double) (nowMillis - this.createdMillis) / 1000);

        if (timePasses >= 0 && timePasses <= (seconds / startCountFrom)) {
            surface.drawText(370, 18, String.valueOf(3), GameLevel.SCORE_SIZE);
        }
        nowMillis = System.currentTimeMillis();
        timePasses = ((double) (nowMillis - this.createdMillis) / 1000);
        if (timePasses >= seconds / startCountFrom && timePasses < 2 * (seconds / startCountFrom)) {
            surface.drawText(370, 18, String.valueOf(2), GameLevel.SCORE_SIZE);

        }
        nowMillis = System.currentTimeMillis();
        timePasses = ((double) (nowMillis - this.createdMillis) / 1000);
        if (timePasses >= 2 * (seconds / startCountFrom)) {
            surface.drawText(370, 18, String.valueOf(1), GameLevel.SCORE_SIZE);
        }
        sleeper.sleepFor((long) (seconds / startCountFrom));
    }

    /**
     * checks whether the frame should stop if its done with the current animation.
     *
     * @return true if the frame should stop or false if it should continue.
     */
    public boolean shouldStop() {
        long nowMillis = System.currentTimeMillis();
        double timePasses = ((double) (nowMillis - this.createdMillis) / 1000);
        return (timePasses >= seconds);
    }
}