package com.yang.autolayout2.conifig;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.yang.autolayout2.utils.ScreenUtil;

public class AutoLayoutConfig {
    private final static String DESIGN_WIDTH = "design_width";
    private final static String DESIGN_HEIGHT = "design_height";
    //自身屏幕大小
    private int mScreen_width;
    private int mScreen_height;
    //设计屏幕大小
    private int mDesign_width;
    private int mDesign_height;


    //
    private boolean useDeviceSize = false;

    private static AutoLayoutConfig config = new AutoLayoutConfig();

    public AutoLayoutConfig() {
    }

    public static AutoLayoutConfig getInstance() {
        if (config == null) {
            synchronized (config) {
                if (config == null) {
                    config = new AutoLayoutConfig();
                }
            }
        }
        return config;
    }



    public void init(Context context) {
        //确定设计屏幕大小
        getMetaData(context);
        //获取适配手机大小
        int[] screenSetting = ScreenUtil.getScreenSize(context, useDeviceSize);
        mScreen_width = screenSetting[0];
        mDesign_height = screenSetting[1];

    }

    public void checkParams(){
        if(mScreen_width<0||mScreen_height<0){
            throw new RuntimeException("\"you must set \" + KEY_DESIGN_WIDTH + \" and \" + KEY_DESIGN_HEIGHT + \"  in your manifest file.\"");
        }
    }


    private void getMetaData(Context context) {
        PackageManager manager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = manager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                mDesign_width = (int) applicationInfo.metaData.get(DESIGN_WIDTH);
                mDesign_height = (int) applicationInfo.metaData.get(DESIGN_HEIGHT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(
                    "you must set " + DESIGN_WIDTH + " and " + DESIGN_HEIGHT + "  in your manifest file.", e);
        }

    }

    public  AutoLayoutConfig useDeviceSize() {
        useDeviceSize=true;
        return this;
    }

    public int getmScreen_width() {
        return mScreen_width;
    }

    public int getmScreen_height() {
        return mScreen_height;
    }

    public int getmDesign_width() {
        return mDesign_width;
    }

    public int getmDesign_height() {
        return mDesign_height;
    }

}
