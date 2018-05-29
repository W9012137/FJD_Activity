package chencheng.bwie.com.fjd_activity.classification.model;

import java.util.HashMap;

import chencheng.bwie.com.fjd_activity.classification.bean.LiatBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;
import chencheng.bwie.com.fjd_activity.net.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListModel implements IListModel {
    @Override
    public void GetList(String pscid, String page, String sort, final NetListenter<LiatBean> netListenter) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("pscid",pscid);
        map.put("page",page);
        map.put("sort",sort);
        Observable flowable= RetrofitUtils.getServerApi().listrv("product/getProducts",map);
        flowable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<LiatBean>() {

                    @Override
                    public void accept(LiatBean liatBean) throws Exception {
                        netListenter.onSccess(liatBean);
                    }


                });
    }
}
