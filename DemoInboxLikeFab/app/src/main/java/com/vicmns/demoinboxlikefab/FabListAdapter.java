package com.vicmns.demoinboxlikefab;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicmns on 12/2/2014.
 */
public class FabListAdapter extends RecyclerView.Adapter<FabListAdapter.FabViewHolder> {
    private static final int ANIMATION_DELAY = 25;

    public static class FabListModelItem {
        public static final int FAB_NORMAL_TYPE = 0;
        public static final int FAB_SMALL_TYPE = 1;

        int fabType;
        String fabTag;
        int fabResId = -1;
        int fabBackgroundResId = -1;
        Drawable fabDrawable;
        Drawable fabBackground;

        public FabListModelItem() {

        }

        public FabListModelItem(int fabType, String fabTag) {
            this.fabType = fabType;
            this.fabTag = fabTag;
        }
    }

    private List<FabListModelItem> mFabListModelItems;

    public FabListAdapter() {
        mFabListModelItems = new ArrayList<>();
        //mFabListModelItems.add(new FabListModelItem(FabListModelItem.FAB_NORMAL_TYPE, "NOT SMALL!!!"));
        mFabListModelItems.add(new FabListModelItem(FabListModelItem.FAB_SMALL_TYPE, "Small Fab 1"));
        mFabListModelItems.add(new FabListModelItem(FabListModelItem.FAB_SMALL_TYPE, "Small Fab 2"));
        mFabListModelItems.add(new FabListModelItem(FabListModelItem.FAB_SMALL_TYPE, "Small Fab 3"));
        mFabListModelItems.add(new FabListModelItem(FabListModelItem.FAB_SMALL_TYPE, "Small Fab 4"));
        mFabListModelItems.add(new FabListModelItem(FabListModelItem.FAB_SMALL_TYPE, "Small Fab 5"));
    }

    @Override
    public FabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == FabListModelItem.FAB_NORMAL_TYPE)
            return new FabViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fab_normal_list_item, parent, false));
        else
            return new FabViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fab_small_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(FabViewHolder holder, int position) {
        setFabTag(holder, mFabListModelItems.get(position));
        animateFabView(holder.fab, position);
        animateFabTagView(holder.fabTagCard, position);
    }

    private void setFabTag(FabViewHolder holder, FabListModelItem fabListModelItem) {
        holder.fabTag.setText(fabListModelItem.fabTag);
    }


    private void animateFabView(final View view, int position) {
        int animationTime = view.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);
        view.setVisibility(View.INVISIBLE);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.5f, 1.0f);
        scaleX.setDuration(animationTime);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.5f, 1.0f);
        scaleY.setDuration(animationTime);
        ObjectAnimator fade = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f);
        fade.setDuration(animationTime);
        fade.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new DecelerateInterpolator());
        set.playTogether(scaleX, scaleY, fade);
        set.setStartDelay(position * ANIMATION_DELAY);

        set.start();
    }

    private void animateFabTagView(final View view, int position) {
        int animationTime = view.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime);
        view.setVisibility(View.INVISIBLE);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.7f, 1.0f);
        scaleX.setDuration(animationTime);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.7f, 1.0f);
        scaleY.setDuration(animationTime);
        ObjectAnimator fade = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f);
        fade.setDuration(animationTime);
        fade.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        view.setPivotX(view.getWidth() / 2);
        view.setPivotY(view.getHeight());

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new DecelerateInterpolator());
        set.playTogether(scaleX, scaleY, fade);
        set.setStartDelay(position * ANIMATION_DELAY);

        set.start();
    }

    @Override
    public int getItemViewType(int position) {
        return mFabListModelItems.get(position).fabType;
    }

    @Override
    public int getItemCount() {
        return mFabListModelItems.size();
    }

    public class FabViewHolder extends RecyclerView.ViewHolder {
        View parentView;
        FloatingActionButton fab;
        ImageView fabImageView;
        View fabTagCard;
        TextView fabTag;

        public FabViewHolder(View parentView) {
            super(parentView);
            this.parentView = parentView;
            fab = (FloatingActionButton) parentView.findViewById(R.id.fab_item);
            fabImageView = (ImageView) fab.getChildAt(0);
            fabTagCard = parentView.findViewById(R.id.fab_tag_item_card);
            fabTag = (TextView) parentView.findViewById(R.id.fab_tag_item);
        }
    }

}