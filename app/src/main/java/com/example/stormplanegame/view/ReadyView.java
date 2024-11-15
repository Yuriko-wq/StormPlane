package com.example.stormplanegame.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.stormplanegame.MainActivity;
import com.example.stormplanegame.R;
import com.example.stormplanegame.constant.ConstantUtil;
import com.example.stormplanegame.sounds.GameSoundPool;

@SuppressLint("ViewConstructor")
public class ReadyView  extends BaseView {
    private Rect rect;

    private float fly_x;
    private float fly_y;
    private float fly_height;
    private float text_x;
    private float text_y;
    private float button_x;
    private float button_y;
    private float button_y2;
    private float strwid;
    private float strhei;
    private boolean isBtChange;
    private boolean isBtChange2;
    private String startGame = "Старт";
    private String exitGame = "Выход";
    private String version = "Учебная версия";
    private float version_width;

    private Bitmap text;
    private Bitmap button;
    private Bitmap button2;
    private Bitmap planefly;
    private Bitmap background;

    public ReadyView(MainActivity mainActivity, GameSoundPool sounds) {
        super(mainActivity, sounds);
        paint.setTextSize(40);
        rect = new Rect();
        thread = new Thread(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        super.surfaceCreated(holder);
        initBitmap();
        if (thread.isAlive()) {
            thread.start();
        } else {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        super.surfaceChanged(holder, format, width, height);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        super.surfaceDestroyed(holder);
        release();
    }

    @Override
    public void initBitmap() {
        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg_01);
        text = BitmapFactory.decodeResource(getResources(), R.drawable.text);
        planefly = BitmapFactory.decodeResource(getResources(), R.drawable.fly);
        button = BitmapFactory.decodeResource(getResources(), R.drawable.button);
        button2 = BitmapFactory.decodeResource(getResources(), R.drawable.button2);

        scalex = screen_width / background.getWidth();
        scaley = screen_height / background.getHeight();

        text_x = screen_width / 2 - (float) text.getWidth() / 2;
        text_y = screen_height / 2 - text.getHeight();

        fly_x = screen_width / 2 - (float) planefly.getWidth() / 2;
        fly_height = (float) planefly.getHeight() / 3;
        fly_y = text_y - fly_height - 20;

        button_x = screen_width / 2 - (float) button.getWidth() / 2;
        button_y = screen_height / 2 + button.getHeight();
        button_y2 = button_y + button.getHeight() + 40;

        paint.getTextBounds(startGame, 0, startGame.length(), rect);
        strwid = rect.width();
        strhei = rect.height();

        paint.getTextBounds(version, 0, version.length(), rect);
        version_width = rect.width();
    }

    @Override
    public void release() {
        if (!text.isRecycled()) {
            text.recycle();
        }
        if (!button.isRecycled()) {
            button.recycle();
        }
        if (!button2.isRecycled()) {
            button2.recycle();
        }
        if (!planefly.isRecycled()) {
            planefly.recycle();
        }
        if (!background.isRecycled()) {
            background.recycle();
        }
    }

    @Override
    public void drawSelf() {
        try {
            canvas = sfh.lockCanvas();
            canvas.drawColor(Color.BLACK);
            canvas.save();
            canvas.scale(scalex, scaley, 0, 0);
            canvas.drawBitmap(background, 0, 0, paint);
            canvas.restore();
            canvas.drawBitmap(text, text_x, text_y, paint);
            if (isBtChange) {
                canvas.drawBitmap(button2, button_x, button_y, paint);
            } else {
                canvas.drawBitmap(button, button_x, button_y, paint);
            }
            if (isBtChange2) {
                canvas.drawBitmap(button2, button_x, button_y2, paint);
            } else {
                canvas.drawBitmap(button, button_x, button_y2, paint);
            }

            paint.setColor(Color.BLACK);
            canvas.drawText(startGame, screen_width / 2 - strwid / 2, button_y
                    + (float) button.getHeight() / 2 + strhei / 2 - 5, paint);
            canvas.drawText(exitGame, screen_width / 2 - strwid / 2, button_y2
                    + (float) button.getHeight() / 2 + strhei / 2 - 5, paint);
            paint.setColor(Color.rgb(235, 161, 1));
            canvas.drawText(version, screen_width / 2 - version_width / 2,
                    button_y / 2 + text_y / 2 + (float) text.getHeight() / 2, paint);

            canvas.save();
            canvas.clipRect(fly_x, fly_y, fly_x + planefly.getWidth(), fly_y + fly_height);
            canvas.drawBitmap(planefly, fly_x, fly_y - currentFrame * fly_height, paint);
            currentFrame++;
            if (currentFrame >= 3) {
                currentFrame = 0;
            }
            canvas.restore();
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (canvas != null)
                sfh.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void run() {
        while (threadFlag) {
            long startTime = System.currentTimeMillis();
            drawSelf();
            long endTime = System.currentTimeMillis();
            try {
                if (endTime - startTime < 400)
                    Thread.sleep(400 - (endTime - startTime));
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && event.getPointerCount() == 1) {
            float x = event.getX();
            float y = event.getY();
            if (x > button_x && x < button_x + button.getWidth()
                    && y > button_y && y < button_y + button.getHeight()) {
                sounds.playSound(7, 0);
                isBtChange = true;
                drawSelf();
                mainActivity.getHandler().sendEmptyMessage(ConstantUtil.TO_MAIN_VIEW);
            } else if (x > button_x && x < button_x + button.getWidth()
                    && y > button_y2 && y < button_y2 + button.getHeight()) {
                sounds.playSound(7, 0);
                isBtChange2 = true;
                drawSelf();
                mainActivity.getHandler().sendEmptyMessage(ConstantUtil.END_GAME);
            }
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX();
            float y = event.getY();
            isBtChange = x > button_x && x < button_x + button.getWidth()
                    && y > button_y && y < button_y + button.getHeight();
            isBtChange2 = x > button_x && x < button_x + button.getWidth()
                    && y > button_y2 && y < button_y2 + button.getHeight();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            isBtChange = false;
            isBtChange2 = false;
            return true;
        }
        return false;
    }
}
