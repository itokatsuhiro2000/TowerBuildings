package com.jp.rami.towerbuildings.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jp.rami.towerbuildings.game.GameConstants.DEFAULT_VALUE;

import static com.jp.rami.towerbuildings.game.SizeConstants.WINDOW_HEIGHT;
import static com.jp.rami.towerbuildings.game.SizeConstants.WINDOW_WIDTH;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    /** 画面の拡大率 */
    public float scale = 0.0f;

    private Context mContext = null;

    private GameManager mGameManager = null;
    private Thread mThread = null;

    public GameView(Context context) {
        super(context);
        mContext = context;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        float scaleX = (float) getWidth() / WINDOW_WIDTH;
        float scaleY = (float) getHeight() / WINDOW_HEIGHT;
        scale = scaleX > scaleY ? scaleY : scaleX;

        mGameManager = new GameManager(mContext);

        // 処理を開始
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread = null;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // タップ時の場合
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 座標を画面サイズに対応
            event.setLocation(event.getX() / scale, event.getY() / scale);
            mGameManager.motionList.add(event);
        }
        return true;
    }

    @Override
    public void run() {
        while (mThread != null) {
            long oldTime = System.currentTimeMillis();
            // 状態を更新
            mGameManager.update();
            // 画面を描画
            draw(getHolder());
            long newTime = System.currentTimeMillis();
            long sleepTime = (1000 / DEFAULT_VALUE.FPS) - (newTime - oldTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                }
            } else {
                // Log.e("TEST", "遅延:" + -sleepTime);
            }
        }
    }

    /**
     * 画面の描画を行う
     *
     * @param holder SurfaceHolder
     */
    private void draw(SurfaceHolder holder) {
        Canvas c = holder.lockCanvas();
        if (c == null) {
            return;
        }
        // 描画領域を移動
        c.translate(0, getHeight() - (WINDOW_HEIGHT * scale));
        // 描画領域を拡縮
        c.scale(scale, scale);
        // 画面を描画
        mGameManager.draw(c);
        holder.unlockCanvasAndPost(c);
    }
}
