package com.jp.rami.towerbuildings.game;

/**
 * Created by PCUser on 2015/03/01.
 * 大きさ・座標用の定数クラス
 */
public class SizeConstants {

    /** ゲーム画面の横幅 */
    public static final int WINDOW_WIDTH = 720;
    /** ゲーム画面の縦幅 */
    public static final int WINDOW_HEIGHT = 1230;

    /** ゲーム領域の横幅 */
    public static final int GAME_WIDTH = WINDOW_WIDTH;
    /** ゲーム領域の縦幅 */
    public static final int GAME_HEIGHT = 900;
    /** ゲーム領域の左座標 */
    public static final int GAME_LEFT = 0;
    /** ゲーム領域の上座標 */
    public static final int GAME_TOP = 0;
    /** ゲーム領域の右座標 */
    public static final int GAME_RIGHT = GAME_LEFT + GAME_WIDTH;
    /** ゲーム領域の下座標 */
    public static final int GAME_BOTTOM = GAME_TOP + GAME_HEIGHT;

    /** ブロックの大きさ */
    public static final int BLOCK_SIZE = 72;

    /** ラインの横幅 */
//    public static final int LINE_WIDTH = BLOCK_SIZE * GameStatus.getInstance().lineBlockCount;
    public static final int LINE_WIDTH = BLOCK_SIZE * 4;
    /** ラインの縦幅 */
    public static final int LINE_HEIGHT = BLOCK_SIZE;
    /** ラインの左座標 */
    public static final int LINE_LEFT = (GAME_WIDTH / 2) - (LINE_WIDTH / 2);
    /** ラインの上座標 */
    public static final int LINE_TOP = -BLOCK_SIZE;
    /** ラインの右座標 */
    public static final int LINE_RIGHT = LINE_LEFT + LINE_WIDTH;
    /** ラインの下座標 */
    public static final int LINE_BOTTOM = LINE_TOP + LINE_HEIGHT;

    /** 総和変動数のX座標 */
    public static final int RESULT_POS_X = LINE_LEFT + (BLOCK_SIZE / 4);
    /** 総和変動数のY座標 */
    public static final int RESULT_POS_Y = GAME_BOTTOM - 128;

    /** 総和領域の横幅 */
    public static final int ALL_COUNT_WIDTH = GAME_WIDTH;
    /** 総和領域の縦幅 */
    public static final int ALL_COUNT_HEIGHT = 80;
    /** 総和領域の左座標 */
    public static final int ALL_COUNT_LEFT = 0;
    /** 総和領域の上座標 */
    public static final int ALL_COUNT_TOP = GAME_HEIGHT;
    /** 総和領域の右座標 */
    public static final int ALL_COUNT_RIGHT = ALL_COUNT_LEFT + ALL_COUNT_WIDTH;
    /** 総和領域の下座標 */
    public static final int ALL_COUNT_BOTTOM = ALL_COUNT_TOP + ALL_COUNT_HEIGHT;

    /** 操作領域の横幅 */
    public static final int CONTROL_WIDTH = GAME_WIDTH;
    /** 操作領域の縦幅 */
    public static final int CONTROL_HEIGHT = WINDOW_HEIGHT - (GAME_HEIGHT + ALL_COUNT_HEIGHT);
    /** 操作領域の左座標 */
    public static final int CONTROL_LEFT = 0;
    /** 操作領域の上座標 */
    public static final int CONTROL_TOP = ALL_COUNT_BOTTOM;
    /** 操作領域の右座標 */
    public static final int CONTROL_RIGHT = CONTROL_LEFT + CONTROL_WIDTH;
    /** 操作領域の下座標 */
    public static final int CONTROL_BOTTOM = CONTROL_TOP + CONTROL_HEIGHT;

    /** マイナスボタンの横幅 */
    public static final int MINUS_WIDTH = 162;
    /** マイナスボタンの縦幅 */
    public static final int MINUS_HEIGHT = 108;
    /** マイナスボタンの左座標 */
    public static final int MINUS_LEFT = CONTROL_LEFT + 24;
    /** マイナスボタンの上座標 */
    public static final int MINUS_TOP = CONTROL_TOP + (CONTROL_HEIGHT / 2) - (MINUS_HEIGHT / 2);
    /** マイナスボタンの右座標 */
    public static final int MINUS_RIGHT = MINUS_LEFT + MINUS_WIDTH;
    /** マイナスボタンの下座標 */
    public static final int MINUS_BOTTOM = MINUS_TOP + MINUS_HEIGHT;

    /** プラスボタンの横幅 */
    public static final int PLUS_WIDTH = 162;
    /** プラスボタンの縦幅 */
    public static final int PLUS_HEIGHT = 108;
    /** プラスボタンの左座標 */
    public static final int PLUS_LEFT = CONTROL_RIGHT - PLUS_WIDTH - 24;
    /** プラスボタンの上座標 */
    public static final int PLUS_TOP = CONTROL_TOP + (CONTROL_HEIGHT / 2) - (PLUS_HEIGHT / 2);
    /** プラスボタンの右座標 */
    public static final int PLUS_RIGHT = PLUS_LEFT + PLUS_WIDTH;
    /** プラスボタンの下座標 */
    public static final int PLUS_BOTTOM = PLUS_TOP + PLUS_HEIGHT;
}
