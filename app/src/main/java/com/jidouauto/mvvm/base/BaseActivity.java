package com.jidouauto.mvvm.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.jidouauto.mvvm.util.system.IMEUtils;

/**
 * @author leosun
 * Created by Leosun on 2019/1/7 22:14
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T dataBinding;
    protected int layoutId = -1;

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutId = getLayoutId();
        if (layoutId > 0) {
            dataBinding = DataBindingUtil.setContentView(this, layoutId);
        }
    }

    @Override
    protected void onDestroy() {
        IMEUtils.fixSoftInputLeaks(this);
        super.onDestroy();
    }
}
