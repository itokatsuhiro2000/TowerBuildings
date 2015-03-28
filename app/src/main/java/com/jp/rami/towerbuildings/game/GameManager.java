package com.jp.rami.towerbuildings.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.jp.rami.towerbuildings.game.allcount.AllCountTask;
import com.jp.rami.towerbuildings.game.control.ControlTask;
import com.jp.rami.towerbuildings.game.fps.FpsCountTask;
import com.jp.rami.towerbuildings.game.line.LineTask;
import com.jp.rami.towerbuildings.game.main.MainTask;

import java.util.LinkedList;

public class GameManager {

    /** タスクのリスト */
    private LinkedList<GameTask> mTaskList = new LinkedList<>();

    /** タッチイベントのリスト */
    public LinkedList<MotionEvent> motionList = new LinkedList<>();

    public GameManager(Context context) {
        // FPS表示
        mTaskList.add(new FpsCountTask());
        // ゲーム領域
        mTaskList.add(new MainTask());
        // ライン
        mTaskList.add(new LineTask(context.getResources()));
        // 総和領域
        mTaskList.add(new AllCountTask(context.getResources()));
        // 操作領域
        mTaskList.add(new ControlTask(context.getResources()));

    }

    /**
     * 状態の更新を行う
     */
    public void update() {
        boolean isTouch = !motionList.isEmpty();
        for (GameTask task : mTaskList) {
            // タッチイベントが存在する場合
            if (isTouch) {
                // タッチイベントを移譲
                task.motionEvent = motionList.getLast();
            }

            // 更新
            task.update();
        }
        // タッチイベントを初期化
        motionList.clear();
    }

    /**
     * 画面の描画を行う
     *
     * @param c キャンバス
     */
    public void draw(Canvas c) {
        // 画面を初期化
        c.drawColor(Color.BLACK);
        for (int i = 0; i < mTaskList.size(); i++) {
            // 画面を描画
            mTaskList.get(i).draw(c);
        }
    }
}
