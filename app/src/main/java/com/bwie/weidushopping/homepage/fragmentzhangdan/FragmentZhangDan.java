package com.bwie.weidushopping.homepage.fragmentzhangdan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.weidushopping.R;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function: 这是滑动的fragment  账单界面
 */

public class FragmentZhangDan extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhangdan,null);
        return view;
    }
}
