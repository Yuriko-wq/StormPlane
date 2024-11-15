package com.example.stormplanegame.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.stormplanegame.MainActivity;
import com.example.stormplanegame.sounds.GameSoundPool;

@SuppressLint("ViewConstructor")
public class BaseView extends SurfaceView implements SurfaceHolder.Callback, Runnable{
    protected int currentFrame;
    protected float scalex;
    protected float scaley;
    protected float screen_width;
    protected float screen_height;
    protected boolean threadFlag;
    protected Paint paint;
    protected Canvas canvas;
    protected Thread thread;
    protected SurfaceHolder sfh;
    protected GameSoundPool sounds;
    protected MainActivity mainActivity;

    public BaseView(Context context, GameSoundPool sounds) {
        super(context);
        this.sounds = sounds;
        this.mainActivity = (MainActivity) context;
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
    }

    @Override
    public void run() {

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        screen_width = this.getWidth();
        screen_height = this.getHeight();
        threadFlag = true;
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        threadFlag = false;
    }

    public void initBitmap() {
    }

    public void release() {
    }

    public void drawSelf() {
    }

    public void setThreadFlag(boolean threadFlag) {
        this.threadFlag = threadFlag;
    }
}
