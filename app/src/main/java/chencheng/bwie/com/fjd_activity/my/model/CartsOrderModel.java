package chencheng.bwie.com.fjd_activity.my.model;

import chencheng.bwie.com.fjd_activity.my.bean.CatsOrdersBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;
import chencheng.bwie.com.fjd_activity.net.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CartsOrderModel implements ICartsOrderModel {
    @Override
    public void getOrder(String uid, String price, String token, final NetListenter<CatsOrdersBean> onNetListner) {
        Observable observable= RetrofitUtils.getServerApi().confirm(uid,price,token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CatsOrdersBean>() {

                    @Override
                    public void accept(CatsOrdersBean catsOrdersBean) throws Exception {
                        onNetListner.onSccess(catsOrdersBean);
                    }


                });
    }
}
