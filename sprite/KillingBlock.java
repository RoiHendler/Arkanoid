package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */

import geometry.Point;
import listener.BallRemover;
import biuoop.DrawSurface;

/***
 * @ Killing Block class
 */
public class KillingBlock extends BorderBlock {
    /***
     * constructors for the KillingBlock class.
     * @param topLeft - top left point for making the block
     * @param bottomRight - bottom right point for making the block
     * @param ballRemover - the ball that make the remover
     */
    public KillingBlock(Point topLeft, Point bottomRight, BallRemover ballRemover) {
        super(topLeft, bottomRight);
        this.addHitListener(ballRemover);
    }

    /***
     * A function that aims to override the function from the inheritance action.
     * @param drawSurface - the surface that we draw on.
     */
    @Override
    public void drawOn(DrawSurface drawSurface) {
        //nothing
    }
}
