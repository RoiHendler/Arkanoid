package lnterface;
/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ ID: 208728337
 * @ version: 3
 * @ since: 24/05/2020
 */

import game.GameLevel;

/***
 * add object to the game - interface.
 */
public interface GameItem extends Sprite {
    /***
     * @param game - the game that we wont to add
     */
    void addToGame(GameLevel game);
    /***
     * remove From Game.
     * @param game -  the game that we wont to remove
     */
    void removeFromGame(GameLevel game);
}
