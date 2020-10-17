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

public class LevelFour implements LevelInformation {

    private int numberOfBalls; // number of balls.
    private List<Velocity> initialBallVelocities; // list of all the ball's velocities by order.
    private int paddleSpeed; // speed of paddle.
    private int paddleWidth; // the width of the paddle.
    private String levelName; // level's name.
    private List<Block> blocks; // list of all the blocks.
    private int numberOfBlocksToRemove; // number of blocks should be remove to pass level.

    /**
     * Initialize level four.
     */
    public LevelFour() {
        initialBallVelocities = new LinkedList<>();
        for (int i = 3; i < 24; i++) {
            initialBallVelocities.add(new Velocity(i, 25 - i));
            this.numberOfBalls++;
            initialBallVelocities.add(new Velocity(-i, 25 - i));
            this.numberOfBalls++;
        }
        this.paddleSpeed = 7;
        this.paddleWidth = 400;
        this.levelName = "Level 4 - Stairway To Hell";
        blocks = new LinkedList<Block>();
        this.numberOfBlocksToRemove = 0;
        createBlocks();
    }

    /**
     * Creates the blocks suit level four.
     */
    private void createBlocks() {
        for (int j = 3; j < 15; j++) {
            for (int i = 38; i > 0; i--) {
                //creates rectangle at the position due to i order.
                Rectangle rec = new Rectangle(new Point(
                        GameLevel.WIDTH - 30 - i * (GameLevel.BLOCK_WIDTH - 30) + 18 - GameLevel.BORDER_SIZE,
                        (GameLevel.START_OF_BLOCKS - j) * (GameLevel.BLOCK_HEIGHT - 3)),
                        GameLevel.BLOCK_WIDTH - 30, GameLevel.BLOCK_HEIGHT - 3);
                if (i == 37 || i == 29 || ((i > 29 && i < 37) && j == 9) //Drawing H.
                        || (i == 26 || ((i < 26 && i > 18) && (j == 9 || j == 14 || j == 3))) //Drawing E.
                        || (i == 16 || ((i < 16 && i > 9) && (j == 3))) //Drawing L.
                        || (i == 8 || ((i < 8 && i > 1) && (j == 3)))) { //Drawing second L.
                    Block block = new Block(rec, Color.red);
                    blocks.add(block);
                } else {
                    Block block = new Block(rec, new Color((int) (255 * 0.05 * (15 - j)), 50, 70));
                    blocks.add(block);
                }
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
        return new BackgroundFour();
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
