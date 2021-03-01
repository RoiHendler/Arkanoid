package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */

import game.GameLevel;
import geometry.Point;
import java.awt.Color;

/**
 * @ Border Block class
 **/
public class BorderBlock extends Block {
    private static final Color BORDERBLOCKCOLOR = Color.GRAY;

    /***
     * constructors for the Block Remover class.
     * @param topLeft - top left point for making the block
     * @param bottomRight - bottom right point for making the block
     */
    public BorderBlock(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight, BORDERBLOCKCOLOR);
    }

    /***
     * A function that aims to override the function from the inheritance action.
     * @param game - Our game area
     */
    @Override
    public void removeFromGame(GameLevel game) {
        //nothing
    }

}
