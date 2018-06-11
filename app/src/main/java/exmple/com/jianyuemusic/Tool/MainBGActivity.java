package exmple.com.jianyuemusic.Tool;

import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import exmple.com.jianyuemusic.R;

/**
 * Created by Admin on 2018/6/6.
 */

public class MainBGActivity extends AppCompatActivity{
    private static View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setColor();
    }

    private void setColor(){
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                if (isStatusBar()) {
                    initStatusBar();
                    getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        @Override
                        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                            initStatusBar();
                        }
                    });
                }
                //只走一次
                return false;
            }
        });
    }
    private void initStatusBar() {
        if (view == null) {
            int identifier = getResources().getIdentifier("statusBarBackground", "id", "android");
            view = getWindow().findViewById(identifier);
        }
        if (view != null) {
            view.setBackgroundResource(R.drawable.main_title_color);
        }
    }
    protected boolean isStatusBar() {
        return true;
    }
}
