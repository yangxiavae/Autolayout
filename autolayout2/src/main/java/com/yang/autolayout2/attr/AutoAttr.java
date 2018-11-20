package com.yang.autolayout2.attr;

import android.view.View;

public class AutoAttr {
    public static final int BASE_WIDTH = 1;
    public static final int BASE_HEIGHT = 2;
    public static final int BASE_DEFAULT = 3;

    protected int pxVal;
    protected int baseWidth;
    protected int baseHeight;

    public AutoAttr(int pxVal, int baseWidth, int baseHeight) {
        this.pxVal = pxVal;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
    }

    public void apply(View view){
        boolean isApply=view.getTag()!=null&&view.getTag().toString().equals("auto");

    }
}
