package com.jidouauto.mvvm.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
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
}
