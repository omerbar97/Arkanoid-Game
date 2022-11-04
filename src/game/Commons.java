// 315318766 Omer Bar

package game;

import java.awt.Color;

/**
 * @author Omer Bar
 * @version jdk 17
 * @since 10-05-2022
 */
public class Commons {

    /**
     * enum for block types.
     */
    public enum BlockKIND {
        FRAME_BLOCK, NORMAL_BLOCK, BALL_BLOCK, BALL_DEATH_BLOCK,
        DESTROYER_BLOCK, PADDLE, BIGPADDLE, SMALLPADDLE, BIGGER_PADDLE_BLOCK, SMALLER_PADDLE_BLOCK
    }

    // trash value:
    public static final double TRASH = 1E-10;

    // game frames:
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 600;

    // paddle ball hit settings
    public static final int LEFT_LEFT_HIT_PADDLE_ANGLE = 300;
    public static final int LEFT_HIT_PADDLE_ANGLE = 330;
    public static final int MIDDLE_HIT_PADDLE_ANGLE = 0;
    public static final int RIGHT_HIT_PADDLE_ANGLE = 30;
    public static final int RIGHT_RIGHT_HIT_PADDLE_ANGLE = 60;

    // paddle movement
    public static final int PADDLE_MOVEMENT = 10;

    // paddle size:
    public static final int LV1_PADDLE_WIDTH = 150;
    public static final int LV1_PADDLE_HEIGHT = 20;

    // ball characteristics
    public static final int BALL_RADIOS = 8;
    public static final double BALL_SPEED = 8;

    // block size
    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 20;

    //block for frames.
    public static final int SCREEN_BLOCK_WIDTH = 25;
    public static final int SCREEN_BLOCK_HEIGHT = 25;

    // score setting
    public static final int SCORE_STARTING_X = 360;
    public static final int SCORE_STARTING_Y = 17;
    public static final int TEXT_SIZE = 18;

    //level name position:
    public static final int LEVEL_NAME_STARTING_X = 510;
    public static final int LEVEL_NAME_STARTING_Y = 17;

    //Live display:
    public static final int LIVE_TEXT_STARTING_X = 100;
    public static final int LIVE_TEXT_STARTING_Y = 17;

    // default color:
    public static final Color DEFAULT_PADDLE_COLOR = Color.yellow;

    // color LVL 1:
    public static final Color LV2_RAIN_DROP_COLOR = new Color(200, 200, 200);
    public static final Color LV2_BALL_COLOR = new Color(150, 160, 220);
    public static final Color LV2_PADDLE_COLOR = new Color(0, 150, 170);
    public static final Color LV2_SCREEN_FRAME_COLOR = new Color(240, 240, 240);
    public static final Color LV2_BG_COLOR = new Color(0, 20, 30);


}
