// 315318766 Omer Bar

package game.levels;

import biuoop.DrawSurface;
import game.Block;
import game.Commons;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class LevelBuilder {

    /**
     * Setter for the game Frames.
     *
     * @param p      - geometry.primitives.Point
     * @param width  - int
     * @param height - int
     * @param color  - Color
     * @return - game.Block
     */
    protected Block getFrame(Point p, int width, int height, Color color) {
        Rectangle r = new Rectangle(p, width, height);
        return new Block(r, color, null) {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(color);
                d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                        (int) this.getCollisionRectangle().getUpperLeft().getY(),
                        (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
                //d.setColor(Color.black);
                //drawLineOfBlock(d, this.getCollisionRectangle().getLowerLine());
            }
        };
    }

    /**
     * returning line of block one near the other.
     * @param firstBlock - Point
     * @param width - int
     * @param height - int
     * @param c - color
     * @param intervals - int
     * @param wideScreen - int
     * @return List
     */
    protected List<Block> addLineOfBlocks(Point firstBlock, int width, int height, Color c,
                                          int intervals, int wideScreen) {
        int count = (int) (wideScreen / width);
        List<Block> lst = new ArrayList<>();
        int j = 0, intervalcounter = intervals;
        for (int i = 0; i < count; i++) {
            Rectangle rec = new Rectangle(firstBlock, width, height);
            if (i == intervalcounter) {
                intervalcounter += intervals;
                j++;
                if (j == 7) {
                    j--;
                }
            }
            Block b = new Block(rec, c, null);
            b.setBlockID(Commons.BlockKIND.NORMAL_BLOCK);
            firstBlock = new Point(firstBlock.getX() - width, firstBlock.getY());
            lst.add(b);
        }
        return lst;
    }

    protected List<Block> addSingleLine(int x, int y, int width, int height, Color color, int numberOfBlocks) {
        List<Block> lst = new ArrayList<>();
        for (int i = 0; i < numberOfBlocks; i++) {
            Rectangle rec = new Rectangle(new Point(x - (i * width), y), width, height);
            rec.setRecID(Commons.BlockKIND.NORMAL_BLOCK);
            Block b = new Block(rec, color, null);
            b.setBlockID(Commons.BlockKIND.NORMAL_BLOCK);
            lst.add(b);
        }
        return lst;
    }
}
