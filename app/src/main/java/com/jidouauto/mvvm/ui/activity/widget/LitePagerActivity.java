package com.jidouauto.mvvm.ui.activity.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.jidouauto.mvvm.R;

/**
 * @author leosun
 * Created by Leosun on 2019/8/8 17:24
 */
public class LitePagerActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepager);
    }
}
