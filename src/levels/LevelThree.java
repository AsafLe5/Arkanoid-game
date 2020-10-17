package levels;

import ball.Velocity;
import block.Block;
import game.GameLevel;
import game.LevelInformation;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import sprite.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * 205543317.
 */

public class LevelThree implements LevelInformation {

    private int numberOfBalls; // number of balls.
    private List<Velocity> initialBallVelocities; // list of all the ball's velocities by order.
    private int paddleSpeed; // speed of paddle.
    private int paddleWidth; // the width of the paddle.
    private String levelName; // level's name.
    private List<Block> blocks; // list of all the blocks.
    private int numberOfBlocksToRemove; // number of blocks should be remove to pass level.

    /**
     * Initialize level three.
     */
    public LevelThree() {
        this.numberOfBalls = 5;
        initialBallVelocities = new LinkedList<>();
        initialBallVelocities.add(new Velocity(3, 5));
        initialBallVelocities.add(new Velocity(-3, 5));
        initialBallVelocities.add(new Velocity(-5, 3));
        initialBallVelocities.add(new Velocity(8, 5));
        initialBallVelocities.add(new Velocity(-8, 5));
        this.paddleSpeed = 7;
        this.paddleWidth = 120;
        this.levelName = "Level 3 - Warming...";
        //this.getBackground = createBackRound();
        blocks = new LinkedList<Block>();
        this.numberOfBlocksToRemove = 0;
        createBlocks();
    }

    /**
     * Creates the blocks suit level three.
     */
    private void createBlocks() {
        for (int j = 18; j > 0; j -= 2) {
            for (int i = j; i > 0; i--) {
                //creates rectangle at the position due to i order.
                Rectangle rec = new Rectangle(new Point(
                        GameLevel.WIDTH - i * (GameLevel.BLOCK_WIDTH - 10)
                                - GameLevel.BORDER_SIZE - 110 - ((7 - j / 2) * (GameLevel.BLOCK_WIDTH - 10)),
                        (GameLevel.START_OF_BLOCKS - (14 - j / 2)) * GameLevel.BLOCK_HEIGHT),
                        GameLevel.BLOCK_WIDTH - 10, GameLevel.BLOCK_HEIGHT);
                Block block = new Block(rec, new Color((int) (255 * 0.05 * j), 20, 20));
                blocks.add(block);
                this.numberOfBlocksToRemove++;
            }
        }
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
        return new BackgroundThree();
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
