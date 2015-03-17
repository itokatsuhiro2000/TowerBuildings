package com.jp.rami.towerbuildings.game.allcount;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;

import com.jp.rami.towerbuildings.R;
import com.jp.rami.towerbuildings.game.GameTask;

import static com.jp.rami.towerbuildings.game.SizeConstants.ALL_COUNT_BOTTOM;
import static com.jp.rami.towerbuildings.game.SizeConstants.ALL_COUNT_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.ALL_COUNT_RIGHT;
import static com.jp.rami.towerbuildings.game.SizeConstants.ALL_COUNT_TOP;

public class AllCountTask extends GameTask {

    /** 背景画像 */
    private Paint mBackground = new Paint();

    public AllCountTask(Resources resources) {
        rect = new Rect(ALL_COUNT_LEFT, ALL_COUNT_TOP, ALL_COUNT_RIGHT, ALL_COUNT_BOTTOM);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inScaled = false;

        // 背景画像
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.allcount_bg);
        Shader shader = new BitmapShader(bitmap, TileMode.REPEAT, TileMode.REPEAT);
        mBackground.setShader(shader);
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public void draw(Canvas c) {
        c.save();
        // 描画領域を設定
        c.clipRect(rect);

        // 背景を描画
        c.drawPaint(mBackground);

        c.restore();
    }
}