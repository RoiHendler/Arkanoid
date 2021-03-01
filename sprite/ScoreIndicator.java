package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 4
 * @ since: 11/06/2020
 */
import game.GameLevel;
import general.Axis;
import general.Direction;
import geometry.Point;
import lnterface.GameItem;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @ Score Indicator class
 **/
public class ScoreIndicator implements GameItem {
    private static final Color SCOREINDICATORCOLOR = Color.LIGHT_GRAY;
    private static final String SCORE = "score: ";

    private Block block;
    private Counter score;

    /***
     * constructors for the Score Indicator class.
     * @param topLeft - top left point for making the block
     * @param bottomRight - bottom right point for making the block
     * @param score - the score of the game
     */
    public ScoreIndicator(Point topLeft, Point bottomRight, Counter score) {
        this.block = new Block(topLeft, bottomRight, SCOREINDICATORCOLOR);
        this.score = score;
    }

    /***
     * draw On.
     * @param d - the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
        String scoreString = SCORE + score.getValue();
        int x = ((int) this.block.getEdge(Direction.TOP).middle().shiftPoint(-scoreString.length(), Axis.X).getX());
        int y = ((int) this.block.getEdge(Direction.RIGHT).middle().shiftPoint(3, Axis.Y).getY());
        d.setColor(Color.BLACK);
        d.drawText(x, y, scoreString, 16);

    }

    /***
     * timePassed - A function that aims to override the function from the inheritance action.
     */
    @Override
    public void timePassed() {
        // nothing
    }

    /***
     * add To Game.
     * @param game - the game that we wont to add
     */
    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /***
     * remove From Game.
     * @param game -  the game that we wont to remove
     */
    @Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
