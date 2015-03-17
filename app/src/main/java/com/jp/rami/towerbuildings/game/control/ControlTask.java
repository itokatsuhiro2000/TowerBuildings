package com.jp.rami.towerbuildings.game.control;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;

import com.jp.rami.towerbuildings.R;
import com.jp.rami.towerbuildings.game.GameTask;

import static com.jp.rami.towerbuildings.game.SizeConstants.CONTROL_BOTTOM;
import static com.jp.rami.towerbuildings.game.SizeConstants.CONTROL_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.CONTROL_RIGHT;
import static com.jp.rami.towerbuildings.game.SizeConstants.CONTROL_TOP;
import static com.jp.rami.towerbuildings.game.SizeConstants.MINUS_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.MINUS_TOP;
import static com.jp.rami.towerbuildings.game.SizeConstants.PLUS_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.PLUS_TOP;

public class ControlTask extends GameTask {

    /** 背景画像 */
    private Paint mBackground = new Paint();

    /** マイナスボタン */
    private Bitmap mButtonMinus = null;

    /** プラスボタン */
    private Bitmap mButtonPlus = null;

    public ControlTask(Resources resources) {
        rect = new Rect(CONTROL_LEFT, CONTROL_TOP, CONTROL_RIGHT, CONTROL_BOTTOM);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inScaled = false;

        // 背景画像
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.controll_bg);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mBackground.setShader(shader);

        // マイナスボタン
        mButtonMinus = BitmapFactory.decodeResource(resources, R.drawable.button_minus, opt);

        // プラスボタン
        mButtonPlus = BitmapFactory.decodeResource(resources, R.drawable.button_plus, opt);
    }

    @Override
    public boolean update() {
        // タッチイベントが存在する場合
        if (motionEvent != null) {
            // タッチイベントの判定
            judgeMotionEvent();
            motionEvent = null;
        }
        return true;
    }

    @Override
    public void draw(Canvas c) {
        c.save();
        // 描画領域を設定
        c.clipRect(rect);

        // 背景を描画
        c.drawPaint(mBackground);

        // マイナスボタンを描画
        c.drawBitmap(mButtonMinus,
                MINUS_LEFT,
                MINUS_TOP,
                null);

        // プラスボタンを描画
        c.drawBitmap(mButtonPlus,
                PLUS_LEFT,
                PLUS_TOP,
                null);

        c.restore();
    }

    /**
     * タッチイベントの判定を行う
     */
    private void judgeMotionEvent() {
    }
}