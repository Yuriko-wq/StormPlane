package com.example.stormplanegame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.stormplanegame.constant.ConstantUtil;
import com.example.stormplanegame.sounds.GameSoundPool;
import com.example.stormplanegame.view.EndView;
import com.example.stormplanegame.view.MainView;
import com.example.stormplanegame.view.ReadyView;

public class MainActivity extends AppCompatActivity {
    private EndView endView;
    private MainView mainView;
    private ReadyView readyView;
    private GameSoundPool sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sounds = new GameSoundPool(this);
        sounds.initGameSound();

        readyView = new ReadyView(this, sounds);
        setContentView(readyView);
    }

    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == ConstantUtil.TO_MAIN_VIEW) {
                toMainView();
            } else if (msg.what == ConstantUtil.TO_END_VIEW) {
                toEndView(msg.arg1);
            } else if (msg.what == ConstantUtil.END_GAME) {
                endGame();
            }
        }
    };

    private void endGame() {
        if (readyView != null) {
            readyView.setThreadFlag(false);
        } else if (mainView != null) {
            mainView.setThreadFlag(false);
        } else if (endView != null) {
            endView.setThreadFlag(false);
        }
        this.finish();
    }

    private void toEndView(int score) {
        if (endView == null) {
            endView = new EndView(this, sounds);
            endView.setScore(score);
        }
        setContentView(endView);
        mainView = null;
    }

    private void toMainView() {
        if (mainView == null) {
            mainView = new MainView(this, sounds);
        }
        setContentView(mainView);
        readyView = null;
        endView = null;
    }

    public Handler getHandler() {
        return handler;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}