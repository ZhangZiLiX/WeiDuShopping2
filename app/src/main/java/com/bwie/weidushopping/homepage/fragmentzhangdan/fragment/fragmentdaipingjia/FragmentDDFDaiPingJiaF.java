package com.bwie.weidushopping.homepage.fragmentzhangdan.fragment.fragmentdaipingjia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.weidushopping.R;

/**
 * date:2018/12/16
 * author:张自力(DELL)
 * function:  我的订单界面Fragment  的待评价Fragment
 */

public class FragmentDDFDaiPingJiaF extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_zdf_dpjf,null);
        return view;
    }
}
