package exmple.com.jianyuemusic.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import exmple.com.jianyuemusic.R;

/**
 * Created by Admin on 2018/6/6.
 */

public class Downloaded extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.downloading,container,false);
        return view;
    }
}
