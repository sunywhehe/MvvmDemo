package com.jidouauto.mvvm.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.jidouauto.mvvm.R;
import com.jidouauto.mvvm.base.BaseActivity;
import com.jidouauto.mvvm.databinding.MainActivityBinding;
import com.jidouauto.mvvm.ui.adapter.SearchListAdapter;
import com.jidouauto.mvvm.viewmodel.MainActivityViewModel;

/**
 * @author leosun
 * Created by Leosun on 2019/1/7 22:13
 */
public class MainActivity extends BaseActivity<MainActivityBinding> {

    public static final String TAG = "MainActivity:";

    private MainActivityViewModel mViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        dataBinding.setVm(mViewModel);
        dataBinding.setLifecycleOwner(this);

        dataBinding.rvSearchkeyList.setLayoutManager(new LinearLayoutManager(this));
        SearchListAdapter mListAdapter = new SearchListAdapter(R.layout.search_list_item, null);
        dataBinding.rvSearchkeyList.setAdapter(mListAdapter);

        dataBinding.addSearchkeyButton.setOnClickListener(view -> {
            mViewModel.addSearchKey(dataBinding.addSearchkeyEdittext.getText().toString());
            dataBinding.addSearchkeyEdittext.setText("");
        });
    }

    @Override
    protected void initIntentValue() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
