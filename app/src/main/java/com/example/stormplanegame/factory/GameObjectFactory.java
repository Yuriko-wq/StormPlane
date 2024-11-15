package com.example.stormplanegame.factory;

import android.content.res.Resources;

import com.example.stormplanegame.bullet.*;
import com.example.stormplanegame.object.*;
import com.example.stormplanegame.plane.*;

public class GameObjectFactory {
    public GameObject createSmallPlane(Resources resources) {
        return new SmallPlane(resources);
    }

    public GameObject createMiddlePlane(Resources resources) {
        return new MiddlePlane(resources);
    }

    public GameObject createBigPlane(Resources resources) {
        return new BigPlane(resources);
    }

    public GameObject createBossPlane(Resources resources) {
        return new BossPlane(resources);
    }

    public GameObject createMyPlane(Resources resources) {
        return new MyPlane(resources);
    }

    public GameObject createMyBlueBullet(Resources resources) {
        return new MyBlueBullet(resources);
    }
    public GameObject createMyPurpleBullet(Resources resources) {
        return new MyPurpleBullet(resources);
    }
    public GameObject createMyRedBullet(Resources resources) {
        return new MyRedBullet(resources);
    }

    public GameObject createBossFlameBullet(Resources resources) {
        return new BossFlameBullet(resources);
    }

    public GameObject createBossSunBullet(Resources resources) {
        return new BossSunBullet(resources);
    }

    public GameObject createBossTriangleBullet(Resources resources) {
        return new BossTriangleBullet(resources);
    }

    public GameObject createBossGThunderBullet(Resources resources) {
        return new BossGThunderBullet(resources);
    }

    public GameObject createBossYHellfireBullet(Resources resources) {
        return new BossYHellfireBullet(resources);
    }

    public GameObject createBossRHellfireBullet(Resources resources) {
        return new BossRHellfireBullet(resources);
    }

    public GameObject createBossBulletDefault(Resources resources) {
        return new BossDefaultBullet(resources);
    }

    public GameObject createBigPlaneBullet(Resources resources) {
        return new BigPlaneBullet(resources);
    }

    public GameObject createMissileGoods(Resources resources) {
        return new MissileGoods(resources);
    }

    public GameObject createLifeGoods(Resources resources) {
        return new LifeGoods(resources);
    }

    public GameObject createPurpleBulletGoods(Resources resources) {
        return new PurpleBulletGoods(resources);
    }

    public GameObject createRedBulletGoods(Resources resources) {
        return new RedBulletGoods(resources);
    }
}
