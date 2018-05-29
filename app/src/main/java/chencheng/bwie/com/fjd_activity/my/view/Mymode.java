package chencheng.bwie.com.fjd_activity.my.view;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import chencheng.bwie.com.fjd_activity.my.bean.OrdersBean;
import chencheng.bwie.com.fjd_activity.my.bean.UpOrdersBean;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class Mymode implements Imode {

    //获取购物车数据的方法
    Okhttp okhttp=new Okhttp();
    @Override
    public void getnetjsonbean(String uri, Map<String, String> map, final Gouwuchelisteneter gouwuchelisteneter) {
        okhttp.getnetpost(uri,map);
        okhttp.setOnOKHttpGetListener(new Okhttp.OkhttpgetListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(String json) {
                Gson gson = new Gson();
                OrdersBean goodbean = gson.fromJson(json, OrdersBean.class);
                List<OrdersBean.DataBean> data = goodbean.getData();
                gouwuchelisteneter.gouwucheListeneter(data);

            }
        });

    }

    //修改的方法
    @Override
    public void xiugai(String uri, Map<String, String> map, final Xiugailisteneter xiugailisteneter) {
        okhttp.getnetpost(uri,map);
        okhttp.setOnOKHttpGetListener(new Okhttp.OkhttpgetListener() {
            @Override
            public void error(String error) {

            }

            @Override
            public void success(String json) {
                Gson gson = new Gson();
                UpOrdersBean upGoodbean = gson.fromJson(json, UpOrdersBean.class);
                xiugailisteneter.xiugaiListeneter(upGoodbean);

            }
        });
    }
}