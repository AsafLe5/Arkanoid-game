package game;

import ball.Velocity;
import block.Block;
import sprite.Sprite;

import java.util.List;

/**
 * 205543317.
 */

public interface LevelInformation {

    /**
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return a list of velocities of each ball by order.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed
     * before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}