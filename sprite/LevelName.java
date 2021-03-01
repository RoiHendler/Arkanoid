package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 4
 * @ since: 11/06/2020
 */

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import lnterface.Sprite;

import java.awt.Color;

/***
 * @ the LevelName class
 */
public class LevelName implements Sprite {
    private static final Color NAMEINDICATORCOLOR = Color.LIGHT_GRAY;
    private static final String NAME = "Lavel Name: ";
    private String levelName;
    private Block block;

    /***
     * constructors for the LevelName class.
     * @param topLeft - top left point for making the block
     * @param bottomRight - bottom right point for making the block
     * @param levelName - the Level Name
     */
    public LevelName(Point topLeft, Point bottomRight, String levelName) {
        this.block = new Block(topLeft, bottomRight, NAMEINDICATORCOLOR);
        this.levelName = levelName;
    }

    /***
     * Displays the level name on the screen.
     * @param d - the draw surface
     */
    public void drawOn(DrawSurface d) {
        String scoreString = NAME + levelName;
        int x = (int) this.block.getUpperLeft().getX() + 5;
        int y = (int) this.block.getUpperLeft().getY() + 20;
        d.setColor(Color.BLACK);
        d.drawText(x, y, scoreString, 16);


    }

    /***
     * timePassed.
     */
    @Override
    public void timePassed() {
        // nothing
    }

    /***
     * add To Game.
     * @param game - the game that we wont to add
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /***
     * remove From Game.
     * @param game -  the game that we wont to remove
     */
    //@Override
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}
