package com.bwie.weidushopping.homepage.fragmentshouye.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.myutilsclass.MyUtils;
import com.bwie.weidushopping.R;
import com.bwie.weidushopping.homepage.fragmentgouwuche.bean.GouWuCheBean;
import com.bwie.weidushopping.homepage.fragmentgouwuche.presenter.GouWuChePresenter;
import com.bwie.weidushopping.homepage.fragmentshouye.adapter.banner.BannerAdapter;
import com.bwie.weidushopping.homepage.fragmentshouye.adapter.keysearchselect.KeySearchSelectAdapte;
import com.bwie.weidushopping.homepage.fragmentshouye.adapter.molishishang.MoLiAdapte;
import com.bwie.weidushopping.homepage.fragmentshouye.adapter.pinzhishenghuo.PinZhiAdapte;
import com.bwie.weidushopping.homepage.fragmentshouye.adapter.rexiaoxinpin.ReXiaoAdapte;
import com.bwie.weidushopping.homepage.fragmentshouye.adapter.xiangqing.XiangQingAdapter;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.AddCar;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.BannerBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.KeySeacherBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.ShopBean;
import com.bwie.weidushopping.homepage.fragmentshouye.bean.XiangQingBean;
import com.bwie.weidushopping.homepage.fragmentshouye.presenter.Presenter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lwj.widget.viewpagerindicator.ViewPagerIndicator;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date:2018/12/6
 * author:张自力(DELL)
 * function:  这是滑动的fragment  首页界面
 */

public class FragmentShouYe extends Fragment implements IView, com.bwie.weidushopping.homepage.fragmentgouwuche.view.IView, View.OnClickListener {

    //轮播图
    private int FLAG = 1001;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FLAG) {
                //得到当前轮播图的条目(不会提示补充)
                int item = vpSyf.getCurrentItem();
                //判断是否是最后一张
                if (item < mBeanList.size() - 1) {
                    item++;
                } else {
                    //否则就让item归零
                    item = 0;
                }
                //重新为vp设置当前条目
                vpSyf.setCurrentItem(item);
                //延迟发送
                handler.sendEmptyMessageDelayed(FLAG, 2000);
            }
        }
    };

    private Boolean isSYFMenu = false;//头部菜单标识
    private Boolean isSYFSearch = false;//头部搜索标识

    private ImageView imgSyfMenu;
    private TextView txtSyfSousuo;
    private ViewPager vpSyf;
    private ViewPagerIndicator indicatorLine2;
    private List<BannerBean.ResultBean> mBeanList;
    private BannerAdapter mBannerAdapter;
    private Presenter mPresenter;
    private SearchView searchviewSyf;
    private LinearLayout lvSyfTopRightmenu;
    private XRecyclerView mXlvRXXPSYF;
    private List<ShopBean.ResultBean.RxxpBean.CommodityListBeanXX> mRxxplist;
    private ReXiaoAdapte mReXiaoAdapte;
    private List<ShopBean.ResultBean.MlssBean.CommodityListBean> mMlssBeanList;
    private List<ShopBean.ResultBean.PzshBean.CommodityListBeanX> mPzshBeanList;
    private MoLiAdapte mMoLiAdapte;
    private PinZhiAdapte mPinZhiAdapte;
    private XRecyclerView mXlvMLSSSYF;
    private XRecyclerView mXlvPZSHSYF;
    private RelativeLayout mRlShowDataSYF;
    private LinearLayout mLlSearchDataSYF;
    private XRecyclerView mXlvSearchDataSYF;

    //用户搜索
    private Handler handlerloding = new Handler();//加载刷新handler
    private int page = 1;//刷新页数
    private boolean isloding;//是否加载
    private boolean issearch = false;//用于点击搜索按钮 与 返回按钮
    private List<KeySeacherBean.ResultBean> mKeySearchList;
    private KeySearchSelectAdapte mKeySearchSelectAdapte;
    private TextView txtNanzhuangSyf;
    private TextView txtNvzhuangSyf;
    private TextView txtNvxieSyf;
    private TextView txtChenshanSyf;
    private TextView txtMeizhuanghufuSyf;
    private TextView txtShoujishumaSyf;
    private ImageView imgMaoyiSyf;
    private ImageView imgKuzhuangSyf;
    private ImageView imgQunzhuangSyf;
    private ImageView imgWaitaoSyf;
    private ImageView imgWeiyiSyf;
    private LinearLayout mLlSearchData0SYF;
    private LinearLayout mLlXiangQingShYF;
    private LinearLayout mLlShopAllShYF;
    private XRecyclerView mXlvXiangQingShyf;
    private XiangQingAdapter mXiangQingAdapter;
    private Button mBtnFanHuiXiangQingShYf;
    private boolean isxiangqing = true;//详情页不展示
    private SharedPreferences mIsonelogin;
    private String mIsuserid;
    private String mIsSessionId;
    private XiangQingBean mXiangQingBean;
    private ImageView mSimpleXiangQingShyf;
    private TextView mTxtTitleXiangQingShyf;
    private TextView mTxtJieShaoXiangQingShyf;
    private TextView mTxtPriceXiangQingShyf;
    private XBanner xbanner;
    private TextView txtSyfBack;
    private XBanner xbannerXqSyf;
    private int mUserid;
    private String mSessionid;
    private TextView txtJiaru;
    private TextView txtFanhui;
    private int commodityIds;
    private GouWuChePresenter mGouWuChePresenter;
    private Map<String, String> mMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye, null);
        //1 初始化控件
        initView(view);
        //2 List 和 Adapter 设置
        setVPLunBo();
        //设置SP
        setSP(view);
        //3 初始化Presenter层
        initPresenter();
        //4 设置搜索框
        setTopSearchView();
        //5 所有Adapter的条目点击事件设置
        setXRVItemOnClick();
        //6 用户搜索  上拉加载下拉刷新设置
        setXRVPushAndLoding();
        return view;
    }


    /**
     * //设置SP
     */
    private void setSP(View view) {

        //通过工具类  得到存储的userid  和  sessionid
        mUserid = (int) MyUtils.getData(getActivity(), "userid", 0);
        mSessionid = (String) MyUtils.getData(getActivity(), "sessionid", "");

    }

    /**
     * //3 为XRecyclerView设置上拉加载  下拉刷新
     */
    private void setXRVPushAndLoding() {
        //设置可以加载刷新
        mXlvSearchDataSYF.setPullRefreshEnabled(true);
        mXlvSearchDataSYF.setLoadingMoreEnabled(true);
        //刷新监听
        mXlvSearchDataSYF.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //下拉刷新  重新到第一页
                page = 1;
                mPresenter.getKeySearchDataP(UserContent, page);
                isloding = false;
                //设置定制刷新时间
                handlerloding.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvSearchDataSYF.refreshComplete();//停止刷新
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                //上拉加载  让page加加
                page++;
                mPresenter.getKeySearchDataP(UserContent, page);
                isloding = true;//是上拉加载
                //设置定制刷新时间
                handlerloding.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvSearchDataSYF.loadMoreComplete();//停止加载
                    }
                }, 2000);
            }
        });

    }

    /**
     * //5 所有Adapter的条目点击事件设置
     * <p>
     * 通过接口回调
     */
    private void setXRVItemOnClick() {
        //1 热销条目点击
        mReXiaoAdapte.setRXSPOnClickListener(new ReXiaoAdapte.RXSPOnClickListener() {
            @Override
            public void onChanger(int id) {
                if (isxiangqing) {//展示不同界面  如果是true
                    //调用P层 请求详情 的方法
                    mPresenter.getXiangQingDataP(id, mUserid, mSessionid);
                    mLlXiangQingShYF.setVisibility(View.VISIBLE);  //说明点击后要展示详情
                    mLlShopAllShYF.setVisibility(View.GONE);
                    isxiangqing = false;
                } else {
                    mLlXiangQingShYF.setVisibility(View.GONE);  //说明点击后要隐藏详情
                    mLlShopAllShYF.setVisibility(View.VISIBLE);
                    isxiangqing = true;
                }

                // Toast.makeText(getActivity(), "您要购买" + id + "号商品吗?请先看看您的口袋-_-!", Toast.LENGTH_SHORT).show();
            }
        });

        //2 魔力时尚
        mMoLiAdapte.setMLSSOnClickListener(new MoLiAdapte.MLSSOnClickListener() {
            @Override
            public void onChanger(int id) {
                if (isxiangqing) {//展示不同界面  如果是true
                    //调用P层 请求详情 的方法
                    mPresenter.getXiangQingDataP(id, mUserid, mSessionid);
                    mLlXiangQingShYF.setVisibility(View.VISIBLE);  //说明点击后要展示详情
                    mLlShopAllShYF.setVisibility(View.GONE);
                    isxiangqing = false;
                } else {
                    mLlXiangQingShYF.setVisibility(View.GONE);  //说明点击后要隐藏详情
                    mLlShopAllShYF.setVisibility(View.VISIBLE);
                    isxiangqing = true;
                }
                //Toast.makeText(getActivity(), "您要购买" + id + "号商品吗?请先看看您的口袋-_-!", Toast.LENGTH_SHORT).show();
            }
        });

        //3 品质生活
        mPinZhiAdapte.setPZSHOnClickListener(new PinZhiAdapte.PZSHOnClickListener() {
            @Override
            public void onChanger(int id) {
                if (isxiangqing) {//展示不同界面  如果是true
                    //调用P层 请求详情 的方法
                    mPresenter.getXiangQingDataP(id, mUserid, mSessionid);

                    mLlXiangQingShYF.setVisibility(View.VISIBLE);  //说明点击后要展示详情
                    mLlShopAllShYF.setVisibility(View.GONE);
                    isxiangqing = false;
                } else {
                    mLlXiangQingShYF.setVisibility(View.GONE);  //说明点击后要隐藏详情
                    mLlShopAllShYF.setVisibility(View.VISIBLE);
                    isxiangqing = true;
                }
                //Toast.makeText(getActivity(), "您要购买" + id + "号商品吗?请先看看您的口袋-_-!", Toast.LENGTH_SHORT).show();
            }
        });
        //4 用户关键字搜索
        mKeySearchSelectAdapte.setKeySearchOnClickListener(new KeySearchSelectAdapte.KeySearchOnClickListener() {
            @Override
            public void onChanger(int id) {
                if (isxiangqing) {//展示不同界面  如果是true
                    //调用P层 请求详情 的方法
                    mPresenter.getXiangQingDataP(id, mUserid, mSessionid);

                    mLlXiangQingShYF.setVisibility(View.VISIBLE);  //说明点击后要展示详情
                    mLlShopAllShYF.setVisibility(View.GONE);
                    isxiangqing = false;
                } else {
                    mLlXiangQingShYF.setVisibility(View.GONE);  //说明点击后要隐藏详情
                    mLlShopAllShYF.setVisibility(View.VISIBLE);
                    isxiangqing = true;
                }
                //Toast.makeText(getActivity(), "您要购买" + id + "号商品吗?请先看看您的口袋-_-!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * //4 设置搜索框
     */
    //定义一个变量  用来存储用户输入要搜索的数据
    String UserContent = "";

    private void setTopSearchView() {
        //点击监听
        searchviewSyf.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //1 当点击搜索按钮时触发该方法
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //2 // 当搜索内容改变时触发该方法  将用户搜索的数据传到点击搜索的模块  进行搜索
                UserContent = newText;
                return false;
            }
        });
    }

    /**
     * //3 初始化Presenter层
     */
    private void initPresenter() {
        //查询购物车
        mGouWuChePresenter = new GouWuChePresenter();
        mGouWuChePresenter.attach(this);
        mGouWuChePresenter.getSelectShoppingDataP(mUserid,mSessionid);//调用查询购物车方法

        mPresenter = new Presenter();
        mPresenter.attach(this);
        mPresenter.getBannerDataP();//轮播图请求数据方法
        mPresenter.getRXXPDataP();//热销新品数据请求方法
        mPresenter.getMLSSDataP();//魔力时尚
        mPresenter.getPZSHDataP();//品质生活


    }

    /**
     * //2 轮播图设置
     */
    private void setVPLunBo() {
        //1 创建List
        mBeanList = new ArrayList<>();//轮播图
        mRxxplist = new ArrayList<>();//热销新品
        mMlssBeanList = new ArrayList<>();//魔力时尚
        mPzshBeanList = new ArrayList<>();//品质生活
        mKeySearchList = new ArrayList<>();//用户搜索list

        //2 创建BannerAdapter对象
        mBannerAdapter = new BannerAdapter(getActivity(), mBeanList);//轮播图
        mReXiaoAdapte = new ReXiaoAdapte(getActivity(), mRxxplist);//热销新品
        mMoLiAdapte = new MoLiAdapte(getActivity(), mMlssBeanList); //魔力时尚
        mPinZhiAdapte = new PinZhiAdapte(getActivity(), mPzshBeanList);//品质生活
        mKeySearchSelectAdapte = new KeySearchSelectAdapte(getActivity(), mKeySearchList);//用户搜索

        //3 添加Adapter
        //3.1 轮播图
        vpSyf.setAdapter(mBannerAdapter);//轮播图

        //3.2 热销新品XlvRXXPSYF设置
        setMXlvRXXPSYF();
        mXlvRXXPSYF.setAdapter(mReXiaoAdapte);//热销新品
        //3.3 魔力时尚设置
        setXlvMLSHSYF();
        mXlvMLSSSYF.setAdapter(mMoLiAdapte);//魔力时尚
        //3.4 品质生活设置
        setXlvPZSHSYF();
        mXlvPZSHSYF.setAdapter(mPinZhiAdapte);//品质生活
        //3.5
        mXlvSearchDataSYF.setAdapter(mKeySearchSelectAdapte);//用户搜索
        //vpBanner延迟发送
        handler.sendEmptyMessageDelayed(FLAG, 2000);//延迟发送

    }

    /**
     * //3.4 品质生活设置
     */
    private void setXlvPZSHSYF() {
        //设置布局格式
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mXlvPZSHSYF.setLayoutManager(layoutManager);
        mXlvPZSHSYF.setPullRefreshEnabled(true);
        mXlvPZSHSYF.setLoadingMoreEnabled(true);//设置上拉加载  下拉刷新
        mXlvPZSHSYF.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);//加载更多样式

        //设置监听 刷新
        mXlvPZSHSYF.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvPZSHSYF.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                //加载
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvPZSHSYF.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    /**
     * //3.3 魔力时尚设置
     */
    private void setXlvMLSHSYF() {
        //设置布局格式
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mXlvMLSSSYF.setLayoutManager(layoutManager);
        mXlvMLSSSYF.setPullRefreshEnabled(true);
        mXlvMLSSSYF.setLoadingMoreEnabled(true);//设置上拉加载  下拉刷新
        mXlvMLSSSYF.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);//加载更多样式

        //设置监听 刷新
        mXlvMLSSSYF.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvMLSSSYF.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                //加载
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvMLSSSYF.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    /**
     * 3.2 热销新品 的XRecyclerView的设置
     */
    private void setMXlvRXXPSYF() {
        //设置布局格式
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        mXlvRXXPSYF.setLayoutManager(layoutManager);
        mXlvRXXPSYF.setPullRefreshEnabled(true);
        mXlvRXXPSYF.setLoadingMoreEnabled(true);//设置上拉加载  下拉刷新
        mXlvRXXPSYF.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);//加载更多样式

        //设置监听 刷新
        mXlvRXXPSYF.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvRXXPSYF.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                //加载
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXlvRXXPSYF.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    /**
     * //1 初始化控件
     */
    private void initView(View view) {
        //初始化控件


        //1 头部
        imgSyfMenu = (ImageView) view.findViewById(R.id.img_syf_menu);//头部menu
        txtSyfSousuo = (TextView) view.findViewById(R.id.txt_syf_sousuo);//头部搜索按钮

        //2 轮播图
        vpSyf = (ViewPager) view.findViewById(R.id.vp_syf);//轮播图
        indicatorLine2 = (ViewPagerIndicator) view.findViewById(R.id.indicator_line2);//小圆点控件
        searchviewSyf = (SearchView) view.findViewById(R.id.searchview_syf);//搜索框
        lvSyfTopRightmenu = (LinearLayout) view.findViewById(R.id.lv_syf_top_rightmenu);//点击菜单是隐藏展示的视图布局父类

        //3 下方数据展示控件
        mXlvRXXPSYF = view.findViewById(R.id.xlv_rxxp_syf);//热销新品
        mXlvMLSSSYF = view.findViewById(R.id.xlv_mlss_syf);//魔力时尚
        mXlvPZSHSYF = view.findViewById(R.id.xlv_pzsh_syf);//品质生活

        //4 整体布局隐藏展示
        mRlShowDataSYF = view.findViewById(R.id.rl_showdata_syf);//第一次进入 搜索框下的内容展示区
        mLlSearchDataSYF = view.findViewById(R.id.ll_searchdata_syf);//点击搜索时  搜索到的内容展示区
        mXlvSearchDataSYF = view.findViewById(R.id.xlv_searchdata_syf);//搜索到的内容展示控件
        mLlSearchData0SYF = view.findViewById(R.id.ll_searchdata0_syf);//点击搜索 搜索不到时进行展示的布局  默认隐藏
        //设置展示界面的布局格式
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mXlvSearchDataSYF.setLayoutManager(layoutManager);
        //http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword="板鞋"&page=2&count=5


        //点击事件监听
        imgSyfMenu.setOnClickListener(this);//菜单点击
        txtSyfSousuo.setOnClickListener(this);//搜索点击
        //点击菜单 展示第一行 进行查询
        txtNanzhuangSyf = (TextView) view.findViewById(R.id.txt_nanzhuang_syf);
        txtNanzhuangSyf.setOnClickListener(this);
        txtNvzhuangSyf = (TextView) view.findViewById(R.id.txt_nvzhuang_syf);
        txtNvzhuangSyf.setOnClickListener(this);
        txtNvxieSyf = (TextView) view.findViewById(R.id.txt_nvxie_syf);
        txtNvxieSyf.setOnClickListener(this);
        txtChenshanSyf = (TextView) view.findViewById(R.id.txt_chenshan_syf);
        txtChenshanSyf.setOnClickListener(this);
        txtMeizhuanghufuSyf = (TextView) view.findViewById(R.id.txt_meizhuanghufu_syf);
        txtMeizhuanghufuSyf.setOnClickListener(this);
        txtShoujishumaSyf = (TextView) view.findViewById(R.id.txt_shoujishuma_syf);
        txtShoujishumaSyf.setOnClickListener(this);
        imgMaoyiSyf = (ImageView) view.findViewById(R.id.img_maoyi_syf);
        imgMaoyiSyf.setOnClickListener(this);
        imgKuzhuangSyf = (ImageView) view.findViewById(R.id.img_kuzhuang_syf);
        imgKuzhuangSyf.setOnClickListener(this);
        imgQunzhuangSyf = (ImageView) view.findViewById(R.id.img_qunzhuang_syf);
        imgQunzhuangSyf.setOnClickListener(this);
        imgWaitaoSyf = (ImageView) view.findViewById(R.id.img_waitao_syf);
        imgWaitaoSyf.setOnClickListener(this);
        imgWeiyiSyf = (ImageView) view.findViewById(R.id.img_weiyi_syf);
        imgWeiyiSyf.setOnClickListener(this);

        //详情页
        mLlShopAllShYF = view.findViewById(R.id.ll_shopall_shyf);//存放scrollerView的容器
        mLlXiangQingShYF = view.findViewById(R.id.ll_xingqing_shyf);//详情容器
        mSimpleXiangQingShyf = view.findViewById(R.id.simple_xiangqing_shyf);
        mTxtTitleXiangQingShyf = view.findViewById(R.id.txt_title_xiangqing_shyf);
        mTxtJieShaoXiangQingShyf = view.findViewById(R.id.txt_jieshao_xiangqing_shyf);
        mTxtPriceXiangQingShyf = view.findViewById(R.id.txt_price_xiangqing_shyf);
        //轮播图第二种方法  使用XBanner实现画廊效果
        xbanner = (XBanner) view.findViewById(R.id.xbanner);
        //用于切换搜索  返回按钮
        txtSyfBack = (TextView) view.findViewById(R.id.txt_syf_back);
        txtSyfBack.setOnClickListener(this);
        xbannerXqSyf = (XBanner) view.findViewById(R.id.xbanner_xq_syf);
        xbannerXqSyf.setOnClickListener(this);
        txtJiaru = (TextView) view.findViewById(R.id.txt_jiaru);
        txtJiaru.setOnClickListener(this);
        txtFanhui = (TextView) view.findViewById(R.id.txt_fanhui);
        txtFanhui.setOnClickListener(this);
    }


    /**
     * 5 点击事件
     */

    //定义一个变量  判断菜单按钮是否被点击过了
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_syf_menu://点击菜单按钮（头部最右侧）
                //点击后 设置为true
                isSYFMenu = !isSYFMenu;
                //点击后需要将视图展示出来  再次点击  隐藏
                if (isSYFMenu) {
                    lvSyfTopRightmenu.setVisibility(View.VISIBLE);
                } else {
                    lvSyfTopRightmenu.setVisibility(View.GONE);
                }

                break;

            case R.id.txt_jiaru://点击加入购物车
                 //调用Presenter的加入购物车方法
                 mPresenter.getAddCarP(mSessionid,mUserid,mMap);

                break;

            case R.id.txt_fanhui://点击返回 从详情
                mLlXiangQingShYF.setVisibility(View.GONE);  //说明点击后要展示详情
                mLlShopAllShYF.setVisibility(View.VISIBLE);
                isxiangqing = false;

                break;

            case R.id.txt_syf_sousuo://点击搜索按钮（头部最左侧）
                //首先判断用户输入搜索框的内容不能为空
                if (UserContent.equals("")) {
                    //如果为空  进行提示  且不进行切换展示
                    Toast.makeText(getActivity(), "请填写要搜索的名称", Toast.LENGTH_SHORT).show();
                    break;
                }
                //不为空  调用隐藏展示方法
                getSearchShowAndHid();
                //点击搜索后  将标记改变  隐藏搜索按钮
                issearch = true;
                if (issearch) {
                    txtSyfSousuo.setVisibility(View.GONE);
                    //显示返回按钮
                    txtSyfBack.setVisibility(View.VISIBLE);
                }


                break;

            case R.id.txt_syf_back://点击完搜索后，展示返回按钮，隐藏搜索按钮
                if (issearch) {
                    //点击返回  不管输入框有无值 就直接切换
                    //不为空  调用隐藏展示方法
                    getSearchShowAndHid();
                    //改变标识
                    issearch = false;
                    //隐藏展示按钮
                    txtSyfSousuo.setVisibility(View.VISIBLE);
                    txtSyfBack.setVisibility(View.GONE);
                }

                break;

            case R.id.txt_nanzhuang_syf://男装点击查询
                ByKeySelect("男装");
                break;

            case R.id.txt_nvzhuang_syf://女装点击查询
                ByKeySelect("女装");
                break;

            case R.id.txt_nvxie_syf://女鞋点击查询
                ByKeySelect("女鞋");
                break;

            case R.id.txt_chenshan_syf://T恤点击查询
                ByKeySelect("T恤");
                break;

            case R.id.txt_meizhuanghufu_syf://美妆护肤点击查询
                ByKeySelect("美妆护肤");
                break;

            case R.id.txt_shoujishuma_syf://手机数码点击查询
                ByKeySelect("手机数码");
                break;

            case R.id.img_maoyi_syf://毛衣点击查询
                ByKeySelect("毛衣");
                break;

            case R.id.img_kuzhuang_syf://裤装点击查询
                ByKeySelect("裤装");
                break;

            case R.id.img_qunzhuang_syf://裙装点击查询
                ByKeySelect("裙装");
                break;

            case R.id.img_waitao_syf://外套点击查询
                ByKeySelect("外套");
                break;

            case R.id.img_weiyi_syf://卫衣点击查询
                ByKeySelect("卫衣");
                break;
            case R.id.simple_xiangqing_shyf://点击详情页的返回到
                //只需要切换界面
                mLlXiangQingShYF.setVisibility(View.GONE);  //说明点击后要隐藏详情
                mLlShopAllShYF.setVisibility(View.VISIBLE);
                isxiangqing = true;
                break;
        }
    }

    /**
     * 用户点击搜索返回按钮  进行页面切换的方法
     */
    private void getSearchShowAndHid() {
        //点击之后  将搜索框下方原有的布局隐藏 展示搜索之后的布局
        //点击后 设置为true
        isSYFSearch = !isSYFSearch;
        //点击后需要将视图展示出来  再次点击  隐藏
        if (isSYFSearch) {
            mRlShowDataSYF.setVisibility(View.GONE);
            mLlSearchDataSYF.setVisibility(View.VISIBLE);//搜索商品展示
            //调用P层方法  进行请求
            mPresenter.getKeySearchDataP(UserContent, page);
        } else {
            //调用P层方法  进行请求
            mPresenter.getKeySearchDataP(UserContent, page);
            mRlShowDataSYF.setVisibility(View.VISIBLE);
            mLlSearchDataSYF.setVisibility(View.GONE);
        }
    }

    //点击菜单 展示第一行  根据关键字查询
    public void ByKeySelect(String userContent) {
        //点击之后  将搜索框下方原有的布局隐藏 展示搜索之后的布局
        //点击后 设置为true
        isSYFSearch = !isSYFSearch;
        //点击后需要将视图展示出来  再次点击  隐藏
        if (isSYFSearch) {
            mRlShowDataSYF.setVisibility(View.GONE);//包裹所有商品的控件隐藏
            lvSyfTopRightmenu.setVisibility(View.GONE);//点击菜单栏的布局也隐藏
            mLlSearchDataSYF.setVisibility(View.VISIBLE);//搜索商品展示区域布局显示
            mXlvSearchDataSYF.setVisibility(View.VISIBLE);//展示搜索商品的RLv展示
            //调用P层方法  进行请求
            mPresenter.getKeySearchDataP(userContent, page);
        } else {
            mRlShowDataSYF.setVisibility(View.VISIBLE);
            mLlSearchDataSYF.setVisibility(View.GONE);
        }
    }

    /**
     * //4 实现接口后  必须实现的方法
     */
    //4.1 轮播图   实现方法一  vp+handler
    /*@Override
    public void BannerData(final List<BannerBean.ResultBean> list) {
        //如果请求数据成功
        *//*if (list != null) {
            mBeanList.clear();
            mBeanList.addAll(list);
            mBannerAdapter.notifyDataSetChanged();
        }*//*

    }*/
    @Override
    public void BannerData(BannerBean bannerBean) {
        //方法二  使用XBanner实现画廊效果
        if (bannerBean != null) {
            //传值
            xbanner.setData(bannerBean.getResult(), null);
            //设置adapter
            xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    //类型强zhuan
                    BannerBean.ResultBean bannerBean = (BannerBean.ResultBean) model;
                    //绑定数据
                    Glide.with(getActivity()).load(bannerBean.getImageUrl()).into((ImageView) view);
                    xbanner.setPageChangeDuration(1000);
                }
            });
        }
    }

    /**
     * 用户根据关键字keyword 进行查询商品
     */
    @Override
    public void KeySearchData(List<KeySeacherBean.ResultBean> list) {

        //先判断list的长度  用来判别是显示那个布局控件
        if (list.size() != 0) {
            mLlSearchData0SYF.setVisibility(View.GONE);//将提示没有查到商品的布局隐藏
            mXlvSearchDataSYF.setVisibility(View.VISIBLE);//要展示数据的 xlv展示出来
            if (list != null) {
                //如果有数据  就将 提示没有找到界面  隐藏
                mKeySearchList.clear();
                mKeySearchList.addAll(list);
                mKeySearchSelectAdapte.notifyDataSetChanged();
            }
        } else {
            //没有数据  就反
            mLlSearchData0SYF.setVisibility(View.VISIBLE);//将提示没有查到商品的布局展示
            mXlvSearchDataSYF.setVisibility(View.GONE);//要展示数据的 xlv隐藏
        }

    }

    //4.1 热销新品
    @Override
    public void ShopDataRXXP(List<ShopBean.ResultBean.RxxpBean.CommodityListBeanXX> list) {
        if (list != null) {
            mRxxplist.clear();
            mRxxplist.addAll(list);
            mReXiaoAdapte.notifyDataSetChanged();
        }
    }

    //4.2 魔力时尚
    @Override
    public void ShopDataMLSS(List<ShopBean.ResultBean.MlssBean.CommodityListBean> list) {
        if (list != null) {
            mMlssBeanList.clear();
            mMlssBeanList.addAll(list);
            mMoLiAdapte.notifyDataSetChanged();
        }
    }

    //4.3 品质生活
    @Override
    public void ShopDataPZSH(List<ShopBean.ResultBean.PzshBean.CommodityListBeanX> list) {
        if (list != null) {
            mPzshBeanList.clear();
            mPzshBeanList.addAll(list);
            mPinZhiAdapte.notifyDataSetChanged();
        }
    }

    //4.4 详情
    @Override
    public void XiangQingData(XiangQingBean xiangQingBean) {
        //轮播图
        if (xiangQingBean != null) {
            String message = xiangQingBean.getMessage();
            //Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
            Glide.with(this).load("http://172.17.8.100/images/small/commodity/nz/wy/7/2.jpg").into(mSimpleXiangQingShyf);
            mTxtTitleXiangQingShyf.setText(xiangQingBean.getResult().getCategoryName());
            mTxtJieShaoXiangQingShyf.setText(xiangQingBean.getResult().getCommodityName());
            mTxtPriceXiangQingShyf.setText("价格:￥" + xiangQingBean.getResult().getPrice());

            //加入购物车
            int commodityId = xiangQingBean.getResult().getCommodityId();
            commodityIds = commodityId;

        }
    }

    /**
     * 同步购物车
     *
     *
     * */
    @Override
    public void AddCar(AddCar addCar) {
        Toast.makeText(getActivity(),addCar.getMessage(),Toast.LENGTH_SHORT).show();
    }

    //查询购物车
    @Override
    public void getSelectShoppingData(List<GouWuCheBean.ResultBean> list) {
        String str="[";
            for (int i=0;i<list.size();i++){
                if (commodityIds==list.get(i).getCommodityId()){
                    int count = list.get(i).getCount();
                    count++;
                    list.get(i).setCount(count);
                    break;
                }/*else if (i==list.size()-1){
                    list.add(new GouWuCheBean.ResultBean(commodityIds,1));
                    break;
                }*/
            }
            for (GouWuCheBean.ResultBean sopCar_bean : list){
                str+="{\"commodityId\":"+sopCar_bean.getCommodityId()+",\"count\":"+sopCar_bean.getCount()+"},";
            }
            String substring = str.substring(0, str.length() - 1);
            substring+="]";
            mMap = new HashMap<>();
            mMap.put("data",substring);
            //mSopDetailPresenter.getPut(map,mUserId,mSessionId);
            mPresenter.getAddCarP(mSessionid,mUserid, mMap);
    }

    @Override
    public void failder(Exception e) {
        //如果请求失败  进行提示
        Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
    }

    //防止内存泄漏
    @Override
    public void onDestroy() {
        super.onDestroy();
        //presenter层
        if (mPresenter != null) {
            mPresenter.datach();
        }
        if (mGouWuChePresenter != null) {
            mGouWuChePresenter.datach();
        }

        //handler销毁
        handler.removeCallbacksAndMessages(null);
        handlerloding.removeCallbacksAndMessages(null);//搜索
    }
}
