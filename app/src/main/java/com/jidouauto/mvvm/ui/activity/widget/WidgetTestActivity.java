package com.jidouauto.mvvm.ui.activity.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.jidouauto.mvvm.R;

/**
 * @author leosun
 * Created by Leosun on 2019/7/26 14:01
 */
public class WidgetTestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_test_activity);
    }

    public void swipePanel(View view) {
        Intent intent = new Intent(this, SwipePanelActivity.class);
        startActivity(intent);
    }

    public void progressbar(View view) {
        Intent intent = new Intent(this, ProgressBarActivity.class);
        startActivity(intent);
    }
}
