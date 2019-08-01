package com.jidouauto.mvvm.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * @author leosun
 * Created by Leosun on 2019/1/8 22:39
 */
public class BindingAdapterUtil {

    public static final String TAG = "BindingAdapterUtil:";

    @BindingAdapter({"list_data"})
    public static void setListData(RecyclerView recyclerView, List data) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        if (data != null) {
            if (adapter instanceof BaseQuickAdapter) {
                ((BaseQuickAdapter) adapter).setNewData(data);
            }
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void loadCircleImage(ImageView imageView, String url) {
        RequestOptions requestOptions = new RequestOptions().centerCrop();
        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    @BindingAdapter({"imageUrl", "placeHolder", "error"})
    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable, Drawable errorDrawable) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(holderDrawable)
                .error(errorDrawable);
        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }


}
