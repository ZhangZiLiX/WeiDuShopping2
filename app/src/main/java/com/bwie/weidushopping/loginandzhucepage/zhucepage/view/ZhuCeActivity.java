package com.bwie.weidushopping.loginandzhucepage.zhucepage.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.loginandzhucepage.loginpage.view.LoginActivity;
import com.bwie.weidushopping.loginandzhucepage.zhucepage.bean.ZhuCeBean;
import com.bwie.weidushopping.loginandzhucepage.zhucepage.presenter.ZhuCePresenter;
import com.bwie.weidushopping.baseactivity.BaseActionBar;
import com.bwie.weidushopping.ourcommon.utils.NetWorkUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ZhuCeActivity extends BaseActionBar implements View.OnClickListener,IView {

    /**
     * 设置倒计时  跳转
     * */
    private int FLAG = 124;//handler倒计时
    private int time = 0;
    //使用一个Handler延迟发
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==FLAG){
                //如果时间大于零  就让时间减1
                if(time>0){
                    time--;
                    //为btn重新赋值
                    txtStartLoginYanzheng.setText("获取验证码("+time+"s)");
                    //重新发送
                    handler.sendEmptyMessageDelayed(FLAG,1000);
                }
            }
        }
    };


    private EditText etUsername;
    private EditText etUseryanzheng;
    private TextView txtStartLoginYanzheng;
    private EditText etUserpassword;
    private ImageView imgStartLoginPasswordeye;
    private TextView txtKuaisudenglu;
    private Button btnZhuce;
    private ZhuCePresenter mZhuCePresenter;
    private boolean iseye = true;//设置密码框  明文密文
    private EventHandler mEventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        //1 初始化数据
        initView();
        //2 初始化P层对象
        initPresenter();
        //3

    }

    /**
     * //2 初始化P层对象
     */
    private void initPresenter() {
        mZhuCePresenter = new ZhuCePresenter();
        mZhuCePresenter.attach(this);
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etUseryanzheng = (EditText) findViewById(R.id.et_useryanzheng);
        txtStartLoginYanzheng = (TextView) findViewById(R.id.txt_start_login_yanzheng);
        etUserpassword = (EditText) findViewById(R.id.et_userpassword);
        imgStartLoginPasswordeye = (ImageView) findViewById(R.id.img_start_login_passwordeye);
        etUserpassword.setInputType(129);//设置默认密文
        txtKuaisudenglu = (TextView) findViewById(R.id.txt_kuaisudenglu);//快速登录
        btnZhuce = (Button) findViewById(R.id.btn_zhuce);

        //点击监听
        btnZhuce.setOnClickListener(this);
        txtKuaisudenglu.setOnClickListener(this);
        txtStartLoginYanzheng.setOnClickListener(this);//短信验证
        imgStartLoginPasswordeye.setOnClickListener(this);//明文和密文设置

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zhuce://点击注册
                //首先判断网络
                boolean netWorkAvailable = NetWorkUtils.isNetWorkAvailable(this);
                if(!netWorkAvailable){
                    //如果为false则表示网络状态异常
                    Toast.makeText(this,"网络状态异常,请检查网络是否可用!",Toast.LENGTH_SHORT).show();
                    break;
                }


                //1 点击注册  首先判断非空
                //1.1得到用户输入的数据
                String phone = etUsername.getText().toString().trim();//手机号
                String usetYanZhengCode = etUseryanzheng.getText().toString().trim();//用户输入的验证码
                String password = etUserpassword.getText().toString().trim();//密码
                /**
                 * 正则表达式  进行输入判断
                 * */
                //2 正则表达式判断手机号  //匹配正整数
                Pattern p = Pattern.compile("^[1-9]\\d*");
                Matcher phonez = p.matcher(phone);
                boolean matchespho = phonez.matches();
                if(!matchespho){
                    Toast.makeText(ZhuCeActivity.this,"手机号只由数字组成！",Toast.LENGTH_SHORT).show();
                    break;
                }
                Matcher yanzhengcodez = p.matcher(usetYanZhengCode);
                boolean matchesyzc = yanzhengcodez.matches();
                if(!matchesyzc){
                    Toast.makeText(ZhuCeActivity.this,"验证码只能是由数字组成！",Toast.LENGTH_SHORT).show();
                    break;
                }
                //3 正则表达式判断输入密码(字母开头，允许5-16字节，允许字母数字下划线)
                /*Pattern pwd = Pattern
                        .compile("^[a-zA-Z][a-zA-Z0-9_]{4,15}");
                Matcher passwordz = pwd.matcher(password);
                boolean matchespwd = passwordz.matches();
                if(!matchespwd){
                    Toast.makeText(ZhuCeActivity.this,"设置密码必须是字母开头，允许5-16字节，允许字母数字下划线！",Toast.LENGTH_SHORT).show();
                    break;
                }*/

                //判断非空
                if(phone.length()==0){
                    Toast.makeText(ZhuCeActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(phone.length()!=11){
                   Toast.makeText(ZhuCeActivity.this,"对不起！手机号只能为11位,请重新填写！",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(usetYanZhengCode.length()==0){
                    Toast.makeText(ZhuCeActivity.this,"验证码不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(usetYanZhengCode.length()!=4){//验证码  必须是四位
                    Toast.makeText(ZhuCeActivity.this,"对不起！请填写正确的验证码！",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(password.length()==0){
                    Toast.makeText(ZhuCeActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                    break;
                }
                //1.2 不等于空就  进行调用P层的方法
                if(time==0){
                    mZhuCePresenter.getZhuCeDataP(phone, password);
                }
                break;

            case R.id.txt_kuaisudenglu://点击快速登录

                //并进行跳转到登录界面  进行登录
                Intent intent = new Intent(ZhuCeActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.img_start_login_passwordeye://设置密码框明文密文
                if (iseye) {
                    //密文设置 129是固定的 128是在布局中设置的，这里设置128不管用
                    etUserpassword.setInputType(129);
                    iseye = false;
                } else {
                    //设置从明文到密文
                    etUserpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iseye = true;
                }
                break;

            case R.id.txt_start_login_yanzheng://短信验证
                if(time == 0){
                   time = 20;
                }
                //首先得到用戶輸入的手机号
                String phone2 = etUsername.getText().toString().trim();//手机号
                if(phone2.length()==0){
                    Toast.makeText(ZhuCeActivity.this,"手机号不能为空,请填写！",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(phone2.length()!=11){
                    Toast.makeText(ZhuCeActivity.this,"对不起！手机号只能为11位,请重新填写！",Toast.LENGTH_SHORT).show();
                    break;
                }
                //定义一个变量用来表示倒计时
                handler.sendEmptyMessageDelayed(FLAG,1000);
                DuanXin(phone2);//调用短信验证方法
                break;
        }
    }

    /**
     * 3 实现接口后  必须实现的方法
     */
    @Override
    public void zhuCeBean(ZhuCeBean zhuCeBean) {
        if (zhuCeBean != null) {
            String message = zhuCeBean.getMessage();
            if (message.equals("注册成功")) {
                //如果注册成功  就进行提示成功
                Toast.makeText(ZhuCeActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                //并进行跳转到登录界面  进行登录
                Intent intent = new Intent(ZhuCeActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                //不成功也提示注册失败
                Toast.makeText(ZhuCeActivity.this, "" + message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failder(Exception e) {
        //如果失败  提示原因
        Toast.makeText(ZhuCeActivity.this, "" + e, Toast.LENGTH_SHORT).show();
    }


    /**
     * 短息验证
     *
     */
    private void DuanXin( String phone2) {
        // 在尝试读取通信录时以弹窗提示用户（可选功能）
        SMSSDK.setAskPermisionOnReadContact(true);

        // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
        // TODO 处理成功得到验证码的结果
        // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
        // TODO 处理错误的结果
        // TODO 处理验证码验证通过的结果
        // TODO 处理错误的结果
        // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
        mEventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                new Handler(Looper.getMainLooper(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        int event = msg.arg1;
                        int result = msg.arg2;
                        Object data = msg.obj;
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            if (result == SMSSDK.RESULT_COMPLETE) {
                                // TODO 处理成功得到验证码的结果
                                // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                            } else {
                                // TODO 处理错误的结果
                                ((Throwable) data).printStackTrace();
                            }
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            if (result == SMSSDK.RESULT_COMPLETE) {
                                // TODO 处理验证码验证通过的结果
                            } else {
                                // TODO 处理错误的结果
                                ((Throwable) data).printStackTrace();
                            }
                        }
                        // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                        return false;
                    }
                }).sendMessage(msg);
            }
        };
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(mEventHandler);

        // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
        SMSSDK.getVerificationCode("86", phone2);

        // 提交验证码，其中的code表示验证码，如“1357”
        SMSSDK.submitVerificationCode("86", "15910975491", "2526");

        // 使用完EventHandler需注销，否则可能出现内存泄漏
    }


    /**
     * 4 防止内存泄漏
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //p层
        if(mZhuCePresenter!=null){
            mZhuCePresenter.datach();
        }
        //短信验证
        SMSSDK.unregisterEventHandler(mEventHandler);
        //Handler注销
        handler.removeCallbacksAndMessages(null);
    }

}
