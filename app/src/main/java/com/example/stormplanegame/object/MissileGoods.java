package com.example.stormplanegame.object;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.stormplanegame.R;

public class MissileGoods extends GameGoods {
    public MissileGoods(Resources resources) {
        super(resources);
    }

    @Override
    protected void initBitmap() {
        bmp = BitmapFactory.decodeResource(resources, R.drawable.missile_goods);
        object_width = bmp.getWidth();
        object_height = bmp.getHeight();
    }
}