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
import java.util.List;
import java.util.Random;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 27-05-2022
 */
public class Level4 extends LevelBuilder implements LevelInformation {


    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        Point ball1Start = new Point(200, 500);
        Point ball2Start = new Point(600, 500);
        Point ball3Start = new Point(400, 450);
        Velocity v1 = Velocity.fromAngleAndSpeed(300, 8);
        Velocity v2 = Velocity.fromAngleAndSpeed(60, 8);
        Velocity v3 = Velocity.fromAngleAndSpeed(0, 8);
        v1.setStartingPoint(ball1Start);
        v2.setStartingPoint(ball2Start);
        v3.setStartingPoint(ball3Start);
        v1.setColor(Color.black);
        v2.setColor(Color.black);
        v3.setColor(Color.black);
        ArrayList<Velocity> lst = new ArrayList<>();
        lst.add(v1);
        lst.add(v2);
        lst.add(v3);
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return 12;
    }

    @Override
    public int paddleWidth() {
        return 160;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        geometry.primitives.Rectangle rbg = new geometry.primitives.Rectangle(new Point(0, 0), 800, 600);
        Block infoBlock = getFrame(new Point(0, 0), 800, 20, new Color(185, 185, 185));
        Block bbg = new Block(rbg, new Color(200, 210, 255), null) {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(this.getColor());
                super.drawOn(d);
                infoBlock.drawOn(d);
                // drawing cloud
                Random rnd = new Random();
                Color cloud1 = new Color(224, 224, 224);
                Color cloud2 = new Color(210, 210, 210);
                Color cloud3 = new Color(180, 180, 180);
                for (int i = 120; i < 200; i += rnd.nextInt(10)) {
                    Drop s = new Drop(i, 350 + rnd.nextInt(250), rnd.nextInt(3) + 2,
                            Color.black, 10, 10);
                    s.drawOn(d);
                }
                d.setColor(cloud1);
                d.fillCircle(125, 345, 20);
                d.fillCircle(150, 360, 30);
                d.setColor(cloud2);
                d.fillCircle(162, 340, 30);
                d.setColor(cloud3);
                d.fillCircle(200, 350, 30);
                d.fillCircle(180, 370, 20);
                for (int i = 520; i < 600; i += rnd.nextInt(10)) {
                    Drop s = new Drop(i, 145 + rnd.nextInt(500), rnd.nextInt(2) + 2,
                            Color.black, 10, 10);
                    s.drawOn(d);
                }
                d.setColor(cloud1);
                d.fillCircle(525, 145, 20);
                d.fillCircle(550, 160, 30);
                d.setColor(cloud2);
                d.fillCircle(562, 140, 30);
                d.setColor(cloud3);
                d.fillCircle(600, 150, 30);
                d.fillCircle(580, 170, 20);
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
        java.util.List<Block> lst1 = addSingleLine(725, 200, 50, 20, new Color(0, 150, 170), 15);
        java.util.List<Block> lst2 = addSingleLine(725, 220, 50, 20, new Color(0, 130, 170), 15);
        java.util.List<Block> lst3 = addSingleLine(725, 240, 50, 20, new Color(0, 110, 170), 15);
        java.util.List<Block> lst4 = addSingleLine(725, 260, 50, 20, new Color(0, 90, 170), 15);
        java.util.List<Block> lst5 = addSingleLine(725, 280, 50, 20, new Color(0, 70, 170), 15);
        List<Block> lst = new ArrayList<>();
        lst.addAll(lst1);
        lst.addAll(lst2);
        lst.addAll(lst3);
        lst.addAll(lst4);
        lst.addAll(lst5);
        geometry.primitives.Rectangle rec1 = new Rectangle(new Point(200, 45), 60, 30);
        geometry.primitives.Rectangle rec2 = new Rectangle(new Point(400, 45), 60, 30);
        geometry.primitives.Rectangle rec3 = new Rectangle(new Point(600, 45), 60, 30);
        rec1.setRecID(Commons.BlockKIND.BALL_BLOCK);
        Block newBall1 = new Block(rec1, Color.white, null) {
            @Override
            public void drawOn(DrawSurface d) {
                super.drawOn(d);
                d.setColor(new Color(0, 0, 64));
                d.fillCircle((int) rec1.getUpperLeft().getX() + 26, (int) rec1.getUpperLeft().getY() + 10, 8);
                d.drawText((int) rec1.getUpperLeft().getX() + 2, (int) rec1.getUpperLeft().getY() + 28, "NEW BALL", 10);
            }
        };
        rec2.setRecID(Commons.BlockKIND.BALL_BLOCK);
        Block newBall2 = new Block(rec2, Color.white, null) {
            @Override
            public void drawOn(DrawSurface d) {
                super.drawOn(d);
                d.setColor(new Color(0, 0, 64));
                d.fillCircle((int) rec2.getUpperLeft().getX() + 26, (int) rec2.getUpperLeft().getY() + 10, 8);
                d.drawText((int) rec2.getUpperLeft().getX() + 2, (int) rec2.getUpperLeft().getY() + 28, "NEW BALL", 10);
            }
        };
        rec3.setRecID(Commons.BlockKIND.BALL_BLOCK);
        Block newBall3 = new Block(rec3, Color.white, null) {
            @Override
            public void drawOn(DrawSurface d) {
                super.drawOn(d);
                d.setColor(new Color(0, 0, 64));
                d.fillCircle((int) rec3.getUpperLeft().getX() + 26, (int) rec3.getUpperLeft().getY() + 10, 8);
                d.drawText((int) rec3.getUpperLeft().getX() + 2, (int) rec3.getUpperLeft().getY() + 28, "NEW BALL", 10);
            }
        };
        Rectangle rec4 = new Rectangle(new Point(100, 150), 90, 30);
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
        Rectangle rec5 = new Rectangle(new Point(650, 150), 90, 30);
        rec5.setRecID(Commons.BlockKIND.BIGGER_PADDLE_BLOCK);
        Block biggerPaddle = new Block(rec5, Color.white, null) {
            @Override
            public void drawOn(DrawSurface d) {
                super.drawOn(d);
                d.setColor(Color.yellow);
                d.fillRectangle((int) rec5.getUpperLeft().getX() + 5, (int) rec5.getUpperLeft().getY() + 5,
                        70, 10);
                d.setColor(new Color(0, 0, 64));
                d.drawText((int) rec5.getUpperLeft().getX() + 2, (int) rec5.getUpperLeft().getY() + 26,
                        "BIGGER PADDLE", 10);
            }
        };
        biggerPaddle.setBlockID(Commons.BlockKIND.BIGGER_PADDLE_BLOCK);
        smallerPaddle.setBlockID(Commons.BlockKIND.SMALLER_PADDLE_BLOCK);
        newBall1.setBlockID(Commons.BlockKIND.BALL_BLOCK);
        newBall2.setBlockID(Commons.BlockKIND.BALL_BLOCK);
        newBall3.setBlockID(Commons.BlockKIND.BALL_BLOCK);
        lst.add(deathRegion);
        lst.add(leftFrame);
        lst.add(rightFrame);
        lst.add(topFrame);
        lst.add(newBall1);
        lst.add(newBall2);
        lst.add(newBall3);
        lst.add(smallerPaddle);
        lst.add(biggerPaddle);
        return lst;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 75;
    }
}
