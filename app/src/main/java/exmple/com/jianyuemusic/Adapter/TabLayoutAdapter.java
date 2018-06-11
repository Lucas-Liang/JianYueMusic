package exmple.com.jianyuemusic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by Admin on 2018/6/8.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {
    private String[] list_title;
    private List<Fragment>list_data;
    public TabLayoutAdapter(FragmentManager fm,String[] list_title,List<Fragment> list_data) {
        super(fm);
        this.list_title =list_title;
        this.list_data =list_data;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title[position];
    }

    @Override
    public Fragment getItem(int position) {
        return list_data.get(position);
    }

    @Override
    public int getCount() {
        return list_data.size();
    }
}
