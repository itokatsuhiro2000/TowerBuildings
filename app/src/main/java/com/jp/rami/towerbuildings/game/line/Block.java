package com.jp.rami.towerbuildings.game.line;

import com.jp.rami.towerbuildings.game.GameUtil;

public class Block {

    /** ラインの左からの位置 */
    public int index = 0;

    /** ブロックの数値 */
    public int number = 0;

    /**
     * コンストラクタ
     * @param index 番号
     */
    public Block(int index) {
        this.index = index;
        number = GameUtil.random();
    }

    /**
     * ブロックの状態を初期化する
     */
    public void reset() {
        number = GameUtil.random();
    }
}
