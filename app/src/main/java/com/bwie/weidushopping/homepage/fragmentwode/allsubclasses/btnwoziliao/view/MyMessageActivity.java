package com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.weidushopping.R;
import com.bwie.weidushopping.baseactivity.BaseActionBar;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.bean.UpdatePasswordBean;
import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.presenter.ZiLiaoPresenter;
import com.bwie.weidushopping.loginandzhucepage.loginpage.bean.LoginBean;
import com.bwie.weidushopping.loginandzhucepage.loginpage.presenter.LoginPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;

/**
 * 我的资料查看
 *
 *  只需要调用登录对象的数据
 *  实现登录的接口  得到数据
 *
 */
public class MyMessageActivity extends BaseActionBar implements com.bwie.weidushopping.loginandzhucepage.loginpage.view.IView, com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwoziliao.view.IView, View.OnClickListener {

    private SimpleDraweeView simpleTouxiang;
    private TextView txtUsername;
    private TextView txtUserpassword;
    private LoginPresenter mLoginPresenter;
    private String mPassword;
    //上传头像设置
    private File imgRoot;
    private Intent intent;
    private Uri uri;
    private static final int FLAG_CAMERA_REQUEST = 100;//裁剪 跳转回传码值
    private static final int FLAG_ALUMB_REQUEST = 101;//相册裁剪 跳转回传码值
    private static final int FLAG_CROP_REQUEST =102;//拍照后直接返回 裁剪
    private AlertDialog.Builder mBuilder;
    private ZiLiaoPresenter mZiLiaoPresenter;
    private SharedPreferences mIsUserMessageSP;
    private String mPhone;
    private LinearLayout mLlZiLiaoKuangWDF;
    private LinearLayout mLlUpdatePwdWDF;
    private EditText mEtNewPwd;
    private Button mBtnUpdatePwd;
    private SharedPreferences mIsUserNewPwd;
    private SharedPreferences mIsonelogin;
    private String mIsuserid;
    private String mIsSessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        //1 初始化控件
        initView();
        //2 登录的p层的重新调用
        initPresenter();
        //3 从登录界面sp存储中得到密码等数据
        initSp();
        //4 上传头像模块
        getUpTouXiang();

    }

    /**
     *  覆写 onActivityResult方法  进行头像响应点击
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //1 如果返回的是定义的100FLAG_CAMERA_REQUEST
        if(requestCode==FLAG_CAMERA_REQUEST){//拍照裁剪上传
            //2 就得到照片后进行裁剪
            intent = crop(uri);
            //3 跳转 到裁剪
            startActivityForResult(intent, FLAG_CROP_REQUEST);
        }else if(requestCode == FLAG_ALUMB_REQUEST){//相册中裁剪上传
            uri = data.getData();//从相册中返回值
            //裁剪 方法
            intent = crop(uri);
            //3 跳转 到裁剪
            startActivityForResult(intent, FLAG_CROP_REQUEST);
        }else if(requestCode == FLAG_CROP_REQUEST){
            // 从返回值中直接获取bitmap
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            simpleTouxiang.setImageBitmap(bmp);
            //上传头像  得到Bitmap  将其存储到本地的sp中 方法
            saveBitmapToSharedPreferences(bmp);
        }
    }

    /**
     * //上传头像  得到Bitmap  将其存储到本地的sp中
     * */
    private void saveBitmapToSharedPreferences(Bitmap bmp) {
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("testSP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("image", imageString);
        editor.commit();
        //上传头像方法
        setImgByStr(imageString,"");
    }

    /**
     * //上传头像方法
     * 此处使用用的OKHttp post请求上传的图片
     * */
    private void setImgByStr(String imageString, String s) {

        //注意  上传头像的接口有问题

        //使用p层调用post请求方法  传值

        //在我的界面通过sp  得到存储到本地的头像
    }

    /**
    * //3 Presenter对象创建
    * */
    private void initPresenter() {
        //登录的p层  初始化对象  传输密码使用的
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attach(this);

        //资料的Presenter对象
        mZiLiaoPresenter = new ZiLiaoPresenter();
        mZiLiaoPresenter.attach(this);
    }

    /**
     * //2  设置SP
     * */
    private void initSp() {
        //从登录界面sp存储中得到密码等数据
        mIsUserMessageSP = getSharedPreferences("isUserMessageSP", MODE_PRIVATE);//初始化登录时记住密码手机号的spd对象
        mPhone = mIsUserMessageSP.getString("isUserMessagePhone", "");//手机号
        mPassword = mIsUserMessageSP.getString("isUserMessagePassword", "");//密码
        if(mPhone !=null&& mPassword !=null){
            //重新调用登录P层的方法
            mLoginPresenter.getLoginDataP(mPhone, mPassword);
        }

        //从登陆几面拿到登陆后的seeeionid 他是不断变化的
        mIsonelogin = getSharedPreferences("isonelogin", MODE_PRIVATE);
        mIsuserid = mIsonelogin.getString("isuserid", "");
        mIsSessionId = mIsonelogin.getString("isSessionId", ""); //得到不断变化的sessionid
    }

    /**
     * //1 初始化控件
     * */
    private void initView() {
        simpleTouxiang = (SimpleDraweeView) findViewById(R.id.simple_touxiang);//头像
        txtUsername = (TextView) findViewById(R.id.txt_username);//昵称
        txtUserpassword = (TextView) findViewById(R.id.txt_userpassword);//密码
        mLlZiLiaoKuangWDF = findViewById(R.id.ll_ziliaoKuang_wdf);//存放所有资料的容器（密码等）
        mLlUpdatePwdWDF = findViewById(R.id.ll_updatapwd_wdf);//存放修改资料的容器（密码）
        mEtNewPwd = findViewById(R.id.et_newpwd);//输入的新密码
        mBtnUpdatePwd = findViewById(R.id.btn_updatepwd);//确认的新密码


        //点击进行修改  事件监听
        simpleTouxiang.setOnClickListener(this);
        txtUsername.setOnClickListener(this);
        txtUserpassword.setOnClickListener(this);
        mBtnUpdatePwd.setOnClickListener(this);

    }

    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.simple_touxiang://头像
               //提示用户是从相册还是相机进行上  点击调用方法
               TouXiangOnClick();
               break;

           case R.id.txt_username://点击昵称修改

               break;

           case R.id.txt_userpassword://点击密码修改
               //首先得到现在的密码  从上面的loginsp中直接得到  oldPwd老密码
               mLlUpdatePwdWDF.setVisibility(View.VISIBLE);//展示修改密码的布局
               mLlZiLiaoKuangWDF.setVisibility(View.GONE);//隐藏现在的布局
               break;

           case R.id.btn_updatepwd://点击确认密码修改
                 //得到新密码
               String newPwd = mEtNewPwd.getText().toString().trim();

               if(newPwd==null || newPwd.equals("")){
                   Toast.makeText(MyMessageActivity.this,"密码不能为空,修改密码失败!",Toast.LENGTH_SHORT).show();
                   break;
               }
               //调用网路请求工具类的put方法 进行请求
               mZiLiaoPresenter.getUpdatePassword(mPassword,mIsuserid,mIsSessionId,newPwd);
               //点击确定  界面修饰  资料框展示
               mLlUpdatePwdWDF.setVisibility(View.GONE);
               mLlZiLiaoKuangWDF.setVisibility(View.VISIBLE);
               break;
       }
    }


    /**
     * 实现登录的方法后  得到数据赋值
     * */
    @Override
    public void LoginBean(LoginBean loginBean) {
        if(loginBean!=null){
            if(loginBean.getMessage().equals("登录成功")){
                Uri uri = Uri.parse(loginBean.getResult().getHeadPic());
                simpleTouxiang.setImageURI(uri);
                txtUsername.setText(loginBean.getResult().getNickName());
                txtUserpassword.setText(mPassword);//通过sp拿到的
            }
        }
    }


    /**
     * 实现修改的IView接口必须实现的方法
     * */
    @Override
    public void updatePassword(UpdatePasswordBean updatePasswordBean) {
        //修改密码方法
        if(updatePasswordBean!=null){
            Toast.makeText(this,""+updatePasswordBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failder(Exception e) {
        //请求失败  进行提示
        Toast.makeText(this,""+e,Toast.LENGTH_SHORT).show();
    }

    /**
     * 5 上传头像设置
     * */
    private void getUpTouXiang() {
        //设置一个存储文件
        //如果SDka已经挂载
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //获取根目录
            File rootSD = Environment.getExternalStorageDirectory();
            //创建文件存储文件包(File.separator 代表反斜杠)
            imgRoot = new File(rootSD.getAbsolutePath()+File.separator+"imgs");
            //判断该文件是否存在
            if(!imgRoot.exists()){
                //如果不存在就创建
                imgRoot.mkdirs();
            }
        }
    }

    /**
     * 头像点击设置
     * */
    private void TouXiangOnClick() {
        mBuilder = new AlertDialog.Builder(MyMessageActivity.this);
        mBuilder.setTitle("温馨提示：");
        mBuilder.setMessage("请选择上传方式:");
        mBuilder.setPositiveButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击选择从相册选择
                Toast.makeText(MyMessageActivity.this,"拍照上传",Toast.LENGTH_SHORT).show();
                //1 参数 dirPath路径, name 名字---创建一个时间 用来当做图片名字(防止覆盖)
                File f = new File(imgRoot,new Date().getTime()+".jpg");
                //2 获取uri
                uri = Uri.fromFile(f);
                //3 使用系统相机的隐式跳转
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //4 设置拍照输出路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                //5 start直接开启 还需要给他设置一个拍照后的保存路径
                startActivityForResult(intent, FLAG_CAMERA_REQUEST);
                dialog.dismiss();
            }
        });
        mBuilder.setNegativeButton("相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击从相机拍照
                Toast.makeText(MyMessageActivity.this, "请选择", Toast.LENGTH_SHORT).show();
                // 相册跳转
                intent = new Intent(Intent.ACTION_PICK);
                //设置类型通配符
                intent.setType("image/*");
                //开启
                startActivityForResult(intent, FLAG_ALUMB_REQUEST);
                dialog.dismiss();
            }
        });
        mBuilder.show();
    }


    /**
     * 裁剪方法
     *
     */
    private Intent crop(Uri uri) {
        // 隐式Intent，调用系统的裁剪
        Intent intent = new Intent("com.android.camera.action.CROP");
        // 设置裁剪的数据源和数据类型
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");// 可裁剪
        // 裁剪的宽高比例
        intent.putExtra("aspectX", 1); // 裁剪的宽比例
        intent.putExtra("aspectY", 1); // 裁剪的高比例

        // 裁剪的宽度和高度
        intent.putExtra("outputX", 300); // 裁剪的宽度
        intent.putExtra("outputY", 300); // 裁剪的高度
        // 可省略
        intent.putExtra("scale", true); // 支持缩放
        // 裁剪之后保存的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(uri.getPath() + ".bak")); // 将裁剪的结果输出到指定的Uri
        // 必须加，否则返回值中找不到返回的值
        intent.putExtra("return-data", true); // 若为true则表示返回数据
        // 可以省略
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 裁剪成的图片的格式
        // intent.putExtra("noFaceDetection", true); //启用人脸识别
        return intent;
}



    /**
     * 防止内存泄漏
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //登录的presenter
        if(mLoginPresenter!= null){
            mLoginPresenter.datach();
        }
        //资料
        if(mZiLiaoPresenter!=null){
            mZiLiaoPresenter.datach();
        }
    }

}
