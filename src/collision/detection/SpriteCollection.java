// 315318766 Omer Bar

package collision.detection;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public class SpriteCollection {

    private List<Sprite> lst;

    /**
     * collision.detection.SpriteCollection Constructor.
     */
    public SpriteCollection() {
        this.lst = new ArrayList<Sprite>();
    }

    /**
     * add the given sprite to the list.
     *
     * @param s - collision.detection.Sprite
     */
    public void addSprite(Sprite s) {
        if (this.lst != null) {
            this.lst.add(s);
        }
    }

    /**
     * notify all the game sprite that they need to updated.
     */
    public void notifyAllTimePassed() {
        List<Sprite> temp = new ArrayList<Sprite>(this.lst);
        for (Sprite s : temp) {
            s.timePassed();
        }
    }

    /**
     * draw all the collision.detection.Sprite in the collection to the given draw surface variable.
     *
     * @param d - DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> temp = new ArrayList<Sprite>(this.lst);
        for (Sprite s : temp) {
            s.drawOn(d);
        }
    }

    /**
     * removing the sprite object from the sprite list.
     *
     * @param s - Sprite
     */
    public void removeSprite(Sprite s) {
        if (s == null) {
            return;
        }
        this.lst.remove(s);
    }
}