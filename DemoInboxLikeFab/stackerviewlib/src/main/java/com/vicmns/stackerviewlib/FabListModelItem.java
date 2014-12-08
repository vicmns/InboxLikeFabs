package com.vicmns.stackerviewlib;

import android.graphics.drawable.Drawable;

/**
* Created by vicmns on 12/8/2014.
*/
public class FabListModelItem {
    public static final int FAB_NORMAL_TYPE = 0;
    public static final int FAB_SMALL_TYPE = 1;

    private int fabType;
    private String fabTag;
    private int fabResId = -1;
    private int fabBackgroundResId = -1;
    private Drawable fabResDrawable;
    private Drawable fabBackgroundDrawable;

    public FabListModelItem() {

    }

    public FabListModelItem(int fabType, String fabTag) {
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

    public static class Builder {
        private FabListModelItem fabListModelItem;

        public Builder(){
            fabListModelItem = new FabListModelItem();
        }

        public Builder setFabType(int fabType) {
            fabListModelItem.setFabType(fabType);
            return this;
        }

        public Builder setFabTag(String fabTag) {
            fabListModelItem.setFabTag(fabTag);
            return this;
        }

        public Builder setFabResourceId(int fabResId) {
            fabListModelItem.setFabResId(fabResId);
            return this;
        }

        public Builder setFabBackgroundResId(int fabBackgroundResId) {
            fabListModelItem.setFabBackgroundResId(fabBackgroundResId);
            return this;
        }

        public Builder setFabResDrawable(Drawable fabResDrawable) {
            fabListModelItem.setFabResDrawable(fabResDrawable);
            return this;
        }

        public Builder setFabBackgroundDrawable(Drawable fabBackgroundDrawable) {
            fabListModelItem.setFabBackgroundDrawable(fabBackgroundDrawable);
            return this;
        }

        public FabListModelItem build() {
            return fabListModelItem;
        }
    }
}
