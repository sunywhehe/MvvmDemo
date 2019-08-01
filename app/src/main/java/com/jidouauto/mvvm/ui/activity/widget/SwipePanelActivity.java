package com.jidouauto.mvvm.ui.activity.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.jidouauto.mvvm.R;
import com.jidouauto.mvvm.ui.widget.SwipePanel;
import com.jidouauto.mvvm.util.system.DimenUtils;

/**
 * @author leosun
 * Created by Leosun on 2019/7/26 14:18
 */
public class SwipePanelActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipepanel_activity);

        final SwipePanel swipePanel = new SwipePanel(this);
        // 设置左侧触发阈值 100dp
        swipePanel.setLeftEdgeSize(DimenUtils.dpToPxInt(100));
        // 设置左侧 icon
        swipePanel.setLeftDrawable(R.drawable.base_back);
        // 设置嵌套在 rootLayout 外层
        swipePanel.wrapView(findViewById(R.id.root_layout));
        // 设置完全划开松手后的监听
        swipePanel.setOnFullSwipeListener(new SwipePanel.OnFullSwipeListener() {
            @Override
            public void onFullSwipe(int direction) {
                finish();
                // 关闭
                swipePanel.close(direction);
            }
        });
    }
}
