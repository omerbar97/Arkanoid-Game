// 315318766 Omer Bar

package game.levels;

import biuoop.DrawSurface;
import collision.detection.Sprite;
import game.Block;
import game.Commons;
import game.LevelInformation;
import geometry.primitives.Drop;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import geometry.primitives.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class Level2 extends LevelBuilder implements LevelInformation {


    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        Point[] starts = new Point[10];
        Velocity[] v = new Velocity[10];
        int locationX = 200, locationY = 500;
        for (int i = 0; i < starts.length / 2; i++) {
            starts[i] = new Point(locationX, locationY);
            starts[starts.length - i - 1] = new Point(locationX + 400 - 30 * i * 2, locationY);
            locationX += 30;
            locationY -= 30;
            v[i] = new Velocity(0, 8);
            v[i].setColor(Commons.LV2_BALL_COLOR);
            v[i].setStartingPoint(starts[i]);
            v[starts.length - 1 - i] = new Velocity(0, 8);
            v[starts.length - 1 - i].setColor(Commons.LV2_BALL_COLOR);
            v[starts.length - 1 - i].setStartingPoint(starts[starts.length - 1 - i]);
        }
        ArrayList<Velocity> lst = new ArrayList<>();
        lst.addAll(Arrays.asList(v));
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 14;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rbg = new geometry.primitives.Rectangle(new Point(0, 0), 800, 600);
        Block infoBlock = getFrame(new Point(0, 0), 800, 20, new Color(185, 185, 185));
        Random rnd = new Random();
        Block bbg = new Block(rbg, Commons.LV2_BG_COLOR, null) {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(this.getColor());
                super.drawOn(d);
                infoBlock.drawOn(d);
                /*d.setColor(new Color(255, 249, 155));
                for (int i = 30; i < 800; i = i + 8) {
                    d.drawLine(130, 150, i, 310);
                }
                infoBlock.drawOn(d);
                d.setColor(new Color(255, 249, 155));
                d.fillCircle(130, 150, 60);
                d.setColor(new Color(255, 245, 94));
                d.fillCircle(130, 150, 50);
                d.setColor(new Color(255, 240, 20));*/
                for (int i = 0; i < 800; i += rnd.nextInt(50)) {
                    Drop s = new Drop(i, 20 + rnd.nextInt(800), rnd.nextInt(2) + 4,
                            Commons.LV2_RAIN_DROP_COLOR, 10, 10);
                    s.drawOn(d);
                }
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
        Block leftFrame = getFrame(new Point(0, 20), 25, 600, Color.GRAY);
        leftFrame.setBlockID(Commons.BlockKIND.FRAME_BLOCK);
        Block deathRegion = getFrame(new Point(0, 600), 800, 25, Color.white);
        deathRegion.setBlockID(Commons.BlockKIND.BALL_DEATH_BLOCK);
        Block rightFrame = getFrame(new Point(775, 20), 25, 600, Color.GRAY);
        rightFrame.setBlockID(Commons.BlockKIND.FRAME_BLOCK);
        // game level blocks
        Point start1 = new Point(725, 300);
        //Color[] c1 = new Color[4];
        //Color[] c2 = new Color[4];
        /*c1[0] = Color.cyan;
        c1[1] = Color.pink;
        c1[2] = Color.blue;
        c1[3] = Color.green;
        c2[0] = Color.green;
        c2[1] = Color.yellow;
        c2[2] = Color.orange;
        c2[3] = Color.red;*/
        List<Block> lst1 = addLineOfBlocks(start1, 50, 20, new Color(0, 150, 170), 2, 400);
        Point start2 = new Point(325, 300);
        List<Block> lst2 = addLineOfBlocks(start2, 50, 20, new Color(0, 150, 170), 2, 360);
        Rectangle rec4 = new Rectangle(new Point(360, 50), 80, 30);
        rec4.setRecID(Commons.BlockKIND.SMALLER_PADDLE_BLOCK);
        Block smallerPaddle = new Block(rec4, Color.white, null) {
            @Override
            public void drawOn(DrawSurface d) {
                super.drawOn(d);
                d.setColor(Color.red);
                d.fillRectangle((int) rec4.getUpperLeft().getX() + 20, (int) rec4.getUpperLeft().getY() + 5,
                        40, 10);
                d.drawText((int) rec4.getUpperLeft().getX() + 1, (int) rec4.getUpperLeft().getY() + 25,
                        "SMALL PADDLE", 10);
            }
        };
        smallerPaddle.setBlockID(Commons.BlockKIND.SMALLER_PADDLE_BLOCK);
        List<Block> lst = new ArrayList<>();
        lst.addAll(lst1);
        lst.addAll(lst2);
        //lst.add(b);
        lst.add(deathRegion);
        lst.add(leftFrame);
        lst.add(rightFrame);
        lst.add(topFrame);
        lst.add(smallerPaddle);
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}

