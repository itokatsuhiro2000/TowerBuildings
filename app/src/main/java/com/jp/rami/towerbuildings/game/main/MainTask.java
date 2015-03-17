package com.jp.rami.towerbuildings.game.main;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.jp.rami.towerbuildings.game.GameTask;

import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_BOTTOM;
import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_RIGHT;
import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_TOP;

/**
 * Created by PCUser on 2015/03/01.
 * メイン部分の処理クラス
 */
public class MainTask extends GameTask {

    public MainTask() {
        rect = new Rect(GAME_LEFT, GAME_TOP, GAME_RIGHT, GAME_BOTTOM);
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public void draw(Canvas c) {
    }
}
