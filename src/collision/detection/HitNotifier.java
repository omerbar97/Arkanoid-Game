// 315318766 Omer Bar

package collision.detection;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 10-05-2022
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl - HitListener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - HitListener
     */
    void removeHitListener(HitListener hl);
}