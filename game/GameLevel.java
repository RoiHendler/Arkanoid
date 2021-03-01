package game;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 4
 * @ since: 11/06/2020
 */

import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.KeyboardSensor;
import general.Axis;
import general.Direction;
import geometry.Point;
import listener.BallRemover;
import listener.BlockRemover;
import sprite.LevelName;
import sprite.ScoreTrackingListener;
import lnterface.Animation;
import lnterface.LevelInformation;
import sprite.Block;
import sprite.Ball;
import sprite.BorderBlock;
import sprite.KillingBlock;
import sprite.Paddle;
import sprite.ScoreIndicator;
import sprite.Velocity;
import sprite.SpriteCollection;
import sprite.Counter;
import lnterface.Collidable;
import lnterface.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * @ class Game
 **/
public class GameLevel implements Animation {
    public static final int BALL_SIZE = 5;
    //size of the Border blocks
    public static final int BORDER_WIDTH = 25;
    public static final int BORDER_HEIGHT = 20;
    private Point topLeft, bottomRight;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksCounter;
    private Counter ballCounter;
    private Counter pointsCounter;
    private AnimationRunner runner;
    private LevelInformation levelInformation;
    private KeyboardSensor keyboardSensor;
    private String levelName;

    /**
     * @param topLeft          - top left point of the game screen
     * @param bottomRight      - bottom right point of the game screen
     * @param levelInformation - all the information for the current level
     * @param ar               - the AnimationRunner
     * @param ks               - the KeyboardSensor
     * @param points           - the current score
     * @ constructors for the GameLeval class
     */
    public GameLevel(Point topLeft, Point bottomRight,
                     LevelInformation levelInformation, AnimationRunner ar, KeyboardSensor ks, Counter points) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = ar;
        this.levelInformation = levelInformation;
        this.keyboardSensor = ks;
        this.ballCounter = new Counter(levelInformation.numberOfBalls());
        this.pointsCounter = points;
        this.levelName = levelInformation.levelName();
    }


    //------------------------------Get Functions-------------------------------------------------

    /**
     * @return width - return the width of the game screen
     * @ Get functions
     */
    public int getWidth() {
        return ((int) Math.abs(this.topLeft.getX() - this.bottomRight.getX()));
    }

    /**
     * @return width - return the height of the game screen
     * @ Get functions
     */
    public int getHeight() {
        return ((int) Math.abs(this.topLeft.getY() - this.bottomRight.getY()));
    }

    /**
     * @param collidable - add the collidable to the environment of the game
     * @ add functions
     */
    public void addCollidable(Collidable collidable) {
        this.environment.addCollidable(collidable);
    }

    /**
     * @param sprite - add the sprite to the sprites list
     * @ add functions
     */
    public void addSprite(Sprite sprite) {
        this.sprites.addSprite(sprite);
    }

    /**
     * @ intialize the game
     */
    public void intialize() {

        Block background = new Block(new Point(0, 0), this.getWidth(), this.getHeight(),
                levelInformation.getBackgroundColor());
        this.addSprite(background);
        this.insertBorders();
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Point(0, 0), new Point(this.getWidth(), 30),
                this.pointsCounter);
        scoreIndicator.addToGame(this);
        LevelName levelName1 = new LevelName(new Point(550, 0), new Point(this.getWidth(), 30), this.levelName);
        levelName1.addToGame(this);
        //Creates the barriers (blocks) in the game
        this.blocksCounter = this.makeBlocks();
        Point topLeftPoint = new Point(getWidth() / 2 - this.levelInformation.paddleWidth() / 2,
                getHeight() - 2 * BORDER_HEIGHT);
        //paddleBoard settings
        Block paddleBoard = new Block(topLeftPoint, this.levelInformation.paddleWidth(), 20,
                this.levelInformation.paddleColor());
        biuoop.KeyboardSensor keyboard = this.runner.getKeyBoardSensor();
        Paddle paddle = new Paddle(keyboard, paddleBoard, this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
        this.createBalls(paddle);
    }

    /***
     * create Balls for the game.
     * @param paddle - the paddle in the game
     */
    private void createBalls(Paddle paddle) {
        List<Velocity> velocitiesList = levelInformation.initialBallVelocities();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Point p = paddle.getEdge(Direction.TOP).middle().shiftPoint(-1.1 * BALL_SIZE, Axis.Y);
            Ball ball = new Ball(p, 5, Color.white);
            ball.setVelocity(velocitiesList.get(i));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
        }
    }

    /***
     * give Env To Ball.
     * @param ball - the ball that we give env
     */
    public void giveEnvToBall(Ball ball) {
        if (this.sprites.contains(ball)) {
            ball.setGameEnvironment(this.environment);
        }
    }

    /**
     * Creates the barriers (block) in the game.
     *
     * @return Counter of the blocks num
     */
    private Counter makeBlocks() {
        List<Block> blockList = levelInformation.blocks();
        Counter counter = new Counter(blockList.size());
        BlockRemover blockRemover = new BlockRemover(this, counter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.pointsCounter);
        for (Block block : blockList) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(this);
        }
        return counter;
    }

    /**
     * Creates the borders of the game.
     */
    private void insertBorders() {
        Point topLeftPoint = this.topLeft.shiftPoint(30, Axis.Y);
        Point topRight = new Point(this.bottomRight.getX(), topLeft.getY());
        Point bottomLeft = new Point(this.topLeft.getX(), this.bottomRight.getY());
        this.ballCounter = new Counter(levelInformation.numberOfBalls());

        List<Block> blocks = new ArrayList<>();
        //left
        blocks.add(new BorderBlock(topLeftPoint, bottomLeft.shiftPoint(BORDER_WIDTH, Axis.X)));
        //top
        blocks.add(new BorderBlock(topLeftPoint, topRight.shiftPoint(BORDER_HEIGHT + 30, Axis.Y)));
        //right
        blocks.add(new BorderBlock(topRight.shiftPoint(-BORDER_WIDTH, Axis.X), this.bottomRight));
        //bottom
        blocks.add(new KillingBlock(bottomLeft.shiftPoint(-BORDER_HEIGHT, Axis.Y), this.bottomRight,
                new BallRemover(this, this.ballCounter)));
        for (Block block : blocks) {
            this.addSprite(block);
            this.addCollidable(block);
        }
    }

    /***
     * This function run the current leval.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(3, this.sprites));
        this.runner.run(this);
    }

    /***
     * is Game Playing?
     * @return true - if the Game Playing or false if not
     */
    @Override
    public boolean shouldStop() {
        boolean b = this.blocksCounter.getValue() == 0 || this.ballCounter.getValue() == 0;
        return b;
    }


    /***
     * remove Collidable.
     * @param c - remove collidable from the environment
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /***
     * remove Sprite.
     * @param s -  remove sprites from the environment
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.runner.getKeyBoardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboardSensor, keyboardSensor.SPACE_KEY,
                    new PauseScreen(this.runner.getKeyBoardSensor())));
        }
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
    }

    /***
     * Get Function.
     * @return the amount of blocks in the game
     */
    public Counter getBlocksCounter() {
        return blocksCounter;
    }

    /***
     * Get Function.
     * @return the amount of ball in the game
     */
    public Counter getballCounter() {
        return ballCounter;
    }
}

