/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yang.autolayout2.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.yang.autolayout2.AutoLayoutInfo;
import com.yang.autolayout2.R;
import com.yang.autolayout2.conifig.AutoLayoutConfig;


public class AutoLayoutHelper {
    private final ViewGroup mHost;

    private static final int[] LL = new int[]
            { //
                    android.R.attr.textSize,
                    android.R.attr.padding,//
                    android.R.attr.paddingLeft,//
                    android.R.attr.paddingTop,//
                    android.R.attr.paddingRight,//
                    android.R.attr.paddingBottom,//
                    android.R.attr.layout_width,//
                    android.R.attr.layout_height,//
                    android.R.attr.layout_margin,//
                    android.R.attr.layout_marginLeft,//
                    android.R.attr.layout_marginTop,//
                    android.R.attr.layout_marginRight,//
                    android.R.attr.layout_marginBottom,//
                    android.R.attr.paddingStart,
                    android.R.attr.paddingEnd,
                    android.R.attr.layout_marginStart,
                    android.R.attr.layout_marginEnd,
                    android.R.attr.maxWidth,//
                    android.R.attr.maxHeight,//
                    android.R.attr.minWidth,//
                    android.R.attr.minHeight,//16843072


            };

    private static final int INDEX_TEXT_SIZE = 0;
    private static final int INDEX_PADDING = 1;
    private static final int INDEX_PADDING_LEFT = 2;
    private static final int INDEX_PADDING_TOP = 3;
    private static final int INDEX_PADDING_RIGHT = 4;
    private static final int INDEX_PADDING_BOTTOM = 5;
    private static final int INDEX_WIDTH = 6;
    private static final int INDEX_HEIGHT = 7;
    private static final int INDEX_MARGIN = 8;
    private static final int INDEX_MARGIN_LEFT = 9;
    private static final int INDEX_MARGIN_TOP = 10;
    private static final int INDEX_MARGIN_RIGHT = 11;
    private static final int INDEX_MARGIN_BOTTOM = 12;
    private static final int INDEX_MAX_WIDTH = 13;
    private static final int INDEX_MAX_HEIGHT = 14;
    private static final int INDEX_MIN_WIDTH = 15;
    private static final int INDEX_MIN_HEIGHT = 16;
    private static final int INDEX_PADDING_START = 17;
    private static final int INDEX_PADDING_END = 18;
    private static final int INDEX_MARGIN_START = 19;
    private static final int INDEX_MARGIN_END = 20;


    /**
     * move to other place?
     */
    private static AutoLayoutConfig mAutoLayoutConfig;

    public AutoLayoutHelper(ViewGroup host) {
        mHost = host;

        if (mAutoLayoutConfig == null) {
            initAutoLayoutConfig(host);
        }

    }

    private void initAutoLayoutConfig(ViewGroup host) {
        mAutoLayoutConfig = AutoLayoutConfig.getInstance();
        mAutoLayoutConfig.init(host.getContext());
    }

    //适配子view
    public void adjustChildView() {
        AutoLayoutConfig.getInstance().checkParams();
        for (int i = 0; i < mHost.getChildCount(); i++) {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = mHost.getLayoutParams();
            if(params instanceof AutoLayoutParams){
                AutoLayoutInfo autoLayoutInfo=((AutoLayoutParams) params).getAutoLayoutInfo();
                if(autoLayoutInfo!=null){
                    autoLayoutInfo.fillAttrs(view);
                }
            }

        }
    }
    public static AutoLayoutInfo  getAutoLayoutInfo(Context context,AttributeSet attributeSet){
        AutoLayoutInfo autoLayoutInfo=new AutoLayoutInfo();
        TypedArray a=context.obtainStyledAttributes(attributeSet, R.styleable.Auto_Layout);

        return autoLayoutInfo;
    }
     public interface AutoLayoutParams
    {
        AutoLayoutInfo getAutoLayoutInfo();
    }

}
