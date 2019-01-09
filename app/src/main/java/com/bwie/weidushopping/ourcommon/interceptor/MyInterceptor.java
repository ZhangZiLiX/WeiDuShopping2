package com.bwie.weidushopping.ourcommon.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * date:2018/11/28
 * author:张自力(DELL)
 * function:  拦截器  公共参数
 */

public class MyInterceptor implements Interceptor {
    Map<String,String> map;
    /**
     * 1 构造方法传递公共参数(从外部传过来的)
     *
     */
    public MyInterceptor(Map<String,String> map) {
        this.map = map;
    }

    /**
     * //2 实现Interceptor的方法
     * */
    @Override
    public Response intercept(Chain chain) throws IOException {
        //拿到原来的request对象
        Request oldRequest = chain.request();
        //通过resuest拿到请求的url
        String url = oldRequest.url().toString();

        //判断请求方式（get 或者  post）
        /**
         * 2.1 get  请求方式
         * */
        if(oldRequest.method().equalsIgnoreCase("GET")){
            //判断非空你
            if(map!= null && map.size()>0){
                StringBuilder urlBuilder = new StringBuilder(url);
                //拼接公共请求参数
                for(Map.Entry<String,String> entry : map.entrySet()){
                    //公共参数拼接
                    urlBuilder.append("&" + entry.getKey() + "=" + entry.getValue());
                }
                url = urlBuilder.toString();
                //如果之前的url没有？号，我们需要手动给他添加一个？号
                if(!url.contains("?")){
                    url = url.replaceFirst("&","?");
                }

                //依据原来的request构造一个新的request
                // 重定向
                Request request = oldRequest.newBuilder()
                        .url(url)
                        .build();
                return chain.proceed(request);
            }
        }else{
            /**
             * 2.2 post請求
             * */
          //判断非空
            if(map != null && map.size()>0){
                //得到请求体
                RequestBody body = oldRequest.body();
                if(body != null && body instanceof FormBody){
                    FormBody formBody = (FormBody) body;
                    //把原理body里面的参数添加到新的body中
                    FormBody.Builder builder = new FormBody.Builder();
                    //为了防止重复添加相同参数到Body里
                    Map<String, String> temMap = new HashMap<>();
                    //遍历
                    for (int x = 0; x < formBody.size() ; x++) {
                        builder.add(formBody.encodedName(x),formBody.encodedValue(x));
                        temMap.put(formBody.encodedName(x),formBody.encodedValue(x));
                    }
                    //2.把公共请求参数添加到新的body中
                    for(Map.Entry<String,String> entry : map.entrySet()){
                       if(!temMap.containsKey(entry.getKey())){
                           builder.add(entry.getKey(),entry.getValue());
                       }
                    }
                    FormBody newFormBody = builder.build();
                    //依据原来的request构造一个新的request
                    Request newRequest = oldRequest.newBuilder()
                            .post(newFormBody)
                            .build();
                    return chain.proceed(newRequest);
                }
            }
        }
        return chain.proceed(oldRequest);
    }
}
