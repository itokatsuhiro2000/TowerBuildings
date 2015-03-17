package com.jp.rami.towerbuildings.game.fps;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.jp.rami.towerbuildings.game.GameTask;

public class FpsCountTask extends GameTask {

	/** サンプル数 */
	private final static int N = 60;
	/** フォントサイズ */
	private final static int FONT_SIZE = 20;

	/** 測定開始時刻 */
	private long mStartTime = 0;
	/** カウンタ */
	private int mCount = 0;
	/** 描画情報 */
	private Paint mPaint = new Paint();
	/** FPS */
	private float mFps;

	public FpsCountTask() {
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(FONT_SIZE);
	}

	@Override
	public boolean update() {
		//1フレーム目の場合
		if (mCount == 0) {
			// 測定開始時刻を保存
			mStartTime = System.currentTimeMillis();
		}

		// 規定回数を測定した場合
		if (mCount == N) {
			// 平均を算出
			long t = System.currentTimeMillis();
			mFps = 1000.f / ((t - mStartTime) / (float) N);
			mCount = 0;
			mStartTime = t;
		}
		mCount++;
		return true;
	}

	@Override
	public void draw(Canvas c) {
		// FPSの値を描画
		c.drawText(String.format("%.1f", mFps), 0, FONT_SIZE - 2, mPaint);
	}
}