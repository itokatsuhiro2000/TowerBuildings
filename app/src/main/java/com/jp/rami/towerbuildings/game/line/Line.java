package com.jp.rami.towerbuildings.game.line;

import com.jp.rami.towerbuildings.game.GameConstants.DEFAULT_VALUE;
import com.jp.rami.towerbuildings.game.GameConstants.LINE_SPEED;

import static com.jp.rami.towerbuildings.game.SizeConstants.BLOCK_SIZE;
import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_HEIGHT;
import static com.jp.rami.towerbuildings.game.SizeConstants.LINE_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.LINE_TOP;

public class Line {

    /** ラインの状態 */
    public enum STATE {
        NONE,
        WAIT,
        MOVE,
        FIX,
        SCROLL
    }

    /** X座標 */
    public int posX;

    /** Y座標 */
    public int posY;

    /** ラインの状態 */
    private STATE mStatus;

    /** ブロック */
    public Block[] blocks = new Block[DEFAULT_VALUE.LINE_BLOCK];

    /** 行番号 */
    private int num;

    /** ラインの停止位置の下限 */
    private int maxY;

    /** ラインの移動位置の下限 */
    private int scrollY;

    /**
     * コンストラクタ
     * @param index 番号
     */
    public Line(int index) {
        num = index + 1;
        reset();
    }

    /**
     * ラインを初期化する
     */
    public void reset() {
        posX = LINE_LEFT;
        posY = LINE_TOP;
        maxY = GAME_HEIGHT - (num * BLOCK_SIZE);
        scrollY = maxY + BLOCK_SIZE;
        mStatus = STATE.NONE;

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] == null) {
                blocks[i] = new Block(i);
            } else {
                blocks[i].reset();
            }
        }
    }

    public STATE getStatus() {
        return mStatus;
    }

    /**
     * ラインの状態を設定する
     * @param state ラインの状態
     */
    public void setStatus(STATE state) {
        mStatus = state;
    }

    /**
     * ラインを更新する
     * @return ラインの状態
     */
    public STATE update() {
        switch (mStatus) {
            case NONE:
                break;
            case MOVE:
                mStatus = move();
                break;
            case FIX:
                mStatus = STATE.WAIT;
                break;
            case SCROLL:
                mStatus = scroll();
                break;
        }
        return mStatus;
    }

    /**
     * ラインを落下させる
     * @return ラインの状態
     */
    private STATE move() {
        posY += LINE_SPEED.FALL;
        return checkMovePosition();
    }

    /**
     * 落下後の位置の判定
     * @return ラインの状態
     */
    private STATE checkMovePosition() {
        if (posY <= maxY) {
            return STATE.MOVE;
        }

        posY = maxY;
        return STATE.FIX;
    }

    /**
     * ラインを画面外に移動させる
     * @return ラインの状態
     */
    private STATE scroll() {
        posY += LINE_SPEED.SCROLL;
        return checkScrollPosition();
    }

    /**
     * 移動後の位置の判定
     * @return ラインの状態
     */
    private STATE checkScrollPosition() {
        if (posY <= scrollY) {
            return STATE.SCROLL;
        }

        if (posY <= GAME_HEIGHT) {
            posY = scrollY;
            num--;
            return STATE.FIX;
        }

        reset();
        num = DEFAULT_VALUE.LINE_COUNT + 1;
        return STATE.NONE;
    }
}
