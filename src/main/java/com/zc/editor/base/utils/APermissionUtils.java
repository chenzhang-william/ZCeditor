package com.zc.editor.base.utils;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;


public class APermissionUtils {

    public static final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
    };

    /**
     * 检测权限
     * @param activity
     */
    public static void checkPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, BASIC_PERMISSIONS, 1);
        // 之后再完善权限请求
        /*for (String basicPermission : BASIC_PERMISSIONS) {
            Log.e("wer", "checkPermission: " );
            if (ContextCompat.checkSelfPermission(activity, basicPermission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{basicPermission}, 1);
            }
        }*/
    }
}
