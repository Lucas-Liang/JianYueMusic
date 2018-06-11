package exmple.com.jianyuemusic.UI;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

import exmple.com.jianyuemusic.Adapter.TabLayoutAdapter;
import exmple.com.jianyuemusic.Fragment.Downloaded;
import exmple.com.jianyuemusic.Fragment.Downloading;
import exmple.com.jianyuemusic.R;
import exmple.com.jianyuemusic.Tool.MainBGActivity;


public class DownloadActivity extends MainBGActivity {
    private String[] list_title = {"正在下载", "已下载"};
    private Toolbar title_toolbar;
    private TextView title_toolbar_title;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private TabLayoutAdapter tabLayoutAdapter;
    private XTabLayout download_tabyout_title;
    private ViewPager download_viewpage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();

    }

    private void initView() {
        title_toolbar = (Toolbar) findViewById(R.id.title_toolbar);
        title_toolbar.inflateMenu(R.menu.title_menu);
        title_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                switch (menuItemId) {
                    case R.id.action_seare: {
                        Log.i("--->", "搜索按钮！！！");
                        break;
                    }
                }
                return false;
            }
        });
        title_toolbar_title = (TextView) findViewById(R.id.title_toolbar_title);
        title_toolbar_title.setText(R.string.DownloadText);
        download_tabyout_title = (XTabLayout) findViewById(R.id.download_tabyout_title);
        download_viewpage = (ViewPager) findViewById(R.id.download_viewpage);
        fragmentList.add(new Downloading());
        fragmentList.add(new Downloaded());
        tabLayoutAdapter = new TabLayoutAdapter(getSupportFragmentManager(), list_title, fragmentList);
        download_viewpage.setAdapter(tabLayoutAdapter);
        download_tabyout_title.setupWithViewPager(download_viewpage);
        download_tabyout_title.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                tab.getPosition();
                // 默认切换的时候，会有一个过渡动画，设为false后，取消动画，直接显示
                download_viewpage.setCurrentItem(tab.getPosition(), false);
                download_viewpage.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
            }
        });

    }

}
