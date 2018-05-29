package chencheng.bwie.com.fjd_activity.my.view;

import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OKHttpUtils {
    private UpListener upListener;
    private MyHandler myHandler = new MyHandler();
    //单利
    private static OKHttpUtils okHttpUtils = null;


    public static OKHttpUtils getInstance() {
        if (okHttpUtils == null) {
            okHttpUtils = new OKHttpUtils();
        }
        return okHttpUtils;
    }


    //上传头像
    public void upLoadFile(String url, File file, final Map<String, String> map) {


        //设置超时时间及缓存
        OkHttpClient mOkHttpClient = new OkHttpClient();
        MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //媒体类型
        final MediaType mediaType = MediaType.parse("image/png");
        //向mBody中添加文件，也就是我们的图片
        mbody.addFormDataPart("file", file.getName(), RequestBody.create(mediaType, file));
        //添加其他参数
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String value = map.get(key);
            mbody.addFormDataPart(key,value);
        }
        //闭环
        RequestBody requestBody = mbody.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();


        mOkHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Message message = myHandler.obtainMessage();
                message.what = 0;
                message.obj = e.getMessage();
                myHandler.sendMessage(message);
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Message message = myHandler.obtainMessage();
                message.what = 1;
                message.obj = response.body().string();
                myHandler.sendMessage(message);
            }
        });


    }


    //外部访问的方法
    public void setUpListene(UpListener upListener) {
        this.upListener = upListener;
    }


    //handler
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String e = (String) msg.obj;
                    upListener.upError(e);
                    break;


                case 1:
                    String json = (String) msg.obj;
                    upListener.upSuccess(json);
                    break;
            }
        }
    }
}
