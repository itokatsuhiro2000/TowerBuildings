package com.jp.rami.towerbuildings.game;


public class GameConstants {

    /** 初期値 */
    public interface DEFAULT_VALUE {
        /** FPS */
        public static final int FPS = 60;
        /** ブロックの数値の最大数 */
        public static final int BLOCK_NUMBER = 9;
        /** 1行のブロック数 */
        public static final int LINE_BLOCK = 4;
        /** 1画面に表示するライン数 */
        public static final int LINE_COUNT = 2;
        /** 総和の基本となる数 */
        public static final int BASE_NUMBER = 10;
    }

    /** ラインの速度 */
    public interface LINE_SPEED {
        /** 落下速度 */
        public static final int FALL = 5;
        /** スクロール速度 */
        public static final int SCROLL = 1;
    }

    /** ラインの状態 */
    public interface LINE_STATUS {
        /** 初期値 */
        public static final int NONE = 0;
        /** 落下中 */
        public static final int MOVE = 1;
        /** 接地 */
        public static final int STOP = 2;
        /** スクロール中 */
        public static final int SCROLL = 3;
    }

    /** 総和変動数 */
    public interface RESULT_COUNT {
        /** 表示フレーム数 */
        public static final int DISPLAY_FRAME = 60;
        /** 不透明度減少値 */
        public static final int SUB_VALUE = 5;
        /** Y座標変動値 */
        public static final int CHANGE_Y = 1;
    }
}
