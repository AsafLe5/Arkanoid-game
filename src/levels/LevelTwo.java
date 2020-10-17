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

public class LevelTwo implements LevelInformation {

    private int numberOfBalls; // number of balls.
    private List<Velocity> initialBallVelocities; // list of all the ball's velocities by order.
    private int paddleSpeed; // speed of paddle.
    private int paddleWidth; // the width of the paddle.
    private String levelName; // level's name.
    private List<Block> blocks; // list of all the blocks.
    private int numberOfBlocksToRemove; // number of blocks should be remove to pass level.

    /**
     * Initialize level two.
     */
    public LevelTwo() {
        this.numberOfBalls = 3;
        initBallsVel();
        this.paddleSpeed = 8;
        this.paddleWidth = 100;
        this.levelName = "Level 2 - Into The Wild";
        blocks = new LinkedList<Block>();
        this.numberOfBlocksToRemove = 0;
        createBlocks();
    }

    /**
     * Creates the blocks suit level two.
     */
    private void createBlocks() {

        for (int i = 15; i > 0; i--) {
            //creates rectangle at the position due to i order.
            Rectangle rec = new Rectangle(new Point(
                    GameLevel.WIDTH - i * GameLevel.BLOCK_WIDTH - GameLevel.BORDER_SIZE * 3.5,
                    (GameLevel.START_OF_BLOCKS - 6) * GameLevel.BLOCK_HEIGHT),
                    GameLevel.BLOCK_WIDTH, GameLevel.BLOCK_HEIGHT);
            Block block = new Block(rec, new Color(50, 255, 50));
            blocks.add(block);
            this.numberOfBlocksToRemove++;
        }
        for (int i = 15; i > 0; i--) {
            //creates rectangle at the position due to i order.
            Rectangle rec = new Rectangle(new Point(
                    GameLevel.WIDTH - i * GameLevel.BLOCK_WIDTH - GameLevel.BORDER_SIZE * 3.5,
                    (GameLevel.START_OF_BLOCKS - 8) * GameLevel.BLOCK_HEIGHT),
                    GameLevel.BLOCK_WIDTH, GameLevel.BLOCK_HEIGHT);
            Block block = new Block(rec, new Color(50, 190, 50));
            ;
            blocks.add(block);
            this.numberOfBlocksToRemove++;
        }
        for (int i = 15; i > 0; i--) {
            //creates rectangle at the position due to i order.
            Rectangle rec = new Rectangle(new Point(
                    GameLevel.WIDTH - i * GameLevel.BLOCK_WIDTH - GameLevel.BORDER_SIZE * 3.5,
                    (GameLevel.START_OF_BLOCKS - 10) * GameLevel.BLOCK_HEIGHT),
                    GameLevel.BLOCK_WIDTH, GameLevel.BLOCK_HEIGHT);
            Block block = new Block(rec, new Color(50, 160, 50));
            ;
            blocks.add(block);
            this.numberOfBlocksToRemove++;
        }
        for (int i = 15; i > 0; i--) {
            //creates rectangle at the position due to i order.
            Rectangle rec = new Rectangle(new Point(
                    GameLevel.WIDTH - i * GameLevel.BLOCK_WIDTH - GameLevel.BORDER_SIZE * 3.5,
                    (GameLevel.START_OF_BLOCKS - 12) * GameLevel.BLOCK_HEIGHT),
                    GameLevel.BLOCK_WIDTH, GameLevel.BLOCK_HEIGHT);
            Block block = new Block(rec, new Color(50, 130, 50));
            ;
            blocks.add(block);
            this.numberOfBlocksToRemove++;
        }
        for (int i = 15; i > 0; i--) {
            //creates rectangle at the position due to i order.
            Rectangle rec = new Rectangle(new Point(
                    GameLevel.WIDTH - i * GameLevel.BLOCK_WIDTH - GameLevel.BORDER_SIZE * 3.5,
                    (GameLevel.START_OF_BLOCKS - 14) * GameLevel.BLOCK_HEIGHT),
                    GameLevel.BLOCK_WIDTH, GameLevel.BLOCK_HEIGHT);
            Block block = new Block(rec, new Color(50, 100, 50));
            ;
            blocks.add(block);
            this.numberOfBlocksToRemove++;
        }

    }

    /**
     * Initialize the ball's velocity.
     */
    private void initBallsVel() {
        initialBallVelocities = new LinkedList<>();
        initialBallVelocities.add(new Velocity(3, 5));
        initialBallVelocities.add(new Velocity(-5, 3));
        initialBallVelocities.add(new Velocity(8, 3));
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
        return new BackgroundTwo();
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
