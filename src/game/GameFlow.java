package game;

import hit.Counter;
import levels.EndScreen;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * 205543317.
 */

public class GameFlow {
    private KeyboardSensor keyboardSensor; // A keyboard sensor.
    private AnimationRunner animationRunner; // An animation runner.
    private Counter scoreCounter; // A score counter.
    private boolean winner; // Whether the player wins or lose.

    /**
     * Initialize a Gameflow.
     *
     * @param ar a animation runner.
     * @param ks a keyboard sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.scoreCounter = new Counter();
        this.winner = false;
    }

    /**
     * runs the levels.
     *
     * @param levels the given levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        GameLevel level = null;
        for (LevelInformation levelInfo : levels) {
            level = new GameLevel(this.keyboardSensor, levelInfo, this.scoreCounter, this.animationRunner);
            level.initialize();
            while (level.getBlockCounter().getValue() > 0 && level.getBallCounter().getValue() > 0) {
                level.run();
            }
            if (level.getBallCounter().getValue() == 0) {
                break;
            }
        }
        if (level.getBallCounter().getValue() > 0) { // game ended with a win or a lose.
            this.winner = true;
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor,
                KeyboardSensor.SPACE_KEY, new EndScreen(this.scoreCounter, this.winner)));
        this.animationRunner.getGui().close();
    }
}