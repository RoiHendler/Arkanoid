package sprite;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */

import lnterface.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Sprite.SpriteCollection class
 * containing:
 * List<Interface.Sprite> spriteList -  List of all the Interface.Sprite collection in the game
 **/
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * @ constructors for the Interface.Sprite collection class
     **/
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /***
     * @param s - the sprite that we want to add (to the list)
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /***
     public void addSprite(List<Sprite> spriteList1) {
     this.spriteList.addAll(spriteList1);
     }
     public void addSprite(List<Block> blockList, boolean t) {
     for (Block block : blockList) {
     this.spriteList.add(block);
     }
     }
     **/

    /***
     * remove From Game.
     * @param s -  the sprite that we want to remove
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /***
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> tempSpriteList = new ArrayList<>(spriteList);
        for (Sprite sprite : tempSpriteList) {
            sprite.timePassed();
        }
    }

    /***
     * call drawOn(d) on all sprites.
     * @param d - the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }

    /***
     * @param o - Object of the spriteList
     * @return true or false
     */
    public boolean contains(Object o) {
        return spriteList.contains(o);
    }
}