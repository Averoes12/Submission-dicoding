package com.averoes.daff.cataloguemovie20.nowShowing;

import android.view.View;

/**
 * Created by daff on 09/02/19 at 11:58.
 */

public class CustomItemClick implements View.OnClickListener {
    private int position;
    private OnItemClickCallback onItemClickCallback;

    public CustomItemClick(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, position);
    }
    public interface OnItemClickCallback{
        void onItemClicked(View view, int position);
    }
}
