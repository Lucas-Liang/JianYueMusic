package exmple.com.jianyuemusic.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import exmple.com.jianyuemusic.Adapter.DownloadingAdaper;
import exmple.com.jianyuemusic.Pojo.FileInfo;
import exmple.com.jianyuemusic.R;

/**
 * Created by Admin on 2018/6/6.
 */

public class Downloading extends Fragment implements View.OnClickListener {
    private ListView downlaoding_listview;
    private LinearLayout start_all;
    private LinearLayout stop_all;
    private List<FileInfo> filelist;
    private DownloadingAdaper downloadingAdaper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.downloading, container, false);
        filelist =  getDownloadInfo();
        initView(view);
        return view;
    }

   private List<FileInfo> getDownloadInfo(){
        List<FileInfo> list = new ArrayList<>();
        for (int i =0;i<5;i++){
            FileInfo fileInfo =new FileInfo();
            fileInfo.setFileName("name"+i);
            fileInfo.setFileSinger("name"+i);
            fileInfo.setFinish((35+i*5));
            fileInfo.setId(i);
            fileInfo.setLength((35+i*5));
            fileInfo.setUrl("234567890"+i);
            list.add(fileInfo);
        }
        return list;
   }

    private void initView(View view) {
        downlaoding_listview = (ListView) view.findViewById(R.id.downlaoding_listview);
        downloadingAdaper =new DownloadingAdaper(getContext(),filelist,getActivity().getWindow());
        downlaoding_listview.setAdapter(downloadingAdaper);
        start_all = (LinearLayout) view.findViewById(R.id.start_all);
        start_all.setOnClickListener(this);
        stop_all = (LinearLayout) view.findViewById(R.id.stop_all);
        stop_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_all:
                break;
            case R.id.stop_all:
                break;
        }
    }
}
