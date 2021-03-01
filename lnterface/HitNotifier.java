/**
 * @ Roi Hendler <roihendler22@gmail.com>
 * @ version: 3
 * @ since: 24/05/2020
 */
package lnterface;
/***
 * interface for Hit Notifier.
 */
public interface HitNotifier {

    /***
     * add Hit Listener
     * Add hl as a listener to hit events.
     * @param hl - the Hit Listener
     */
    void addHitListener(HitListener hl);

    /***
     * Remove hl from the list of listeners to hit events.
     * @param hl - Hit Listener
     */
    void removeHitListener(HitListener hl);
}
