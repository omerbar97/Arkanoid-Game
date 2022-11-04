// 315318766 Omer Bar

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.AnimationRunner;
import game.GameFlow;
import game.LevelInformation;
import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import game.levels.Level4;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public class Ass6Game {
    /**
     * main program.
     *
     * @param args - irrelevant for the program.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid Game", 800, 600);
        AnimationRunner gameAnimation = new AnimationRunner(gui, 60);
        Sleeper sleeper = new Sleeper();
        List<LevelInformation> levels = new ArrayList<>();
        // initializing the list from the user input:
        for (String s : args) {
            if (s.equals("1")) {
                LevelInformation lv1 = new Level1();
                levels.add(lv1);
            } else if (s.equals("2")) {
                LevelInformation lv2 = new Level2();
                levels.add(lv2);
            } else if (s.equals("3")) {
                LevelInformation lv3 = new Level3();
                levels.add(lv3);
            } else if (s.equals("4")) {
                LevelInformation lv4 = new Level4();
                levels.add(lv4);
            }
        }
        // in case no input was added then print to the gui INCORRECT INPUT.
        if (levels.size() == 0) {
            while (true) {
                DrawSurface d = gameAnimation.getGui().getDrawSurface();
                d.setColor(Color.black);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.white);
                d.drawText(300, 300, "Incorrect Input!", 30);
                gameAnimation.getGui().show(d);
                sleeper.sleepFor(30);
                if (gameAnimation.getGui().getKeyboardSensor().isPressed(KeyboardSensor.SPACE_KEY)) {
                    gameAnimation.getGui().close();
                }
            }
        } else {
            GameFlow game = new GameFlow(gameAnimation, gui.getKeyboardSensor());
            game.runLevels(levels);
        }
    }
}
