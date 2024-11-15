package com.example.stormplanegame.plane;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.stormplanegame.R;
import com.example.stormplanegame.constant.GameConstant;

import java.util.Random;

public class SmallPlane extends EnemyPlane {
    private static int currentCount = 0;
    private Bitmap smallPlane;
    public static int sumCount = GameConstant.SMALLPLANE_COUNT;

    public SmallPlane(Resources resources) {
        super(resources);
        initBitmap();
        this.score = GameConstant.SMALLPLANE_SCORE;
    }

    @Override
    public void initial(int arg0, float arg1, float arg2) {
        super.initial(arg0, arg1, arg2);
        isAlive = true;
        bloodVolume = GameConstant.SMALLPLANE_BLOOD;
        blood = bloodVolume;
        Random ran = new Random();

        speed = 6 * ran.nextInt(3) + 19;

        object_x = ran.nextInt((int) (screen_width - object_width));
        object_y = -object_height * (currentCount * 2 + 1);
        currentCount++;
        if (currentCount >= sumCount) {
            currentCount = 0;
        }
    }

    @Override
    public void initBitmap() {
        smallPlane = BitmapFactory.decodeResource(resources, R.drawable.small);
        object_width = smallPlane.getWidth();
        object_height = (float) smallPlane.getHeight() / 3;
    }

    @Override
    public void drawSelf(Canvas canvas) {
        if (isAlive) {
            if (!isExplosion) {
                if (isVisible) {
                    canvas.save();
                    canvas.clipRect(object_x, object_y,
                            object_x + object_width, object_y + object_height);
                    canvas.drawBitmap(smallPlane, object_x, object_y, paint);
                    canvas.restore();
                }
                logic();
            } else {
                int y = (int) (currentFrame * object_height);
                canvas.save();
                canvas.clipRect(object_x, object_y, object_x + object_width,
                        object_y + object_height);
                canvas.drawBitmap(smallPlane, object_x, object_y - y, paint);
                canvas.restore();
                currentFrame++;
                if (currentFrame >= 3) {
                    currentFrame = 0;
                    isExplosion = false;
                    isAlive = false;
                }
            }
        }
    }

    @Override
    public void release() {
        if (!smallPlane.isRecycled()) {
            smallPlane.recycle();
        }
    }

    public void logic() {
        Random random = new Random();

        if (object_y < screen_height) {

            object_y += speed;
            object_x += (float) (20 * speedTime * Math.sin(object_y));

        } else {
            isAlive = false;
        }

        isVisible = object_y + object_height > 0;
    }
}
