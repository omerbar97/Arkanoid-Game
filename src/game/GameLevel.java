// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;
import collision.detection.BallRemover;
import collision.detection.BlockRemover;
import collision.detection.Collidable;
import collision.detection.HitListener;
import collision.detection.LevelNameIndicator;
import collision.detection.Lives;
import collision.detection.ScoreIndicator;
import collision.detection.ScoreTrackingListener;
import collision.detection.Sprite;
import collision.detection.SpriteCollection;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import geometry.primitives.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public class GameLevel implements Animation {

    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final List<HitListener> hitListeners;
    private Paddle paddle;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter numOfBlock;
    private AnimationRunner runner;
    private boolean running;
    private Counter score;
    private LevelInformation level;
    private List<Velocity> ballsVelocity;
    private Lives lives;

    /**
     * Constructor.
     * @param game - AnimationRunner
     * @param level - LevelInformation
     * @param counter - Counter
     * @param lives - Lives
     */
    public GameLevel(AnimationRunner game, LevelInformation level, Counter counter, Lives lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.hitListeners = new ArrayList<HitListener>();
        this.runner = game;
        this.level = level;
        this.score = counter;
        this.numOfBlock = new Counter(this.level.numberOfBlocksToRemove());
        this.ballRemover = new BallRemover(this, new Counter(this.getLevel().numberOfBalls()));
        this.blockRemover = new BlockRemover(this, new Counter(this.getLevel().numberOfBlocksToRemove()));
        this.ballsVelocity = this.getLevel().initialBallVelocities();
        this.lives = lives;
    }

    /**
     * getter for game Environment.
     *
     * @return - GameEnvironment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * add the given collidable to the game.Game Environment.
     *
     * @param c - collision.detection.Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Getter for Runner.
     * @return - AnimationRunner
     */
    public AnimationRunner getRunner() {
        return runner;
    }

    /**
     * Setter for Paddle.
     * @param paddle - Paddle
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * Adder for hit listener.
     *
     * @param l1 - HitListener.
     */
    public void addHitListener(HitListener l1) {
        this.hitListeners.add(l1);
    }

    /**
     * add the given collision.detection.Sprite to the collision.detection.Sprite Collection.
     *
     * @param s - collision.detection.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * getter for score.
     *
     * @return - Counter
     */
    public Counter getScore() {
        return this.score;
    }


    /**
     * method that remove a Collidable object from the game environment collection.
     *
     * @param c - Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * method that remove a sprite object from the game sprite collection.
     *
     * @param s - Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Getter for BallsVelocity.
     * @return - List
     */
    public List<Velocity> getBallsVelocity() {
        return ballsVelocity;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // game-specific logic
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // pause screen:
        if (this.getRunner().getGui().getKeyboardSensor().isPressed("p")) {
            PauseScreen screen = new PauseScreen(this.getRunner().getGui().getKeyboardSensor(),
                    this.sprites, this);
            this.getRunner().run(screen);
        }
        // stopping condition
        if (this.ballRemover.isBallEmpty()) {
            if (this.lives.isDead()) {
                this.running = false;
                return;
            }
            this.lives.decreaseLive(1);
            this.running = false;
            return;
        }
        if (this.blockRemover.isCounterEmpty()) {
            this.running = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Initialize a new game: create the Blocks and game.Ball (and game.Paddle) and add them to the game.
     */
    public void initialize() {
        // initialized the block list;
        Sprite bg = this.getLevel().getBackground();
        bg.addToGame(this);
        this.lives.addToGame(this);
        for (Block b : this.getLevel().blocks()) {
            b.setHitListeners(this.getHitListeners());
            b.addToGame(this);
        }
        // paddle creation:
        Rectangle paddle = new Rectangle(new Point((int) (400 - this.getLevel().paddleWidth() / 2), 560),
                this.getLevel().paddleWidth(), 20);
        Paddle p = new Paddle(this.runner.getGui().getKeyboardSensor(), paddle, this.getLevel().paddleSpeed());
        p.addToGame(this);
        this.paddle = p;
        this.environment.setPaddle(this.paddle);
        // ball creation:
        for (Velocity v : this.ballsVelocity) {
            if (v.getStartingPoint() != null) {
                Ball b = new Ball(v.getStartingPoint(), 8, v.getColor(), this.getHitListeners());
                b.setVelocity(v);
                b.setGame(this.environment);
                b.addToGame(this);
            }
        }
        // score and level name creation:
        ScoreIndicator s = new ScoreIndicator(this);
        ScoreTrackingListener scroe = new ScoreTrackingListener(this.getScore(), this);
        s.addToGame(this);
        LevelNameIndicator name = new LevelNameIndicator(this);
        name.addToGame(this);

    }

    /**
     * Getter for level.
     * @return - LevelInformation
     */
    public LevelInformation getLevel() {
        return level;
    }

    /**
     * getter for the hitlistener list.
     *
     * @return - List of HitListener objects.
     */
    public List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * getter for the BallRemover object.
     *
     * @return - BallRemover.
     */
    public BallRemover getBallRemover() {
        return this.ballRemover;
    }

    /**
     * Getter for Sprites.
     * @return - SpriteCollection
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * Getter for BlockRemover.
     * @return - BlockRemover
     */
    public BlockRemover getBlockRemover() {
        return blockRemover;
    }

    /**
     * Getter for running.
     * @return - boolean.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Getter for Paddle.
     * @return - Paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * run command for the game -- start the animation loop.
     */
    public void run() {
        this.initialize();
        this.running = true;
        this.environment.setGUI(this.runner.getGui());
        CountdownAnimation counter = new CountdownAnimation(2, 3, this.getSprites(), this.ballsVelocity.get(0));
        counter.setRunner(new AnimationRunner(this.runner.getGui(), 60));
        counter.getRunner().run(counter);
        this.runner.run(this);
    }
}
