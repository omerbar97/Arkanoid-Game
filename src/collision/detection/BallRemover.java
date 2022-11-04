// 315318766 Omer Bar

package collision.detection;

import game.Ball;
import game.Block;
import game.Commons;
import game.Counter;
import game.GameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 10-05-2022
 */
public class BallRemover implements HitListener {

    private final GameLevel game;
    private final Counter remainingBalls;
    private List<Ball> removedBall;

    /**
     * constructor.
     *
     * @param game           - Game
     * @param remainingBalls - Counter
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
        this.game.addHitListener(this);
        this.removedBall = new ArrayList<>();
    }

    /**
     * Getter for game.
     * @return GameLevel
     */
    public GameLevel getGame() {
        return game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.removedBall.contains(hitter)) {
            return;
        }
        if (beingHit.getBlockID() == Commons.BlockKIND.BALL_DEATH_BLOCK) {
            hitter.removeFromGame(this.getGame());
            this.removedBall.add(hitter);
            this.remainingBalls.decrease(1);
        }
    }

    /**
     * function that check if the amount of ball is empty, return true if so.
     *
     * @return - boolean.
     */
    public boolean isBallEmpty() {
        return this.remainingBalls.getValue() <= 0;
    }

    /**
     * add an amount of given integers to the counter.
     *
     * @param i - int
     */
    public void addCounter(int i) {
        this.remainingBalls.increase(i);
    }
}
