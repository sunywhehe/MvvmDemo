package com.jidouauto.mvvm.ui.activity.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.jidouauto.mvvm.R;

/**
 * @author leosun
 * Created by Leosun on 2019/7/26 15:18
 */
public class ProgressBarActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar_activity);

    }
}
