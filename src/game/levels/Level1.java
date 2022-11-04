// 315318766 Omer Bar

package game.levels;

import biuoop.DrawSurface;
import collision.detection.Sprite;
import game.Block;
import game.Commons;
import game.LevelInformation;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import geometry.primitives.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class Level1 extends LevelBuilder implements LevelInformation {


    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Point start = new Point(400, 300);
        Velocity v = new Velocity(0, 8);
        v.setStartingPoint(start);
        v.setColor(Color.white);
        ArrayList<Velocity> lst = new ArrayList<>();
        lst.add(v);
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 14;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rbg = new Rectangle(new Point(0, 0), 800, 600);
        Block infoBlock = getFrame(new Point(0, 0), 800, 20, new Color(185, 185, 185));
        Block bbg = new Block(rbg, Color.black, null) {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(this.getColor());
                super.drawOn(d);
                infoBlock.drawOn(d);
                d.setColor(Color.blue);
                for (int i = 1; i < 4; i++) {
                    d.drawCircle(400, 160, 40 * i);
                }
                d.drawLine(260, 160, 370, 160);
                d.drawLine(430, 160, 540, 160);
                d.drawLine(400, 30, 400, 140);
                d.drawLine(400, 180, 400, 300);
            }
        };
        return bbg;
    }

    @Override
    public List<Block> blocks() {
        //info block:
        // game frame:
        Block topFrame = getFrame(new Point(0, 20), 800, 25, Color.GRAY);
        topFrame.setBlockID(Commons.BlockKIND.FRAME_BLOCK);
        Block leftFrame = getFrame(new Point(0, 25), 20, 600, Color.GRAY);
        leftFrame.setBlockID(Commons.BlockKIND.FRAME_BLOCK);
        Block deathRegion = getFrame(new Point(0, 600), 800, 25, Color.black);
        deathRegion.setBlockID(Commons.BlockKIND.BALL_DEATH_BLOCK);
        Block rightFrame = getFrame(new Point(780, 25), 20, 600, Color.GRAY);
        rightFrame.setBlockID(Commons.BlockKIND.FRAME_BLOCK);
        // game level blocks
        Rectangle recBlock = new Rectangle(new Point(380, 150), 40, 20);
        Block b = new Block(recBlock, Color.red, null);
        ArrayList<Block> lst = new ArrayList<>();
        lst.add(b);
        lst.add(deathRegion);
        lst.add(leftFrame);
        lst.add(rightFrame);
        lst.add(topFrame);
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
