package com.jp.rami.towerbuildings.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.jp.rami.towerbuildings.game.TouchEventConstants.Touch;

public abstract class GameTask {

    /** コンテキスト */
    public Context context = MainActivity.context;

    /** 描画領域 */
    public Rect rect = null;

    /** タッチイベント */
    public MotionEvent motionEvent = null;
    /** ハンドリングしたタッチイベント */
    public Touch bindEvent = Touch.NONE;

    abstract public boolean update();

    abstract public void draw(Canvas c);
}