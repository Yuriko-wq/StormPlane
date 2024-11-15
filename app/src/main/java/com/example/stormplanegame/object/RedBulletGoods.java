package com.example.stormplanegame.object;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.stormplanegame.R;

public class RedBulletGoods extends GameGoods {

    public RedBulletGoods(Resources resources) {
        super(resources);
    }

    protected void initBitmap() {
        bmp = BitmapFactory.decodeResource(resources, R.drawable.bullet_goods2);
        object_width = bmp.getWidth();
        object_height = bmp.getHeight();
    }
}

