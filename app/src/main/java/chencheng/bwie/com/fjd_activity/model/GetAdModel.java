package chencheng.bwie.com.fjd_activity.model;

import chencheng.bwie.com.fjd_activity.homepage.bean.GetAdBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;
import chencheng.bwie.com.fjd_activity.net.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GetAdModel implements IGetAdModel {
    @Override
    public void ShowAD(final NetListenter<GetAdBean> netListenter) {
        Observable getAD= RetrofitUtils.getServerApi().getAd();
        getAD.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<GetAdBean>() {

                    @Override
                    public void accept(GetAdBean getAdBean) throws Exception {
                        netListenter.onSccess(getAdBean);
                    }

                });
    }
}
