package com.jidouauto.mvvm.base;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author leosun
 * Created by Leosun on 2019/1/29 09:48
 * 所以的listAdapter 都继承这个类 ，方便做 公共操作
 */
public class BaseMyListAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    public BaseMyListAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(K helper, T item) {

    }
}
