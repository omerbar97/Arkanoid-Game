// 315318766 Omer Bar

package game;

import biuoop.DrawSurface;
import collision.detection.Collidable;
import collision.detection.HitListener;
import collision.detection.HitNotifier;
import collision.detection.Sprite;
import geometry.primitives.Line;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import geometry.primitives.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 06-04-2022
 */
public class Block extends Commons implements Collidable, Sprite, HitNotifier {

    private final Rectangle block;
    private Color color;
    private BlockKIND blockID = BlockKIND.NORMAL_BLOCK; // default a normal block
    private List<HitListener> hitListeners;

    /**
     * game.Block constructor.
     *
     * @param block - geometry.primitives.Rectangle
     * @param c     - color
     * @param h     - List of HitListeners.
     */
    public Block(Rectangle block, Color c, List<HitListener> h) {
        this.block = block;
        this.color = c;
        this.hitListeners = h;
    }

    /**
     * getter for the ball color.
     *
     * @return Color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * getter for HitListeners list.
     *
     * @return - List
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * Setter for HitListeners.
     *
     * @param hitListeners - List
     */
    public void setHitListeners(List<HitListener> hitListeners) {
        this.hitListeners = hitListeners;
    }

    /**
     * Getter for BlockID.
     *
     * @return - BlockKIND
     */
    public BlockKIND getBlockID() {
        return blockID;
    }

    /**
     * Setter for BlockID.
     *
     * @param blockid - BlockKIND
     */
    public void setBlockID(BlockKIND blockid) {
        this.block.setRecID(blockid);
        this.blockID = blockid;
    }

    /**
     * setter for the color.
     *
     * @param c - Color
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * draw a given line of the block on the given draw surface.
     *
     * @param d - DrawSurface
     * @param l - geometry.primitives.Line
     */
    protected void drawLineOfBlock(DrawSurface d, Line l) {
        d.setColor(Color.black);
        int x1, y1, x2, y2;
        x1 = (int) l.start().getX();
        y1 = (int) l.start().getY();
        x2 = (int) l.end().getX();
        y2 = (int) l.end().getY();
        d.drawLine(x1, y1, x2, y2);
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        drawLineOfBlock(d, this.getCollisionRectangle().getUpperLine());
        drawLineOfBlock(d, this.getCollisionRectangle().getLowerLine());
        drawLineOfBlock(d, this.getCollisionRectangle().getLeftLine());
        drawLineOfBlock(d, this.getCollisionRectangle().getRightLine());
        d.setColor(Color.black);
    }

    /**
     * Empty for now.
     */
    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //the point is in the left line of the block.
        if (this.block.getLeftLine().isPointExist(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        //the point is in the right line of the block.
        if (this.block.getRightLine().isPointExist(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        //the point is in the upper line of the block.
        if (this.block.getUpperLine().isPointExist(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        //the point is the lower line of the block.
        if (this.block.getLowerLine().isPointExist(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (this.hitListeners != null) {
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        if (hitter == null) {
            System.out.println("null");
            return;
        }
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * method to remove a block from the game.
     *
     * @param game - Game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

}
