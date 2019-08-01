package com.jidouauto.mvvm.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jidouauto.mvvm.BR;
import com.jidouauto.mvvm.R;
import com.jidouauto.mvvm.base.BaseMyListAdapter;
import com.jidouauto.mvvm.data.entity.SearchKey;

import java.util.List;

/**
 * @author leosun
 * Created by Leosun on 2019/1/11 15:44
 */
public class SearchListAdapter extends BaseMyListAdapter<SearchKey, SearchListAdapter.SearchViewHolder> {

    private SearchViewPresenter mPresenter;

    public SearchListAdapter(int layoutResId, List<SearchKey> data) {
        super(layoutResId, data);

        mPresenter = new SearchViewPresenter();
    }

    @Override
    protected void convert(SearchViewHolder helper, SearchKey item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.item, item);
        binding.setVariable(BR.presenter, mPresenter);
        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    public static class SearchViewHolder extends BaseViewHolder {

        public SearchViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }

    public class SearchViewPresenter {
        public void addSearch(View view, SearchKey item) {

        }
    }
}
