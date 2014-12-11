/*
 * Copyright 2014, The Android Open Source Project
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

package com.vicmns.stackerviewlib;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vicmns.myapplication.R;

/**
 * A Floating Action Button is a {@link android.widget.Checkable} view distinguished by a circled
 * icon floating above the UI, with special motion behaviors.
 */
public class FloatingActionButton extends FrameLayout implements Checkable {
    private static final int NORMAL_SIZE = 0;
    private static final int SMALL_SIZE = 1;

    private Context mContext;
    private int mFabSize;

    private ImageView mImageView;
    private Drawable mImageViewDrawable;

    /**
     * Interface definition for a callback to be invoked when the checked state
     * of a compound button changes.
     */
    public static interface OnCheckedChangeListener {

        /**
         * Called when the checked state of a FAB has changed.
         *
         * @param fabView   The FAB view whose state has changed.
         * @param isChecked The new checked state of buttonView.
         */
        void onCheckedChanged(FloatingActionButton fabView, boolean isChecked);
    }

    /**
     * An array of states.
     */
    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    private static final String TAG = "FloatingActionButton";

    // A boolean that tells if the FAB is checked or not.
    private boolean mChecked;

    // A listener to communicate that the FAB has changed it's state
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public FloatingActionButton(Context context) {
        this(context, null, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr,
                                int defStyleRes) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context,  AttributeSet attrs, int defStyle) {
        TypedArray a;
        Resources.Theme theme = context.getTheme();
        if (theme == null) {
            return;
        }

        a = theme.obtainStyledAttributes(attrs, R.styleable.FloatingActionButton, defStyle, 0);
        if (a == null) {
            return;
        }
        initAttrs(a);
        a.recycle();

        addImageView(context);
        setClickable(true);
        initBackground();
    }

    private void initAttrs(TypedArray a) {
        Drawable temp = a.getDrawable(R.styleable.FloatingActionButton_fabCenterDrawable);
        setImageViewDrawable(temp);
    }

    private void addImageView(Context context) {
        mImageView = new ImageView(context);
        LayoutParams layoutParams =
                new LayoutParams((int) getResources().getDimension(R.dimen.fab_icon_size),
                        (int) getResources().getDimension(R.dimen.fab_icon_size));
        layoutParams.gravity = Gravity.CENTER;

        mImageView.setDuplicateParentStateEnabled(true);
        mImageView.setLayoutParams(layoutParams);
        if(mImageViewDrawable != null) mImageView.setImageDrawable(mImageViewDrawable);

        addView(mImageView);
    }

    private void initBackground() {
        // Set the outline provider for this view. The provider is given the outline which it can
        // then modify as needed. In this case we set the outline to be an oval fitting the height
        // and width.
        if(isLollipop()) {
            setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setOval(0, 0, getWidth(), getHeight());
                }
            });

            // Finally, enable clipping to the outline, using the provider we set above
            setClipToOutline(true);
        } else {
            Drawable background = getBackground();

            if (background instanceof LayerDrawable) {
                LayerDrawable layers = (LayerDrawable) background;
                if (layers.getNumberOfLayers() == 2) {
                    Drawable shadow = layers.getDrawable(0);
                    Drawable circle = layers.getDrawable(1);

                    if (shadow instanceof GradientDrawable) {
                        ((GradientDrawable) shadow.mutate()).setGradientRadius(getShadowRadius(shadow, circle));
                    }

                    /*if (circle instanceof GradientDrawable) {
                        initCircleDrawable(circle);
                    }*/
                }
            } /*else if (background instanceof GradientDrawable) {
                initCircleDrawable(background);
            }*/

            setBackground(background);
            ViewCompat.setElevation(this, (int) getResources().getDimension(R.dimen.fab_elevation));
        }
    }

    private boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Calculates required radius of shadow.
     *
     * @param shadow underlay drawable
     * @param circle overlay drawable
     * @return calculated radius, always >= 1
     */
    protected static int getShadowRadius(Drawable shadow, Drawable circle) {
        int radius = 0;

        if (shadow != null && circle != null) {
            Rect rect = new Rect();
            radius = (circle.getIntrinsicWidth() + (shadow.getPadding(rect) ? rect.left + rect.right : 0)) / 2;
        }

        return Math.max(1, radius);
    }

    public Drawable getImageViewDrawable() {
        return mImageViewDrawable;
    }

    public void setImageViewDrawable(Drawable mImageViewDrawable) {
        this.mImageViewDrawable = mImageViewDrawable;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*int size = getWidth();
        if (!isLollipop()) {
            size += getResources().getDimension(R.dimen.fab_elevation) * 2;
        }
        setMeasuredDimension(size, size);*/
    }

    /**
     * Sets the checked/unchecked state of the FAB.
     * @param checked
     */
    public void setChecked(boolean checked) {
        // If trying to set the current state, ignore.
        if (checked == mChecked) {
            return;
        }
        mChecked = checked;

        // Now refresh the drawable state (so the icon changes)
        refreshDrawableState();

        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, checked);
        }
    }

    public ImageView getCenterImageView() {
        return mImageView;
    }

    /**
     * Register a callback to be invoked when the checked state of this button
     * changes.
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    /**
     * Override performClick() so that we can toggle the checked state when the view is clicked
     */
    @Override
    public boolean performClick() {
        //toggle();
        return super.performClick();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // As we have changed size, we should invalidate the outline so that is the the
        // correct size
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            invalidateOutline();
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }
}
