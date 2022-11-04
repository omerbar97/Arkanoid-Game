// 315318766 Omer Bar

package collision.detection;

import game.Ball;
import game.Block;
import game.Commons;
import game.Counter;
import game.GameLevel;
import game.Paddle;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import geometry.primitives.Velocity;

import java.awt.Color;
import java.util.Random;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 10-05-2022
 */
public class BlockRemover extends Commons implements HitListener {

    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param game          - Game
     * @param removedBlocks - Counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
        this.game.addHitListener(this);
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (hitter == null) {
            System.out.println("null");
            return;
        }
        Random rnd = new Random();
        // checking which kind of block has being hit.
        if (beingHit.getBlockID() == BlockKIND.BALL_BLOCK) {
            // this is a special block when it is hit the block is destroyed and a new ball is created in the game.
            Ball b = new Ball(new Point(beingHit.getCollisionRectangle().getUpperLeft().getX(),
                    beingHit.getCollisionRectangle().getUpperLeft().getY()), BALL_RADIOS,
                    this.game.getBallsVelocity().get(0).getColor(), this.game.getHitListeners());
            b.setVelocity(Velocity.fromAngleAndSpeed(rnd.nextInt(60) + 1, BALL_SPEED));
            b.setGame(this.game.getEnvironment());
            b.addToGame(this.game);
            this.game.getBallRemover().addCounter(1);
            beingHit.removeFromGame(this.game);
            // in this case we don't need to lower the counter because this block is extra, you don't need to destroy
            // it to win the game.
        } else if (beingHit.getBlockID() == BlockKIND.NORMAL_BLOCK) {
            // when the ball hit the normal block just destroy the block and reduce the counter with 1.
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        } else if (beingHit.getBlockID() == BlockKIND.BIGGER_PADDLE_BLOCK) {
            // in this case when the ball hit this special block then the paddle will be bigger
            Point start = this.game.getPaddle().getCollisionRectangle().getUpperLeft();
            int width = (int) this.game.getPaddle().getCollisionRectangle().getWidth();
            width *= 2;
            int height = (int) this.game.getPaddle().getCollisionRectangle().getHeight();
            Rectangle rec = new Rectangle(start, width, height);
            rec.setRecID(BlockKIND.BIGPADDLE);
            Paddle newPaddle = new Paddle(this.game.getRunner().getGui().getKeyboardSensor(),
                    rec, this.game.getPaddle().getPaddleMovement());
            this.game.getPaddle().removeFromGame(this.game);
            newPaddle.setPaddleID(BlockKIND.BIGPADDLE);
            newPaddle.addToGame(this.game);
            this.game.setPaddle(newPaddle);
            beingHit.removeFromGame(this.game);
        } else if (beingHit.getBlockID() == BlockKIND.SMALLER_PADDLE_BLOCK) {
            // in this case when the ball hit this special block then the paddle will be smaller.
            Point start = this.game.getPaddle().getCollisionRectangle().getUpperLeft();
            int width = (int) this.game.getPaddle().getCollisionRectangle().getWidth();
            width /= 2;
            int height = (int) this.game.getPaddle().getCollisionRectangle().getHeight();
            Rectangle rec = new Rectangle(start, width, height);
            rec.setRecID(BlockKIND.SMALLPADDLE);
            Paddle newPaddle = new Paddle(this.game.getRunner().getGui().getKeyboardSensor(),
                    rec, this.game.getPaddle().getPaddleMovement());
            this.game.getPaddle().removeFromGame(this.game);
            newPaddle.setColor(Color.red);
            newPaddle.setPaddleID(BlockKIND.SMALLPADDLE);
            newPaddle.addToGame(this.game);
            this.game.setPaddle(newPaddle);
            beingHit.removeFromGame(this.game);
        }
    }

    /**
     * checking if the amount of blocks in the game is 0, if so return true.
     *
     * @return - boolean.
     */
    public boolean isCounterEmpty() {
        return this.remainingBlocks.getValue() <= 0;
    }
}