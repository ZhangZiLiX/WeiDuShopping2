package com.bwie.weidushopping.homepage.fragmentshouye.adapter.banner;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * date:2018/12/7
 * author:张自力(DELL)
 * function:  这是首页FragmentBanner  两边缩放
 *
 *         banner图的两边缩放图
 */

public class CardTransformer implements ViewPager.PageTransformer {

    //2 设置常量
    private static final float MAX_SCALE = 1.2f;
    private static final float MIN_SCALE = 1.0f;//0.85f

     //1 实现 ViewPager.PageTransformer 实现的方法
    @Override
    public void transformPage(View page, float position) {
        //进行设置
        if (position <= 1) {
            //   1.2f + (1-1)*(1.2-1.0)
            float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);

            page.setScaleX(scaleFactor);  //缩放效果

            if (position > 0) {
                page.setTranslationX(-scaleFactor * 2);
            } else if (position < 0) {
                page.setTranslationX(scaleFactor * 2);
            }
            page.setScaleY(scaleFactor);
        } else {

            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
