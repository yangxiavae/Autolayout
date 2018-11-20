package com.yang.autolayout2.utils;

import android.view.View;

import com.yang.autolayout2.AutoLayoutInfo;
import com.yang.autolayout2.R;
import com.yang.autolayout2.attr.Attrs;
import com.yang.autolayout2.attr.AutoAttr;
import com.yang.autolayout2.conifig.AutoLayoutConfig;

public class AutoUtils {
    /**
     * 会直接将view的LayoutParams上设置的width，height直接进行百分比处理
     *
     * @param view
     */
    public static void auto(View view) {
        autoSize(view);
        autoPadding(view);
        autoMargin(view);
        autoTextSize(view, AutoAttr.BASE_DEFAULT);
    }

    private static void auto(View view, int attrs, int base) {
        AutoLayoutInfo autoLayoutInfo = AutoLayoutInfo.getAttrFromView(view, attrs, base);
        if (autoLayoutInfo != null) {
            autoLayoutInfo.fillAttrs(view);
        }
    }

    private static void autoPadding(View view) {
        auto(view, Attrs.PADDING, AutoAttr.BASE_DEFAULT);
    }

    private static void autoSize(View view) {
        auto(view, Attrs.WIDTH|Attrs.HEIGHT, AutoAttr.BASE_DEFAULT);
    }

    private static void autoMargin(View view) {
        auto(view, Attrs.MARGIN, AutoAttr.BASE_DEFAULT);
    }

    private static void autoTextSize(View view) {
        auto(view, Attrs.TEXTSIZE, AutoAttr.BASE_DEFAULT);
    }

    private static void autoPadding(View view, int base) {
        auto(view, Attrs.PADDING, base);
    }

    private static void autoSize(View view, int base) {
        auto(view, Attrs.WIDTH|Attrs.HEIGHT,base);
    }

    private static void autoMargin(View view, int base) {
        auto(view, Attrs.MARGIN, base);
    }

    private static void autoTextSize(View view, int base) {
        auto(view, Attrs.TEXTSIZE, base);
    }
    //判断是否适配
    public static boolean autoed(View view)
    {
        Object tag = view.getTag(R.id.id_tag_autolayout_size);
        if (tag != null) return true;
        view.setTag(R.id.id_tag_autolayout_size, "Just Identify");
        return false;
    }

    private static float getPercentHeight(){
        int design_height=AutoLayoutConfig.getInstance().getmDesign_height();
        int screen_height=AutoLayoutConfig.getInstance().getmScreen_height();
        return 1.0f*screen_height/design_height;
    }

    private static float getPercentWidth(){
        int design_width=AutoLayoutConfig.getInstance().getmDesign_width();
        int screen_width=AutoLayoutConfig.getInstance().getmScreen_width();
        return  1.0f*screen_width/design_width;
    }
    private static float getPercentHeightSize(int size){
        int design_height=AutoLayoutConfig.getInstance().getmDesign_height();
        int screen_height=AutoLayoutConfig.getInstance().getmScreen_height();
        return size*1.0f*screen_height/design_height;
    }
    private static float getPercentWidthSize(int size){
        int design_width=AutoLayoutConfig.getInstance().getmDesign_width();
        int screen_width=AutoLayoutConfig.getInstance().getmScreen_width();
        return  size*1.0f*screen_width/design_width;
    }

    public static int getPercentWidthSizeBigger(int val)
    {
        int screenWidth = AutoLayoutConfig.getInstance().getmScreen_width();
        int designWidth = AutoLayoutConfig.getInstance().getmDesign_width();

        int res = val * screenWidth;
        if (res % designWidth == 0)
        {
            return res / designWidth;
        } else
        {
            return res / designWidth + 1;
        }

    }

    public static int getPercentHeightSizeBigger(int val)
    {
        int screenHeight = AutoLayoutConfig.getInstance().getmScreen_height();
        int designHeight = AutoLayoutConfig.getInstance().getmDesign_height();

        int res = val * screenHeight;
        if (res % designHeight == 0)
        {
            return res / designHeight;
        } else
        {
            return res / designHeight + 1;
        }
    }
}
