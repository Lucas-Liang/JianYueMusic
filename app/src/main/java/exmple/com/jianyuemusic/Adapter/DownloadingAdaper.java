package exmple.com.jianyuemusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import exmple.com.jianyuemusic.Pojo.FileInfo;
import exmple.com.jianyuemusic.R;
import exmple.com.jianyuemusic.Tool.CommonPopupWindowTool;

import static android.support.constraint.Constraints.TAG;

/**
 * @Author: LKL
 * @Date: 2018/6/11 13:49
 * @CodeInfo:下载的ListView的是适配器
 */
public class DownloadingAdaper extends BaseAdapter {
    private View activityPopup;
    private TextView start_download;
    private TextView stop_download;
    private TextView delete_download;
    private TextView name_download;
    private CommonPopupWindowTool window;
    private Context context;
    private List<FileInfo> fileList;
    private LayoutInflater inflater;
    private Window getwindow;
    private PopupWindow win;

    public DownloadingAdaper(Context context, List<FileInfo> fileList,Window window) {
        this.context = context;
        this.getwindow =window;
        this.fileList = fileList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return fileList.size();
    }

    @Override
    public Object getItem(int position) {
        return fileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final FileInfo info = fileList.get(i);
        final ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.downloading_list_item, null);
            holder = new ViewHolder();
            holder.downlaod_name = (TextView) view.findViewById(R.id.downlaod_name);
            holder.downlaod_singer = (TextView) view.findViewById(R.id.downlaod_singer);
            holder.downlaod_popu = (ImageView) view.findViewById(R.id.downlaod_popu);
            holder.download_progressBar = (ProgressBar) view.findViewById(R.id.download_progressBar);
            activityPopup=view.findViewById(R.id.downlaoding_list_item_layout);
            holder.downlaod_name.setText(info.getFileName());
            holder.downlaod_singer.setText(info.getFileSinger());
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.downlaod_popu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+info.getFileName());
                initPopupWindow(info);
                win=window.getPopupWindow();
                win.setAnimationStyle(R.style.animTranslate);
                window.showAtLocation(activityPopup, Gravity.BOTTOM, 0, 0);
                WindowManager.LayoutParams lp=getwindow.getAttributes();
                lp.alpha=0.3f;
                getwindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getwindow.setAttributes(lp);
            }
        });
        int pro = (int) info.getFinish();
        holder.download_progressBar.setProgress(pro);

        return view;
    }
    /**
     * 更新下载列表的进度
     * @param id 文件的id
     * @param progress 进度
     */
    public void updateProgress(int id, long progress) {
        FileInfo fileInfo = fileList.get(id);
        fileInfo.setFinish(progress);
        notifyDataSetChanged();
    }
    class ViewHolder {
        TextView downlaod_name;
        TextView downlaod_singer;
        ImageView downlaod_popu;
        ProgressBar download_progressBar;
    }
    /**
     *PopupWindowCodeInfo
     *@author Admin
     *@time  14:43
     * @param info
     */
    private void initPopupWindow(final FileInfo info) {
        // get the height of screen
        View view = inflater.inflate(R.layout.popuwindows_download, null);
        LinearLayout linearlayout = (LinearLayout)view.findViewById(R.id.downlaod_popu_layout);
        linearlayout.measure(0,0);
        int width = linearlayout.getMeasuredWidth();
        int height = linearlayout.getMeasuredHeight();
        // create popup window
        window=new CommonPopupWindowTool(context, R.layout.popuwindows_download, ViewGroup.LayoutParams.MATCH_PARENT,height) {
            @Override
            protected void initView() {
                View view=getContentView();
                start_download=(TextView) view.findViewById(R.id.start_download);
                stop_download=(TextView) view.findViewById(R.id.stop_download);
                delete_download=(TextView) view.findViewById(R.id.delete_download);
                name_download=(TextView) view.findViewById(R.id.name_download);
                name_download.setText("当前选择: "+info.getFileName());

            }

            @Override
            protected void initEvent() {
                start_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, "onClick:  开始下载  "+info.getFileName());
                        window.getPopupWindow().dismiss();

                    }
                });
                stop_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, "onClick:  停止下载  "+info.getFileName());
                        window.getPopupWindow().dismiss();

                    }
                });
                delete_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, "onClick:  删除下载  "+info.getFileName());
                        window.getPopupWindow().dismiss();

                    }
                });

            }
            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance=getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp=getwindow.getAttributes();
                        lp.alpha=1.0f;
                        getwindow.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getwindow.setAttributes(lp);
                    }
                });
            }
        };
    }

}
