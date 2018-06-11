package exmple.com.jianyuemusic.UI;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.androidkun.xtablayout.XTabLayout;

import exmple.com.jianyuemusic.R;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager User_Viewpage;
    private RadioButton User_sign;
    private RelativeLayout User_Title;
    private XTabLayout xTablayout;
    private String[] title ={"登录","注册"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initView() {
        User_Viewpage = (ViewPager) findViewById(R.id.User_Viewpage);
        User_sign = (RadioButton) findViewById(R.id.User_sign);
       /* tablayout.addTab(tablayout.newTab().setText("登录"));
        tablayout.addTab(tablayout.newTab().setText("注册"));
        tablayout.setOnClickListener(this);*/
        xTablayout = (XTabLayout) findViewById(R.id.xTablayout);
        xTablayout.addTab(xTablayout.newTab().setText("登录"));
        xTablayout.addTab(xTablayout.newTab().setText("注册"));
        xTablayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

    }
}
