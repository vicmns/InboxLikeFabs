package com.vicmns.stackerviewlib;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.vicmns.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicmns on 12/2/2014.
 */
public class FabListAdapter extends RecyclerView.Adapter<FabListAdapter.FabViewHolder> {
    private static final int ANIMATION_DELAY = 25;

    private List<FabListModelItem> mFabListModelItems;

    public FabListAdapter() {
        mFabListModelItems = new ArrayList<>();
    }

    public void setFabListModel(List<FabListModelItem> fabListModelItems) {
        mFabListModelItems = fabListModelItems;
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
        setFabSourceDrawable(holder, mFabListModelItems.get(position));
        setFabBackgroundDrawable(holder, mFabListModelItems.get(position));
        setFabTag(holder, mFabListModelItems.get(position));
        animateFabView(holder.fab, position);
        animateFabTagView(holder.fabTagCard, position);
    }

    private void setFabSourceDrawable(FabViewHolder holder, FabListModelItem fabListModelItem) {
        if(fabListModelItem.getFabResDrawable() != null) {
            holder.fabImageView.setImageDrawable(fabListModelItem.getFabResDrawable());
        } else {
            holder.fabImageView.setImageResource(fabListModelItem.getFabResId());
        }
    }

    private void setFabBackgroundDrawable(FabViewHolder holder, FabListModelItem fabListModelItem) {
        if(fabListModelItem.getFabBackgroundDrawable() != null) {
            holder.fab.setBackground(fabListModelItem.getFabBackgroundDrawable());
        } else {
            holder.fab.setBackgroundResource(fabListModelItem.getFabBackgroundResId());
        }
    }

    private void setFabTag(FabViewHolder holder, FabListModelItem fabListModelItem) {
        holder.fabTag.setText(fabListModelItem.getFabTag());
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
        return mFabListModelItems.get(position).getFabType();
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