package com.example.stormplanegame.object;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.example.stormplanegame.R;

public class PurpleBulletGoods extends GameGoods{
    public PurpleBulletGoods(Resources resources) {
        super(resources);
    }
    @Override
    protected void initBitmap() {
        bmp = BitmapFactory.decodeResource(resources, R.drawable.bullet_goods1);
        object_width = bmp.getWidth();
        object_height = bmp.getHeight();
    }
}
