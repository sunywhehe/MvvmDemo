package com.jidouauto.mvvm.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author leosun
 * Created by Leosun on 2019/1/7 22:14
 */
public abstract class BaseActivity<VDB extends ViewDataBinding> extends AppCompatActivity {

    protected VDB dataBinding;
    protected int layoutId = -1;

    /**
     * 获取 R.layout 的id
     *
     * @return id
     */
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentValue();
        layoutId = getLayoutId();
        if (layoutId > 0) {
            dataBinding = DataBindingUtil.setContentView(this, layoutId);
        }
        initView();
        initData();
    }

    /**
     * 初始化 数据
     */
    protected abstract void initData();

    /**
     * 初始化 控件
     */
    protected abstract void initView();

    /**
     * 获取 intent 传值 的value
     */
    protected abstract void initIntentValue();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
