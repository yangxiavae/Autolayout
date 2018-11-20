package com.yang.autolayout2;

import android.view.View;
import android.view.ViewGroup;

import com.yang.autolayout2.attr.Attrs;
import com.yang.autolayout2.attr.AutoAttr;

import java.util.ArrayList;
import java.util.List;

public class AutoLayoutInfo {
    private List<AutoAttr> autoAttrs=new ArrayList<>();

    //添加view至autoAttrs
    public void addAttr(AutoAttr autoAttr){
        autoAttrs.add(autoAttr);
    }

    //对autoAttr添加属性
    public void fillAttrs(View view){
        for (AutoAttr autoAttr : autoAttrs) {
            autoAttr.apply(view);
        }
    }

    public static AutoLayoutInfo getAttrFromView(View view, int attrs, int base){
        ViewGroup.LayoutParams params=view.getLayoutParams();
        if(params==null) return null;
        AutoLayoutInfo autoLayoutInfo=new AutoLayoutInfo();

        //width&&height
        if((attrs& Attrs.WIDTH)!=0&params.width>0){
            //autoLayoutInfo.addAttr();
        }
        if((attrs&Attrs.HEIGHT)!=0&params.height>0){
            //autoLayoutInfo.addAttr();
        }

        //margin


        return autoLayoutInfo;
    }

    @Override
    public String toString() {
       return "AutoLayoutInfo{" +
                "autoAttrs=" + autoAttrs +
                '}';
    }
}
