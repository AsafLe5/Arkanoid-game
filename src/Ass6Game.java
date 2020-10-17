import game.GameLevel;
import game.AnimationRunner;
import game.GameFlow;
import game.LevelInformation;
import levels.LevelFour;
import levels.LevelOne;
import levels.LevelThree;
import levels.LevelTwo;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.LinkedList;
import java.util.List;

/**
 * 205543317.
 */

public class Ass6Game {

    /**
     * starts the game.
     *
     * @param args gets nothing.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        List<LevelInformation> levels = new LinkedList<>();
        if (args.length == 0 || args[0].equals("${args}")) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        } else {
            for (String level : args) {
                if (level.equals("1")) {
                    levels.add(new LevelOne());
                }
                if (level.equals("2")) {
                    levels.add(new LevelTwo());
                }
                if (level.equals("3")) {
                    levels.add(new LevelThree());
                }
                if (level.equals("4")) {
                    levels.add(new LevelFour());
                }
            }
            if (levels.isEmpty()) {
                levels.add(new LevelOne());
                levels.add(new LevelTwo());
                levels.add(new LevelThree());
                levels.add(new LevelFour());
            }
        }
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor);
        gameFlow.runLevels(levels);
    }
}
