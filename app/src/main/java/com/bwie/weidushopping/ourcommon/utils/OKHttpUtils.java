package com.bwie.weidushopping.ourcommon.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;


import com.bwie.weidushopping.ourcommon.inter.ICallBack;
import com.bwie.weidushopping.ourcommon.interceptor.MyInterceptor;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * date:2018/11/14
 * author:张自力(DELL)
 * function: 网络请求工具类
 */

public class OKHttpUtils {

    //1 使用单例模式
    private static volatile OKHttpUtils instance;
    private Handler handler = new Handler(Looper.getMainLooper());
    private OkHttpClient okHttpClient;

    //2 单例  私有化一个构造方法
    public OKHttpUtils() {
        //1 OKHttp添加公共参数懂啊拦截器中
        /*Map<String,String> map = new HashMap<>();
        map.put("source","android");
        //将自定义的拦截器对象初始化
        MyInterceptor myInterceptor = new MyInterceptor(map);*/

        //2 日志拦截器
        // 日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        // 新建日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.i("http请求参数：", message);
            }
        });
        loggingInterceptor.setLevel(level);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000,TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)//日志拦截器
                //.addInterceptor(myInterceptor)//拦截器设置
                .build();
    }
    //3 单例  对外提供一个方法
    public static OKHttpUtils getInstance(){
       if(instance == null){
           synchronized (OKHttpUtils.class){
               if(instance == null){
                   //就创建出对象
                   instance = new OKHttpUtils();
               }
           }
       }
       return instance;
    }

    //定义一个网路请求数据的   get() 方法
    public void getData(String url, final ICallBack callBack, final Type type){
        //1 在单例模式中 初始化一个OK对象
        //2 始化一个request对象  并设置相关属性
        Request request = new Request.Builder()
                .get()
                .url(url)
                //.addHeader("userid", String.valueOf(175))
               // .addHeader("sessionid","1544619570901175")//添加请求头参数
                .build();
        //3 通过OK对象  和  request对象  得到一个call
        Call call = okHttpClient.newCall(request);
        //4 通过call对象  调用同步或异步方法进行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
               //通过Handler发送到主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.Failder(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               //成功  通过Handler发送到主线程
                //通过response得到数据类型Object
                String result = response.body().string();
                //使用gson解析
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.Success(o);
                    }
                });
            }
        });
    }

    //定义一个购物车 网路请求数据的   get() 方法
    public void getGouWuCheData(String url,int userId,String sessionId, final ICallBack callBack, final Type type){
        //1 在单例模式中 初始化一个OK对象
        //2 始化一个request对象  并设置相关属性
        Request request = new Request.Builder()
                .get()
                .addHeader("userId", userId+"")
                .addHeader("sessionId",sessionId)//添加请求头参数
                .url(url)
                .build();
        //3 通过OK对象  和  request对象  得到一个call
        Call call = okHttpClient.newCall(request);
        //4 通过call对象  调用同步或异步方法进行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //通过Handler发送到主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.Failder(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功  通过Handler发送到主线程
                //通过response得到数据类型Object
                String result = response.body().string();
                //使用gson解析
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.Success(o);
                    }
                });
            }
        });
    }


    //定义一个网路请求数据的   post() 方法
    public void getDataPost(String url, HashMap<String,String> map, final ICallBack callBack, final Type type){
        //1 创建FormBody的对象，把表单添加到formBody中
        FormBody.Builder builder = new FormBody.Builder();
        //2 判断集合对象不为空
        if(map!=null){
            //遍历map中的key值
            for(String key : map.keySet()){
                builder.add(key,map.get(key));
            }
        }
        //3 得到一个formbody体
        FormBody formBody = builder.build();

        //4 在单例模式中 初始化一个OK对象
        //5 始化一个request对象  并设置相关属性
        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
        //6 通过OK对象  和  request对象  得到一个call
        Call call = okHttpClient.newCall(request);
        //7 通过call对象  调用同步或异步方法进行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(callBack!=null){
                    //通过Handler发送到主线程
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.Failder(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功  通过Handler发送到主线程
                if(response !=null && response.isSuccessful()){
                    //通过response得到数据类型Object
                    String result = response.body().string();
                    //使用gson解析
                    Gson gson = new Gson();
                    final Object o = gson.fromJson(result, type);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(callBack!=null){
                                callBack.Success(o);
                            }
                        }
                    });
                }
            }
        });
    }


    //定义一个网路请求数据的   post() 方法  加入头参
    public void getDataTouCanPostU(String url,HashMap<String,String> map,int userId,String sessionId, final ICallBack callBack, final Type type){
        //1 创建FormBody的对象，把表单添加到formBody中
        FormBody.Builder builder = new FormBody.Builder();
        //2 判断集合对象不为空
        if(map!=null){
            //遍历map中的key值
            for(String key : map.keySet()){
                builder.add(key,map.get(key));
            }
        }
        //3 得到一个formbody体
        FormBody formBody = builder.build();
        //4 在单例模式中 初始化一个OK对象
        //5 始化一个request对象  并设置相关属性
        Request request = new Request.Builder()
                .post(formBody)
                .addHeader("userId",userId+"")
                .addHeader("sessionId",sessionId)
                .url(url)
                .build();
        //6 通过OK对象  和  request对象  得到一个call
        Call call = okHttpClient.newCall(request);
        //7 通过call对象  调用同步或异步方法进行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(callBack!=null){
                    //通过Handler发送到主线程
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.Failder(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功  通过Handler发送到主线程
                if(response !=null && response.isSuccessful()){
                    //通过response得到数据类型Object
                    String result = response.body().string();
                    //使用gson解析
                    Gson gson = new Gson();
                    final Object o = gson.fromJson(result, type);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(callBack!=null){
                                callBack.Success(o);
                            }
                        }
                    });
                }
            }
        });
    }

    //上传头像 post
    public void getHeadPostImage(String url, File img, int userId, String sessionId, final ICallBack iCallBack, final Type type){
        MultipartBody responseBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", img.getName(), RequestBody.create(MediaType.parse("image/png"), img))
                .build();

        Request request = new Request.Builder()
                .post(responseBody)
                .addHeader("userId", userId+"")
                .addHeader("sessionId", sessionId)
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.Failder(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(json, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCallBack.Success(o);
                    }
                });
            }
        });
    }


    //使用put方式  进行提交
    //定义一个网路请求数据的   put() 方法
    public void getDataPut(String url,int isuserid,String sessionId, HashMap<String,String> map, final ICallBack callBack, final Type type){
        //1 创建FormBody的对象，把表单添加到formBody中
        FormBody.Builder builder = new FormBody.Builder();
        //2 判断集合对象不为空
        if(map!=null){
            //遍历map中的key值
            for(String key : map.keySet()){
                builder.add(key,map.get(key));
            }
        }
        //3 得到一个formbody体
        FormBody formBody = builder.build();

        Log.i("utils", "getDataPut: "+sessionId);
        Log.i("utils", "getDataPut: "+isuserid);
        //4 在单例模式中 初始化一个OK对象
        //5 始化一个request对象  并设置相关属性
        Request request = new Request.Builder()
                .addHeader("userId", isuserid+"")
                .addHeader("sessionId",sessionId)//添加请求头参数  这个id是随时变更的
                .put(formBody)//使用Put请求方式  值需要改这里即可
                .url(url)
                .build();
        //6 通过OK对象  和  request对象  得到一个call
        Call call = okHttpClient.newCall(request);
        //7 通过call对象  调用同步或异步方法进行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                if(callBack!=null){
                    //通过Handler发送到主线程
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.Failder(e);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //成功  通过Handler发送到主线程
                if(response !=null && response.isSuccessful()){
                    //通过response得到数据类型Object
                    String result = response.body().string();
                    //使用gson解析
                    Gson gson = new Gson();
                    final Object o = gson.fromJson(result, type);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(callBack!=null){
                                callBack.Success(o);
                            }
                        }
                    });
                }
            }
        });
    }


}
