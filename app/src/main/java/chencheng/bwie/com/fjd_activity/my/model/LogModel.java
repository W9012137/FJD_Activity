package chencheng.bwie.com.fjd_activity.my.model;

import java.util.HashMap;

import chencheng.bwie.com.fjd_activity.my.bean.LogBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;
import chencheng.bwie.com.fjd_activity.net.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2018/4/10.
 */

public class LogModel implements ILogModel {
    @Override
    public void getLogin(String mobile, String password, final NetListenter<LogBean> netListenter) {
        final HashMap<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("password",password);
        Observable logBean= RetrofitUtils.getServerApi().login("user/login",map);
        logBean.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<LogBean>() {

                    @Override
                    public void accept(LogBean logBean) throws Exception {
                        netListenter.onSccess(logBean);
                    }



    });
    }
}
