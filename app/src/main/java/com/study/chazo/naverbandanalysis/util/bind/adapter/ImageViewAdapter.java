package com.study.chazo.naverbandanalysis.util.bind.adapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Chazo on 2018-08-21
 */
public class ImageViewAdapter {

    @BindingAdapter("bind:imageUrl")
    public static void setUrl(ImageView view, String url){
        Glide.with(view.getContext()).load(url).into(view);
    }
}
