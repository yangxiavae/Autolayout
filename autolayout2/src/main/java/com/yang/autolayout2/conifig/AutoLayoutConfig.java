package com.yang.autolayout2.conifig;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class AutoLayoutConfig {
    private final static String DESIGN_WIDTH = "design_width";
    private final static String DESIGN_HEIGHT="design_height";
    //自身屏幕大小
    private int mScreen_width;
    private int mScreen_height;
    //设计屏幕大小
    private int mDesign_width;
    private int mDesign_height;

    private static AutoLayoutConfig config=new AutoLayoutConfig();

    public AutoLayoutConfig() {
    }
    public static AutoLayoutConfig getInstance(){
        if(config==null){
            synchronized (config){
                if(config==null){
                    config=new AutoLayoutConfig();
                }
            }
        }
        return config;
    }
    private void init(Context context){
        //确定设计屏幕大小
        getMetaData(context);
        //获取适配手机大小

    }

    private void getMetaData(Context context){
        PackageManager manager=context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo=manager.getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
            if(applicationInfo!=null&&applicationInfo.metaData!=null){
                mDesign_width= (int) applicationInfo.metaData.get(DESIGN_WIDTH);
                mDesign_height= (int) applicationInfo.metaData.get(DESIGN_HEIGHT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(
                    "you must set " + DESIGN_WIDTH + " and " + DESIGN_HEIGHT + "  in your manifest file.", e);
        }

    }
}
