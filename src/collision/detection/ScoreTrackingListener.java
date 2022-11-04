// 315318766 Omer Bar

package collision.detection;

import game.Ball;
import game.Block;
import game.Commons;
import game.Counter;
import game.GameLevel;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 10-05-2022
 */
public class ScoreTrackingListener implements HitListener {

    private final GameLevel game;
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter - Counter
     * @param game         - Game
     */
    public ScoreTrackingListener(Counter scoreCounter, GameLevel game) {
        this.currentScore = scoreCounter;
        this.game = game;
        this.game.addHitListener(this);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.game.getBlockRemover().isCounterEmpty()) {
            this.currentScore.increase(100);
        }
        if (beingHit.getBlockID() != Commons.BlockKIND.BALL_DEATH_BLOCK && beingHit.getBlockID()
                != Commons.BlockKIND.BALL_BLOCK && beingHit.getBlockID() != Commons.BlockKIND.FRAME_BLOCK) {
            this.currentScore.increase(5);
        }
    }
}