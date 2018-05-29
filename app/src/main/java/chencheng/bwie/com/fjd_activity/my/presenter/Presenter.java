package chencheng.bwie.com.fjd_activity.my.presenter;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chencheng.bwie.com.fjd_activity.my.bean.OrdersBean;
import chencheng.bwie.com.fjd_activity.my.bean.UpOrdersBean;
import chencheng.bwie.com.fjd_activity.my.view.Gouwuchelisteneter;
import chencheng.bwie.com.fjd_activity.my.view.Imode;
import chencheng.bwie.com.fjd_activity.my.view.Iview;
import chencheng.bwie.com.fjd_activity.my.view.JIekou;
import chencheng.bwie.com.fjd_activity.my.view.Xiugailisteneter;

/**
 * @Creation date
 * @name
 * @Class action
 */

public class Presenter implements Ipresenter {


    @Override
    public void getmv(final Context context, final Iview iview, Imode imode) {

        Map<String,String> map=new HashMap<>();
        map.put("uid","71");
        map.put("page","1");
        imode.getnetjsonbean(JIekou.net, map, new Gouwuchelisteneter() {
            @Override
            public void gouwucheListeneter(List<OrdersBean.DataBean> data) {
                //调用v层设置适配器的代码
                iview.setmyadapter(context,data);

            }
        });

    }

    //上啦加载更多的方法
    @Override
    public void pjiazaigengduo(final Context context, final Iview iview, Imode imode) {
        int i=1;
        i++;
        Map<String,String> map=new HashMap<>();
        map.put("uid","71");
        map.put("page",""+i);
        imode.getnetjsonbean(JIekou.net, map, new Gouwuchelisteneter() {
            @Override
            public void gouwucheListeneter(List<OrdersBean.DataBean> data) {
                iview.vjiazaigengduo(context,data);
            }
        });

    }

    @Override
    public void upgood(final Context context, final Iview iview, final Imode imode, int id) {

        Map<String,String> map=new HashMap<>();
        map.put("uid","71");
        map.put("orderId",id+"");
        map.put("status","2");

        imode.xiugai(JIekou.update, map, new Xiugailisteneter() {
            @Override
            public void xiugaiListeneter(UpOrdersBean upGoodbean) {
                //如果成功从新设置适配器
                if (upGoodbean.getCode().equals("0")){
                    Map<String,String> map=new HashMap<>();
                    map.put("uid","71");
                    map.put("page","1");
                    imode.getnetjsonbean(JIekou.net, map, new Gouwuchelisteneter() {
                        @Override
                        public void gouwucheListeneter(List<OrdersBean.DataBean> data) {
                            //调用v层设置适配器的代码
                            iview.setmyadapter(context,data);

                        }
                    });
                    Log.d("成功", "xiugaiListeneter: 开心");
                }else {
                    Log.d("失败", "xiugaiListeneter: 大哭");
                }

            }
        });


    }
    //点击图片时  从新设置适配器
    @Override
    public void diantu(Context context, Iview iview, Imode imode, List<OrdersBean.DataBean> list1) {
        //调用v层设置适配器的方法   新的适配器的方法
        iview.settuadapter(context,list1);

    }

}
