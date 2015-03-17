package com.jp.rami.towerbuildings.game.line;

import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.jp.rami.towerbuildings.game.GameConstants;

import static com.jp.rami.towerbuildings.game.SizeConstants.RESULT_POS_X;
import static com.jp.rami.towerbuildings.game.SizeConstants.RESULT_POS_Y;

public class ResultCount {

    /** 描画フラグ */
    public boolean isDraw = false;

    /** X座標 */
    public int posX = RESULT_POS_X;

    /** Y座標 */
    public int posY = RESULT_POS_Y;

    /** 総和変動数 */
    public int[] resultCounts = new int[GameConstants.DEFAULT_VALUE.LINE_BLOCK];

    /** 数値描画用のPaint */
    public Paint paint = new Paint();

    /** 表示フレーム数 */
    private int mFrame = 0;

    /** 不透明度 */
    private int mAlpha = 255;

    public ResultCount() {
        // フォントを設定
        paint.setStyle(Style.FILL);
        // 色を設定
        paint.setARGB(mAlpha, 255, 255, 255);
        // サイズを設定
        paint.setTextSize(32);
    }

    /**
     * 初期化する
     */
    public void init() {
        isDraw = false;
        posY = RESULT_POS_Y;
        mFrame = 0;
        mAlpha = 255;
    }

    /**
     * 更新する
     */
    public void update() {
        // Y座標を変動
        posY -= GameConstants.RESULT_COUNT.CHANGE_Y;
        // 不透明度を減少
        mAlpha -= 255 / GameConstants.RESULT_COUNT.DISPLAY_FRAME;
        paint.setAlpha(mAlpha);
        // 表示フレーム数を増加
        mFrame++;

        // 表示フレーム数が規定値を超えた場合
        if (mFrame > GameConstants.RESULT_COUNT.DISPLAY_FRAME) {
            // 初期化
            init();
        }
    }
}
