package game;

import ball.Ball;
import block.Block;
import ball.BallRemover;
import block.BlockRemover;
import collidable.Collidable;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import ball.Velocity;
import sprite.Sprite;
import sprite.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import hit.Counter;


import java.awt.Color;
import java.util.Random;

/**
 * 205543317.
 */

public class GameLevel implements Animation {

    private SpriteCollection sprites; // all the sprites.
    private GameEnvironment environment; // game environment.
    private GUI gui; //gui.
    public static final int WIDTH = 800; // width of screen.
    public static final int HEIGHT = 600; //height of screen.
    public static final int BORDER_SIZE = 7; // size of borders.
    public static final int PADDLE_WIDTH = 135; //paddle width.
    public static final int PADDLE_HEIGHT = 15; //paddle height.
    public static final int BLOCK_HEIGHT = 25; //block height.
    public static final int BLOCK_WIDTH = 50; // block width.
    public static final int BALL_RADIUS = 5; //radius of ball.
    public static final int BLOCKS_IN_FIRST_ROW = 12; //radius of ball.
    public static final int SCORE_SIZE = 20; //size of score window above.
    // space above blocks calculated by start of block - number of block in first row.
    public static final int START_OF_BLOCKS = 16; // where the blocks beginning to appear.
    private Counter blockCounter; // Keeps count the blocks.
    private Counter ballCounter; // Keeps count the balls.
    private BlockRemover blockRemover; // keeps a block remover of any block so it can remove it when hit.
    private BallRemover ballRemover; // keeps a ball remover of any ball so it finish the game when no balls left.
    private ScoreTrackingListener scoreTracking; // Keeps track the score.
    private Counter scoreCounter; // Keeps count the score.
    private ScoreIndicator scoreIndicator; // a score indicator.
    private AnimationRunner runner; //animation runner.
    private boolean running; // is the game running.
    private biuoop.KeyboardSensor keyboard; //keyboard sensor.
    private LevelInformation levelInfo; // level information.

    /**
     * initialize a GameLevel.
     *
     * @param ks               a keyboard sensor.
     * @param levelInformation level information.
     * @param score            a counter of the score.
     * @param runner           animation runner.
     */
    public GameLevel(KeyboardSensor ks, LevelInformation levelInformation, Counter score, AnimationRunner runner) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = runner.getGui();
        this.blockCounter = new Counter();
        this.blockRemover = new BlockRemover(this, this.blockCounter);
        this.ballCounter = new Counter();
        this.ballRemover = new BallRemover(this, this.ballCounter);
        this.scoreCounter = score;
        this.scoreTracking = new ScoreTrackingListener(this.scoreCounter);
        this.scoreIndicator = new ScoreIndicator(this.scoreCounter);
        this.runner = runner;
        this.keyboard = ks;
        this.levelInfo = levelInformation;
    }

    /**
     * add a given collidable to the game environment.
     *
     * @param c a collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a given sprite to all sprites.
     *
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * creates a random color.
     *
     * @return random color.
     */
    public Color getRandomColor() {
        Random rand = new Random(); // create a random-number generator
        float r = rand.nextFloat(); // gets random float number.
        float g = rand.nextFloat(); // gets random float number.
        float b = rand.nextFloat(); // gets random float number.
        return new Color(r, g, b); // create a color within the 3 random color that represent RGB.
    }

    /**
     * creates the frames to the game to stop the ball from exiting the borders.
     */
    public void createFrames() {
        //creates a block for each border so it can be treated as a collidable.
        Rectangle leftFrame = new Rectangle(new Point(0, 0), BORDER_SIZE, HEIGHT);
        Rectangle rightFrame = new Rectangle(new Point(WIDTH - BORDER_SIZE, 0), BORDER_SIZE, HEIGHT);
        Rectangle topFrame = new Rectangle(new Point(0, SCORE_SIZE), WIDTH, BORDER_SIZE);
        new Block(leftFrame, Color.GRAY).addToGame(this);
        new Block(rightFrame, Color.GRAY).addToGame(this);
        new Block(topFrame, Color.GRAY).addToGame(this);
    }

    /**
     * @return the block counter.
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * @return the ball counter.
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    /**
     * Add blocks in a row.
     *
     * @param blocksInARow number of blocks in the current row.
     * @param color        a color for the block of this row.
     */
    public void createBlocks(int blocksInARow, java.awt.Color color) {
        for (int i = blocksInARow; i > 0; i--) {
            Rectangle rec = new Rectangle(new Point(WIDTH - i * BLOCK_WIDTH - BORDER_SIZE,
                    (START_OF_BLOCKS - blocksInARow) * BLOCK_HEIGHT), BLOCK_WIDTH, BLOCK_HEIGHT);
            Block block = new Block(rec, color);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracking);
            blockCounter.increase(1);
        }
    }

    /**
     * Remove a Collidable.Collidable.
     *
     * @param c a Collidable.Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove a Sprite.Sprite.
     *
     * @param s a Sprite.Sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getAllSprites().remove(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball.Ball and Game.Paddle.
     * and add them to the game.
     */
    public void initialize() {
        this.sprites.addSprite(this.levelInfo.getBackground());
        for (Block block : this.levelInfo.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracking);
        }
        blockCounter.increase(this.levelInfo.numberOfBlocksToRemove());

        Rectangle bottomFrame = new Rectangle(new Point(0, HEIGHT), WIDTH, BORDER_SIZE);
        Block bottomBlock = new Block(bottomFrame, Color.GRAY);
        bottomBlock.addToGame(this); // block under the screen that "kill" balls when hit.
        bottomBlock.addHitListener(ballRemover);
        Rectangle rec = new Rectangle(new Point(WIDTH / 2, HEIGHT / 2), 30, 30);
        Paddle paddle = new Paddle(this.gui, new Point((WIDTH - PADDLE_WIDTH) / 2,
                HEIGHT - BORDER_SIZE - PADDLE_HEIGHT), this.levelInfo.paddleWidth(),
                PADDLE_HEIGHT, this.levelInfo.paddleSpeed());
        paddle.addToGame(this);
        createFrames();
        for (Velocity velocity : this.levelInfo.initialBallVelocities()) {
            Ball ball = new Ball(WIDTH / 2, HEIGHT - 2 * PADDLE_HEIGHT, BALL_RADIUS, Color.BLACK);
            ball.setVelocity(velocity);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
        ballCounter.increase(this.levelInfo.numberOfBalls());
        scoreIndicator.addToGame(this);

    }

    /**
     * Run the game and start the animation loop.
     */
    public void run() {
        this.running = true;
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.setColor(Color.BLACK); //sets color to black.
        d.drawText(15, 18, this.levelInfo.levelName(), GameLevel.SCORE_SIZE);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    "space", new PauseScreen(this.keyboard)));
        }
        if (this.blockCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * @return whether the frame should be stopped from running or not.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}
