package com.jp.rami.towerbuildings.game.line;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.jp.rami.towerbuildings.R;
import com.jp.rami.towerbuildings.game.GameConstants.DEFAULT_VALUE;
import com.jp.rami.towerbuildings.game.GameTask;
import com.jp.rami.towerbuildings.game.line.Line.State;

import java.util.ArrayList;
import java.util.Collections;

import static com.jp.rami.towerbuildings.game.SizeConstants.BLOCK_SIZE;
import static com.jp.rami.towerbuildings.game.SizeConstants.CONTROL_TOP;
import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_BOTTOM;
import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_HEIGHT;
import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_RIGHT;
import static com.jp.rami.towerbuildings.game.SizeConstants.GAME_TOP;
import static com.jp.rami.towerbuildings.game.SizeConstants.MINUS_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.MINUS_RIGHT;
import static com.jp.rami.towerbuildings.game.SizeConstants.PLUS_LEFT;
import static com.jp.rami.towerbuildings.game.SizeConstants.PLUS_RIGHT;

public class LineTask extends GameTask {

    /** ライン */
    public ArrayList<Line> mLines = new ArrayList<>();

    /** 落下中のライン */
    private int mCurrentLineIndex = 0;

    /** ブロックの画像 */
    private Bitmap[] mBlocks = new Bitmap[DEFAULT_VALUE.BLOCK_NUMBER];

    /** 総和変動数 */
    private ResultCount mResultCount = new ResultCount();

    public LineTask(Resources resources) {
        for (int i = 0; i <= DEFAULT_VALUE.LINE_COUNT; i++) {
            // [画面に表示するライン数 + 1] のラインを生成
            mLines.add(new Line());
        }
        mLines.get(0).status = State.PREPARE;
        rect = new Rect(GAME_LEFT, GAME_TOP, GAME_RIGHT, GAME_BOTTOM);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inScaled = false;
        mBlocks[0] = BitmapFactory.decodeResource(resources, R.drawable.block_01, opt);
        mBlocks[1] = BitmapFactory.decodeResource(resources, R.drawable.block_02, opt);
        mBlocks[2] = BitmapFactory.decodeResource(resources, R.drawable.block_03, opt);
        mBlocks[3] = BitmapFactory.decodeResource(resources, R.drawable.block_04, opt);
        mBlocks[4] = BitmapFactory.decodeResource(resources, R.drawable.block_05, opt);
        mBlocks[5] = BitmapFactory.decodeResource(resources, R.drawable.block_06, opt);
        mBlocks[6] = BitmapFactory.decodeResource(resources, R.drawable.block_07, opt);
        mBlocks[7] = BitmapFactory.decodeResource(resources, R.drawable.block_08, opt);
        mBlocks[8] = BitmapFactory.decodeResource(resources, R.drawable.block_09, opt);
    }

    @Override
    public boolean update() {
        // タッチイベントが存在する場合
        if (motionEvent != null) {
            // タッチイベントの判定
            judgeMotionEvent();
            motionEvent = null;
        }

        updateLine();

        // 総和変動数が表示中の場合
        if (mResultCount.isDraw) {
            // 総和変動数を更新
            mResultCount.update();
        }

        return true;
    }

    @Override
    public void draw(Canvas c) {
        c.save();
        c.clipRect(rect);

        // ラインを描画
        for (Line line : mLines) {
            for (Block block : line.blocks) {
                c.drawBitmap(mBlocks[block.number],
                        line.posX + (block.index * BLOCK_SIZE),
                        line.posY,
                        null);
            }
        }

        // 総和変動数を描画
        if (mResultCount.isDraw) {
            int[] resultCounts = mResultCount.resultCounts;
            int length = resultCounts.length;
            for (int i = 0; i < length; i++) {
                int resultCount = resultCounts[i];
                String sign = "";
                if (resultCount >= 0) {
                    sign = "+";
                }
                c.drawText(sign + resultCount,
                        mResultCount.posX + (i * BLOCK_SIZE),
                        mResultCount.posY,
                        mResultCount.paint);
            }
        }
        c.restore();
    }

    /**
     * ラインを更新する
     */
    private void updateLine() {
        boolean finished = false;
        for (int i = 0; i < mLines.size(); i++) {
            Line line = mLines.get(i);
            int fixY = GAME_HEIGHT - ((i + 1) * BLOCK_SIZE);
            int nextFixY = fixY + BLOCK_SIZE;

            line.update();
            switch (line.status) {
                case NONE:
                    break;
                case PREPARE:
                    mCurrentLineIndex = i;
                    if (line.posY < fixY) {
                        continue;
                    }
                    line.posY = fixY;
                    mLines.get(i + 1).status = State.PREPARE;
                    if (i == 0) {
                        line.status = State.FIX;
                        continue;
                    }
                    for (int j = 0; j <= i; j++) {
                        mLines.get(j).status = State.SCROLL;
                    }
                    break;
                case WAIT:
                    break;
                case FALL:
                    if (line.posY < fixY) {
                        continue;
                    }
                    line.posY = fixY;
                    mLines.get(i + 1).status = State.PREPARE;
                    calcResultCount();
                    if (i == 0) {
                        line.status = State.FIX;
                        continue;
                    }
                    for (int j = 0; j <= i; j++) {
                        mLines.get(j).status = State.SCROLL;
                    }
                    break;
                case FIX:
                    break;
                case SCROLL:
                    if (line.posY < nextFixY) {
                        continue;
                    }
                    if (line.posY < GAME_BOTTOM) {
                        line.posY = nextFixY;
                        line.status = State.FIX;
                        continue;
                    }
                    line.reset();
                    finished = true;
                    break;
                case FINISH:
                    break;
            }
        }
        // ラインが画面外に出た場合、順番を変更
        if (finished) {
            Collections.rotate(mLines, -1);
        }
    }

    /**
     * 総和変動数を算出する
     */
    private void calcResultCount() {
        for (int i = 0; i < DEFAULT_VALUE.LINE_BLOCK; i++) {
            // ブロックの数値の差分を算出
            int result = (mLines.get(mCurrentLineIndex).blocks[i].number + 1) +
                    (mLines.get(mCurrentLineIndex - 1).blocks[i].number + 1);
            // 差分から総和の基本となる数を減算
            mResultCount.resultCounts[i] = result - DEFAULT_VALUE.BASE_NUMBER;
        }
        mResultCount.isDraw = true;
    }

    /**
     * タッチイベントの判定を行う
     */
    private void judgeMotionEvent() {
        float posX = motionEvent.getX();
        float posY = motionEvent.getY();
        // マイナスボタンの場合
        if (posX > MINUS_LEFT && posX < MINUS_RIGHT
                && posY > CONTROL_TOP) {
            // ブロックの番号を減少
            for (Block block : mLines.get(mCurrentLineIndex).blocks) {
                block.number--;
                block.number = (block.number + DEFAULT_VALUE.BLOCK_NUMBER) % DEFAULT_VALUE.BLOCK_NUMBER;
            }
        }
        // プラスボタンの場合
        else if (posX > PLUS_LEFT && posX < PLUS_RIGHT
                && posY > CONTROL_TOP) {
            // ブロックの番号を増加
            for (Block block : mLines.get(mCurrentLineIndex).blocks) {
                block.number++;
                block.number %= DEFAULT_VALUE.BLOCK_NUMBER;
            }
        }
    }
}