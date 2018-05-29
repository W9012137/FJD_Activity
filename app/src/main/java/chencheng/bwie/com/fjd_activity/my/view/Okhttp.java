package chencheng.bwie.com.fjd_activity.my.view;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class Okhttp {
    public static Okhttp okposthttp = null;
    public static Okhttp getInstance() {

        if (okposthttp == null) {
            okposthttp = new Okhttp();
        }
        return okposthttp;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int i=msg.what;
            //等于0失败
            if (i==0){
                String shi = (String) msg.obj;
                okhttpgetListener.error(shi);
            }
            //等于1成功
            if (i==1){
                String json = (String) msg.obj;
                okhttpgetListener.success(json);
            }
        }
    };
    private OkhttpgetListener okhttpgetListener;

    public void getnetpost(String url,Map<String, String> params){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new MyInter()).build();

        //3.x版本post请求换成FormBody 封装键值对参数
        FormBody.Builder builder = new FormBody.Builder();

        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }

        Request request = new Request.Builder().url(url).post(builder.build()).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message=new Message();
                message.obj="处理失败";
                message.what=0;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message=new Message();
                message.obj=string;
                message.what=1;
                handler.sendMessage(message);
            }
        });
    }

    public interface OkhttpgetListener{
        //失败的方法
        void error(String error);
        //成功的方法
        void success(String json);
    }

    //主线程获取网络解析后json的方法
    public void setOnOKHttpGetListener(OkhttpgetListener okhttpgetListener){
        this.okhttpgetListener=okhttpgetListener;
    }

    public void okgetnet(String url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new MyInter()).build();
        //创建请求对象
        Request request = new Request.Builder().url(url).build();
        //创建Call请求队列
        //请求都是放到一个队列里面的
        Call call = okHttpClient.newCall(request);
        //开始请求
        call.enqueue(new Callback() {
            //处理失败的方法
            @Override
            public void onFailure(Call call, IOException e) {
                Message message=new Message();
                message.obj="处理失败";
                message.what=0;
                handler.sendMessage(message);
            }
            //处理成功的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Message message=new Message();
                message.obj=string;
                message.what=1;
                handler.sendMessage(message);

            }
        });
    }

    //拦截器
    class MyInter implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取原来的body
            Request request = chain.request();
            RequestBody body = request.body();
            if (body instanceof FormBody) {
                //遍历原来的所有参数，加到新的Body里面，最后将公共参数加到新的Body
                FormBody.Builder newBuilder = new FormBody.Builder();
                for (int i = 0; i < ((FormBody) body).size(); i++) {
                    String name = ((FormBody) body).name(i);
                    String value = ((FormBody) body).value(i);

                    //放入新的
                    newBuilder.add(name, value);
                }
                //在将公共参数添加进去
                newBuilder.add("source", "android");
                FormBody newBody = newBuilder.build();
                //创建新的请求
                Request newRequest = request.newBuilder().post(newBody).build();
                Response response = chain.proceed(newRequest);
                return response;
            }

            return null;
        }
    }

}
