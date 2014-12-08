package com.vicmns.demoinboxlikefab;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;

/**
 * Created by vicmns on 12/1/2014.
 */
public class FabStackerView {
    /**
     * Max allowed duration for a "click", in milliseconds.
     */
    private static final int MAX_CLICK_DURATION = 1000;

    /**
     * Max allowed distance to move during a "click", in DP.
     */
    private static final int MAX_CLICK_DISTANCE = 15;
    private View mMainView;
    private RecyclerView mFabRecyclerView;
    private RecyclerView.Adapter<?> mFabAdapter;
    private View mMainFab, mMainFabTag, mMainFabItemLayout;
    private Animation mRotateRightAnimation, mRotateLeftAnimation, mAppearAnimation, mHideAnimation;

    private long pressStartTime;
    private float pressedX;
    private float pressedY;

    public FabStackerView() {
    }

    public static void attachStackerView(FabStackerView fabStackerView, ViewGroup parentView) {
        fabStackerView.initFabStackerView(parentView);
    }

    public void initFabStackerView(ViewGroup parentView) {
        initViews(parentView);
        initAnimators();
        setupRecyclerView();
    }

    private void initViews(ViewGroup parentView) {
        LayoutInflater layoutInflater = LayoutInflater.from(parentView.getContext());
        mMainView = layoutInflater.inflate(R.layout.fab_list_layout, parentView, false);
        mMainFabItemLayout = mMainView.findViewById(R.id.fab_item_main_layout);
        mMainFab = mMainView.findViewById(R.id.fab_item);
        mMainFabTag = mMainView.findViewById(R.id.fab_tag_item);
        mMainView.setVisibility(View.GONE);
        parentView.addView(mMainView);

        mMainView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideStacker();
                return true;
            }
        });
    }

    private void initAnimators() {
        mRotateRightAnimation = new RotateAnimation(-45.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateLeftAnimation = new RotateAnimation(0.0f, -45.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateRightAnimation.setDuration(350);
        mRotateLeftAnimation.setDuration(350);
        mAppearAnimation = new AlphaAnimation(0.0f, 1.0f);
        mAppearAnimation.setDuration(250);
        mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
        mHideAnimation.setDuration(250);
    }

    private void setupRecyclerView() {
        mFabRecyclerView = (RecyclerView) mMainView.findViewById(R.id.fabs_recycler_view);

        mFabRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        pressStartTime = System.currentTimeMillis();
                        pressedX = event.getX();
                        pressedY = event.getY();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        long pressDuration = System.currentTimeMillis() - pressStartTime;
                        if (pressDuration < MAX_CLICK_DURATION &&
                                distance(pressedX, pressedY, event.getX(),
                                        event.getY(), v.getContext()) < MAX_CLICK_DISTANCE) {
                            hideStacker();
                            return true;
                        }
                    }
                }
                return false;
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mMainView.getContext());
        //linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        mFabRecyclerView.setLayoutManager(linearLayoutManager);
        mFabRecyclerView.setHasFixedSize(true);
    }

    private static float distance(float x1, float y1, float x2, float y2, Context context) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        float distanceInPx = (float) Math.sqrt(dx * dx + dy * dy);
        return pxToDp(distanceInPx, context);
    }

    private static float pxToDp(float px, Context context) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public void setMainFabPosition(View attachingFab) {
        ViewGroup.MarginLayoutParams mMainFabLayoutParams = (ViewGroup.MarginLayoutParams) mMainFab.getLayoutParams();
        ViewGroup.MarginLayoutParams attachingFabLayoutParams = (ViewGroup.MarginLayoutParams) attachingFab.getLayoutParams();
        mMainFabLayoutParams.topMargin = attachingFabLayoutParams.topMargin;
        mMainFabLayoutParams.bottomMargin = attachingFabLayoutParams.bottomMargin;
        mMainFabLayoutParams.leftMargin = attachingFabLayoutParams.leftMargin;
        mMainFabLayoutParams.rightMargin = attachingFabLayoutParams.rightMargin;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mMainFabLayoutParams.setMarginStart(attachingFabLayoutParams.getMarginStart());
            mMainFabLayoutParams.setMarginEnd(attachingFabLayoutParams.getMarginEnd());
        }
    }

    public void initFabListView(View fabToAnchor) {
        ViewGroup.MarginLayoutParams anchorLayoutParams = (ViewGroup.MarginLayoutParams) fabToAnchor.getLayoutParams();
        LinearLayout.LayoutParams layoutParamsToAnchor = (LinearLayout.LayoutParams) mMainFabItemLayout.getLayoutParams();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layoutParamsToAnchor.setMarginEnd(anchorLayoutParams.getMarginEnd());
        } else {
            layoutParamsToAnchor.rightMargin = anchorLayoutParams.rightMargin;
        }

        layoutParamsToAnchor.bottomMargin = anchorLayoutParams.bottomMargin;
        mMainFabItemLayout.setMinimumHeight(fabToAnchor.getMeasuredHeight() +
                (int) fabToAnchor.getContext().getResources().getDimension(R.dimen.fab_elevation));
        mMainFabItemLayout.requestLayout();

    }

    public void setFabAdapter(RecyclerView.Adapter adapter) {
        mFabAdapter = adapter;
        mFabRecyclerView.setAdapter(mFabAdapter);
    }

    public RecyclerView.Adapter<?> getFabAdapter() {
        return mFabAdapter;
    }


    public void hideStacker() {
        setFabListVisibility(View.INVISIBLE, true);
    }

    public void hideStackerInmediate() {
        setFabListVisibility(View.INVISIBLE, false);
    }

    public void showStacker() {
        setFabListVisibility(View.VISIBLE, true);
    }

    public void showStacketInmediate() {
        setFabListVisibility(View.VISIBLE, false);
    }

    private void setFabListVisibility(int visibility, boolean showAnimation) {
        if (visibility == mMainView.getVisibility())
            return;

        if (visibility == View.VISIBLE)
            showFabList(showAnimation);
        else
            hideFabList(showAnimation);
    }

    private void showFabList(boolean showAnimation) {
        mMainView.setVisibility(View.VISIBLE);
        if (!showAnimation) {
            return;
        }

        mAppearAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mFabRecyclerView.getAdapter().notifyItemRangeChanged(0, mFabRecyclerView.getAdapter().getItemCount());
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mMainView.startAnimation(mAppearAnimation);
        mMainFab.startAnimation(mRotateRightAnimation);
    }

    private void hideFabList(boolean showAnimation) {
        if (!showAnimation) {
            mMainView.setVisibility(View.GONE);
            return;
        }
        mHideAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mMainView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mMainView.startAnimation(mHideAnimation);
        mMainFab.startAnimation(mRotateLeftAnimation);
    }

    public boolean isStackerVisible() {
        return mMainView.getVisibility() == View.VISIBLE;
    }

    public boolean handleBackPress() {
        if (isStackerVisible()) {
            hideStacker();
            return true;
        }

        return false;
    }

    public static class Builder {
        private FabStackerView fabStackerView;
        private Context mContext;
        private ViewGroup mViewToAttach;

        public Builder(Activity activity) {
            fabStackerView = new FabStackerView();
            mViewToAttach = (ViewGroup) activity.findViewById(android.R.id.content);
            attachStackerView(fabStackerView, mViewToAttach);
        }

        public Builder anchorStackToFab(View fabToAnchor) {
            fabStackerView.setMainFabPosition(fabToAnchor);
            return this;
        }

        public FabStackerView build() {
            return fabStackerView;
        }
    }
}
