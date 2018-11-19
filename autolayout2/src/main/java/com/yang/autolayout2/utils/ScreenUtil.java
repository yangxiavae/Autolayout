package com.yang.autolayout2.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;

public class ScreenUtil {


    public static int[] getScreenSize(Context context, boolean useDeviceSize) {
        int[] size = new int[2];
        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.ICE_CREAM_SANDWICH&&Build.VERSION.SDK_INT<Build.VERSION_CODES.JELLY_BEAN_MR1){
            try {
                widthPixels= (int) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels= (int) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        // includes window decorations (statusbar bar/menu bar)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            Point point=new Point();
            try {
                Display.class.getMethod("getRealSize", Point.class).invoke(point);
                widthPixels=point.x;
                heightPixels=point.y;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        size[0] =widthPixels;
        size[1] = heightPixels;

        return size;
    }
}
