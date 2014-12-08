package com.vicmns.stackerviewlib;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
* Created by vicmns on 12/8/2014.
*/
public class FabStackerItem {
    public static final int FAB_NORMAL_TYPE = 0;
    public static final int FAB_SMALL_TYPE = 1;

    private int fabType;
    private String fabTag;
    private int fabResId = -1;
    private int fabBackgroundResId = -1;
    private Drawable fabResDrawable;
    private Drawable fabBackgroundDrawable;
    private View.OnClickListener fabClickListener;

    public FabStackerItem() {

    }

    public FabStackerItem(int fabType, String fabTag) {
        this.fabType = fabType;
        this.fabTag = fabTag;
    }

    public int getFabType() {
        return fabType;
    }

    public void setFabType(int fabType) {
        this.fabType = fabType;
    }

    public String getFabTag() {
        return fabTag;
    }

    public void setFabTag(String fabTag) {
        this.fabTag = fabTag;
    }

    public int getFabResId() {
        return fabResId;
    }

    public void setFabResId(int fabResId) {
        this.fabResId = fabResId;
    }

    public int getFabBackgroundResId() {
        return fabBackgroundResId;
    }

    public void setFabBackgroundResId(int fabBackgroundResId) {
        this.fabBackgroundResId = fabBackgroundResId;
    }

    public Drawable getFabResDrawable() {
        return fabResDrawable;
    }

    public void setFabResDrawable(Drawable fabResDrawable) {
        this.fabResDrawable = fabResDrawable;
    }

    public Drawable getFabBackgroundDrawable() {
        return fabBackgroundDrawable;
    }

    public void setFabBackgroundDrawable(Drawable fabBackgroundDrawable) {
        this.fabBackgroundDrawable = fabBackgroundDrawable;
    }

    public View.OnClickListener getFabClickListener() {
        return fabClickListener;
    }

    public void setFabClickListener(View.OnClickListener fabClickListener) {
        this.fabClickListener = fabClickListener;
    }

    public static class Builder {
        private FabStackerItem fabStackerItem;

        public Builder(){
            fabStackerItem = new FabStackerItem();
        }

        public Builder setFabType(int fabType) {
            fabStackerItem.setFabType(fabType);
            return this;
        }

        public Builder setFabTag(String fabTag) {
            fabStackerItem.setFabTag(fabTag);
            return this;
        }

        public Builder setFabResourceId(int fabResId) {
            fabStackerItem.setFabResId(fabResId);
            return this;
        }

        public Builder setFabBackgroundResId(int fabBackgroundResId) {
            fabStackerItem.setFabBackgroundResId(fabBackgroundResId);
            return this;
        }

        public Builder setFabResDrawable(Drawable fabResDrawable) {
            fabStackerItem.setFabResDrawable(fabResDrawable);
            return this;
        }

        public Builder setFabBackgroundDrawable(Drawable fabBackgroundDrawable) {
            fabStackerItem.setFabBackgroundDrawable(fabBackgroundDrawable);
            return this;
        }

        public Builder setFabClickListener(View.OnClickListener fabClickListener) {
            fabStackerItem.setFabClickListener(fabClickListener);
            return this;
        }

        public FabStackerItem build() {
            return fabStackerItem;
        }
    }
}
