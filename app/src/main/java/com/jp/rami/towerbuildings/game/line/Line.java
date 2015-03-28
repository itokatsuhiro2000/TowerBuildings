package com.jp.rami.towerbuildings.game.line;

import com.jp.rami.towerbuildings.game.GameConstants.DEFAULT_VALUE;
import com.jp.rami.towerbuildings.game.GameConstants.LINE_SPEED;

import static com.jp.rami.towerbuildings.game.SizeConstants.LINE_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.LINE_TOP;

public class Line {

    /** ラインの状態 */
    public enum State {
        NONE,
        PREPARE,
        WAIT,
        FALL,
        FIX,
        SCROLL,
        FINISH
    }

    /** X座標 */
    public int posX;

    /** Y座標 */
    public int posY;

    /** ラインの状態 */
    public State status;

    /** ブロック */
    public Block[] blocks = new Block[DEFAULT_VALUE.LINE_BLOCK];

    /**
     * コンストラクタ
     */
    public Line() {
        reset();
    }

    /**
     * ラインを初期化する
     */
    public void reset() {
        posX = LINE_LEFT;
        posY = LINE_TOP;
        status = State.WAIT;

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] == null) {
                blocks[i] = new Block(i);
            } else {
                blocks[i].reset();
            }
        }
    }

    /**
     * ラインを更新する
     */
    public void update() {
        switch (status) {
            case NONE:
                break;
            case PREPARE:
                move();
                break;
            case WAIT:
                break;
            case FALL:
                move();
                break;
            case FIX:
                break;
            case SCROLL:
                scroll();
                break;
            case FINISH:
                break;
        }
    }

    /**
     * ラインを落下させる
     */
    private void move() {
        posY += LINE_SPEED.FALL;
    }

    /**
     * ラインを画面外に移動させる
     */
    private void scroll() {
        posY += LINE_SPEED.SCROLL;
    }
}
