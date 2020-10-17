package levels;

import ball.Velocity;
import block.Block;
import game.LevelInformation;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import sprite.Sprite;
import game.GameLevel;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * 205543317.
 */

public class LevelOne implements LevelInformation {

    private int numberOfBalls; // number of balls.
    private List<Velocity> initialBallVelocities; // list of all the ball's velocities by order.
    private int paddleSpeed; // speed of paddle.
    private int paddleWidth; // the width of the paddle.
    private String levelName; // level's name.
    private List<Block> blocks; // list of all the blocks.
    private int numberOfBlocksToRemove; // number of blocks should be remove to pass level.

    /**
     * Initialize level one.
     */
    public LevelOne() {
        this.numberOfBalls = 1;
        initialBallVelocities = new LinkedList<>();
        initialBallVelocities.add(new Velocity(0, 2));
        this.paddleSpeed = 7;
        this.paddleWidth = 120;
        this.levelName = "Level 1 - Good Morning!";
        blocks = new LinkedList<Block>();
        this.numberOfBlocksToRemove = 0;
        createBlocks();
    }

    /**
     * Creates the blocks suit level one.
     */
    private void createBlocks() {
        Rectangle rec = new Rectangle(new Point(
                GameLevel.WIDTH / 2 - 20,
                GameLevel.HEIGHT / 5),
                GameLevel.BLOCK_HEIGHT * 2, GameLevel.BLOCK_HEIGHT * 2);
        Block block = new Block(rec, Color.pink);
        blocks.add(block);
        this.numberOfBlocksToRemove++;
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundOne();
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }


}
