package chencheng.bwie.com.fjd_activity.classification.model;

import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.classification.bean.RightBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;
import chencheng.bwie.com.fjd_activity.net.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CatagoryModel implements ICatagoryModel {
    @Override
    public void getCata(final NetListenter<CatagoryBean> netListenter) {
        Observable Catagory= RetrofitUtils.getServerApi().catagory();
        Catagory.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CatagoryBean>() {

                    @Override
                    public void accept(CatagoryBean catagoryBean) throws Exception {
                        netListenter.onSccess(catagoryBean);
                    }


                });
    }

    @Override
    public void getCid(String cid, final NetListenter<RightBean> netListenter) {
        Observable observable=RetrofitUtils.getServerApi().products(cid);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<RightBean>() {

                    @Override
                    public void accept(RightBean rightBean) throws Exception {
                        netListenter.onSccess(rightBean);
                    }

                });
    }
}
