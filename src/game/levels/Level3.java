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
public class Level3 extends LevelBuilder implements LevelInformation {


    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        Point ball1Start = new Point(200, 500);
        Point ball2Start = new Point(600, 500);
        Velocity v1 = Velocity.fromAngleAndSpeed(300, 8);
        Velocity v2 = Velocity.fromAngleAndSpeed(60, 8);
        v1.setStartingPoint(ball1Start);
        v2.setStartingPoint(ball2Start);
        v1.setColor(Color.black);
        v2.setColor(Color.black);
        ArrayList<Velocity> lst = new ArrayList<>();
        lst.add(v1);
        lst.add(v2);
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 14;
    }

    @Override
    public int paddleWidth() {
        return 140;
    }

    @Override
    public String levelName() {
        return "Blue 3";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rbg = new geometry.primitives.Rectangle(new Point(0, 0), 800, 600);
        Block infoBlock = getFrame(new Point(0, 0), 800, 20, new Color(185, 185, 185));
        Block bbg = new Block(rbg, new Color(192, 192, 192), null) {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(this.getColor());
                super.drawOn(d);
                infoBlock.drawOn(d);
                d.setColor(Color.white);
                d.fillRectangle(100, 400, 100, 300);
                d.setColor(Color.gray);
                // drawing the cables for the bridge:
                for (int i = 205; i < 500; i = i + 16) {
                    d.drawLine(307, 392, i, 500);
                }
                for (int i = 500; i < 780; i = i + 15) {
                    d.drawLine(657, 392, i, 500);
                }
                d.setColor(Color.gray);
                d.fillRectangle(205, 500, 595, 15);
                d.fillRectangle(148, 140, 10, 210);
                d.setColor(Color.darkGray);
                d.fillRectangle(205, 500, 595, 5);
                d.fillRectangle(205, 510, 605, 5);
                d.setColor(new Color(105, 105, 105));
                d.fillRectangle(300, 400, 15, 200);
                d.fillRectangle(650, 400, 15, 200);
                d.setColor(Color.darkGray);
                d.fillRectangle(300, 400, 5, 200);
                d.fillRectangle(310, 400, 5, 200);
                d.fillRectangle(650, 400, 5, 200);
                d.fillRectangle(660, 400, 5, 200);
                d.setColor(new Color(255, 250, 160));
                d.fillCircle(307, 392, 12);
                d.fillCircle(657, 392, 12);
                d.setColor(Color.orange);
                d.fillCircle(307, 392, 10);
                d.fillCircle(657, 392, 10);
                d.setColor(Color.red);
                d.fillCircle(307, 392, 6);
                d.fillCircle(657, 392, 6);
                d.setColor(Color.white);
                d.fillCircle(307, 392, 3);
                d.fillCircle(657, 392, 3);
                d.setColor(Color.darkGray);
                // drawing the building
                for (int i = 0; i < 110; i = i + 30) {
                    d.fillRectangle(100 + i, 400, 15, 300);
                }
                for (int i = 0; i < 190; i = i + 30) {
                    d.fillRectangle(100, 400 + i, 100, 15);
                }
                d.fillRectangle(135, 350, 35, 50);
                d.setColor(new Color(255, 250, 160));
                d.fillCircle(153, 130, 12);
                d.setColor(Color.orange);
                d.fillCircle(153, 130, 10);
                d.setColor(Color.red);
                d.fillCircle(153, 130, 6);
                d.setColor(Color.white);
                d.fillCircle(153, 130, 3);
            }
        };
        return bbg;
    }

    @Override
    public java.util.List<Block> blocks() {
        //info block:
        // game frame:
        Block topFrame = getFrame(new Point(0, 20), 800, 25, Color.GRAY);
        topFrame.setBlockID(Commons.BlockKIND.FRAME_BLOCK);
        Block leftFrame = getFrame(new Point(0, 20), 25, 600, Color.GRAY);
        leftFrame.setBlockID(Commons.BlockKIND.FRAME_BLOCK);
        Block deathRegion = getFrame(new Point(0, 600), 800, 1, new Color(192, 192, 192));
        deathRegion.setBlockID(Commons.BlockKIND.BALL_DEATH_BLOCK);
        Block rightFrame = getFrame(new Point(775, 20), 25, 600, Color.GRAY);
        rightFrame.setBlockID(Commons.BlockKIND.FRAME_BLOCK);
        // game level blocks
        List<Block> lst1 = addSingleLine(725, 200, 50, 20, new Color(50, 150, 110), 10);
        List<Block> lst2 = addSingleLine(725, 220, 50, 20, new Color(0, 130, 170), 9);
        List<Block> lst3 = addSingleLine(725, 240, 50, 20, new Color(50, 150, 110), 8);
        List<Block> lst4 = addSingleLine(725, 260, 50, 20, new Color(0, 90, 170), 7);
        List<Block> lst5 = addSingleLine(725, 280, 50, 20, new Color(50, 150, 110), 6);
        List<Block> lst = new ArrayList<>();
        lst.addAll(lst1);
        lst.addAll(lst2);
        lst.addAll(lst3);
        lst.addAll(lst4);
        lst.addAll(lst5);
        Rectangle rec1 = new Rectangle(new Point(700, 50), 55, 30);
        rec1.setRecID(Commons.BlockKIND.BALL_BLOCK);
        Block newBall = new Block(rec1, Color.white, null) {
            @Override
            public void drawOn(DrawSurface d) {
                super.drawOn(d);
                d.setColor(new Color(0, 0, 64));
                d.fillCircle((int) rec1.getUpperLeft().getX() + 28, (int) rec1.getUpperLeft().getY() + 10, 8);
                d.drawText((int) rec1.getUpperLeft().getX() + 1, (int) rec1.getUpperLeft().getY() + 28, "NEW BALL", 10);
            }
        };
        newBall.setBlockID(Commons.BlockKIND.BALL_BLOCK);
        Rectangle rec2 = new Rectangle(new Point(680, 100), 90, 30);
        rec2.setRecID(Commons.BlockKIND.BIGGER_PADDLE_BLOCK);
        Block bigPADDLE = new Block(rec2, Color.white, null) {
            @Override
            public void drawOn(DrawSurface d) {
                super.drawOn(d);
                d.setColor(Color.yellow);
                d.fillRectangle((int) rec2.getUpperLeft().getX() + 5, (int) rec2.getUpperLeft().getY() + 5,
                        70, 10);
                d.setColor(new Color(0, 0, 64));
                d.drawText((int) rec2.getUpperLeft().getX() + 2, (int) rec2.getUpperLeft().getY() + 26,
                        "BIGGER PADDLE", 10);
            }
        };
        bigPADDLE.setBlockID(Commons.BlockKIND.BIGGER_PADDLE_BLOCK);
        lst.add(deathRegion);
        lst.add(leftFrame);
        lst.add(rightFrame);
        lst.add(topFrame);
        lst.add(newBall);
        lst.add(bigPADDLE);
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
