package exmple.com.jianyuemusic.Tool;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exmple.com.jianyuemusic.R;

/**
 * @Author: LKL
 * @Date: 2018/6/4 16:01
 * @CodeInfo:批量获取权限
 */
public class PermissionTool extends Activity {
    public   List<String> needPermission;
    public final int REQUEST_CODE_PERMISSION = 0;
    public Context context;
    public String[] permission;
    public Dialog dialog=null;

    public final String[] permissionArray = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };
    /**
     * @Author: LKL
     * @Date: 2018/6/4 16:32
     * @CodeInfo:获取所有的权限
     */
    public class AllPermission extends AppCompatActivity{
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            askMultiplePermission();
        }
        public void askMultiplePermission() {
            needPermission = new ArrayList<>();
            for (String permissionName : permissionArray) {
                if (!checkIsAskPermission(this, permissionName)) {
                    needPermission.add(permissionName);
                }
            }
            if (needPermission.size() > 0) {
                //开始申请权限
                ActivityCompat.requestPermissions(this, needPermission
                        .toArray(new String[needPermission.size()]), REQUEST_CODE_PERMISSION);
            } else {
            //获取数据
            }
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode) {
                case REQUEST_CODE_PERMISSION:
                    Map<String, Integer> permissionMap = new HashMap<>();
                    for (String name : needPermission) {
                        permissionMap.put(name, PackageManager.PERMISSION_GRANTED);
                    }
                    for (int i = 0; i < permissions.length; i++) {
                        permissionMap.put(permissions[i], grantResults[i]);
                    }
                    if (checkIsAskPermissionState(permissionMap, permissions)) {
//获取数据
                    } else {
//提示权限获取不完成，可能有的功能不能使用
                    }
                    break;
            }
        }
        public  boolean checkIsAskPermission(Context context, String permission) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        }
        public boolean checkIsAskPermissionState(Map<String, Integer> maps, String[] list) {
            for (int i = 0; i < list.length; i++) {
                if (maps.get(list[i]) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            } return true;
        }
    };
    /**
     * @Author: LKL
     * @Date: 2018/6/4 16:38
     * @CodeInfo:检查权限是否被申请
     */
    public boolean getPermissionState(String Permission[],Context con) {
        this.permission = Permission;
        boolean rs = false;
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(context, permission[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                rs = true;
             return rs;
            }
        }
        return rs;
    }
    // 开始提交请求权限
    public void showDialogTipUserRequestPermission(String Permission[],Context con) {
        this.permission = Permission;
        this.context = con;

        new AlertDialog.Builder(context)
                .setTitle("权限不可用")
                .setMessage("由于简悦音乐播放器需要从网络上下载音乐和从本地下载音乐，我们将要调相关的权限。")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRequestPermission();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setCancelable(false).show();
    }

    private void startRequestPermission() {
            ActivityCompat.requestPermissions(this, permission, 100);
        }
    // 用户权限申请的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        showDialogTipUserGoToAppSettting();
                    } else
                        finish();
                } else {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    // 提示用户去应用设置界面手动开启权限
    private void showDialogTipUserGoToAppSettting() {
        dialog = new AlertDialog.Builder(this)
                .setTitle("权限不可用")
                .setMessage("请在-应用设置-权限-中，允许简悦音乐使用相关的权限")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到应用设置界面
                        goToAppSetting();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setCancelable(false).show();
        }
        // 跳转到当前应用的设置界面
        private void goToAppSetting() {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permission[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    // 提示用户应该去应用设置界面手动开启权限
                    showDialogTipUserGoToAppSettting();
                } else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}