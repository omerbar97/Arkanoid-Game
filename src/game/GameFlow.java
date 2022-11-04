// 315318766 Omer Bar

package game;

import biuoop.KeyboardSensor;
import collision.detection.Lives;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor ks;

    /**
     * Constructor for this object.
     * @param ar - AnimationRunner
     * @param ks - KeyboardSensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;

    }

    /**
     * running the list of level that the user choice.
     * @param levels - List
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter(0);
        int liveCheck;
        Lives live = new Lives(new Counter(5));
        for (int i = 0; i < levels.size(); i++) {
            GameLevel level = new GameLevel(this.ar, levels.get(i), score, live);
            do {
                liveCheck = live.getLive().getValue();
                level.run();
                while (live.getLive().getValue() != liveCheck && !live.isDead()) {
                    // it means there was one fail we need to start again the level;
                    liveCheck = live.getLive().getValue();
                    GameLevel failedLevel = new GameLevel(this.ar, levels.get(i), score, live);
                    failedLevel.run();
                }
                // last stage winner
                if (i == levels.size() - 1 && level.getBlockRemover().isCounterEmpty()) {
                    YouWin screen = new YouWin(this.ar, score.getValue());
                    this.ar.run(screen);
                    this.ar.getGui().close();
                    return;
                    // need to display the winner screen:
                }
            } while (level.isRunning() && level.getBlockRemover().isCounterEmpty());
            if (level.getBallRemover().isBallEmpty() && live.isDead()) {
                // loser screen:
                YouLose screen = new YouLose(this.ar, score.getValue());
                this.ar.run(screen);
                this.ar.getGui().close();
            }
        }
    }
}
