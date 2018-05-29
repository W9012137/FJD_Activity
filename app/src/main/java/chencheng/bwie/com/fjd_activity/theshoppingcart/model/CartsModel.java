package chencheng.bwie.com.fjd_activity.theshoppingcart.model;

import java.util.HashMap;

import chencheng.bwie.com.fjd_activity.net.NetListenter;
import chencheng.bwie.com.fjd_activity.net.RetrofitUtils;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.CartsBean;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.DeleteBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CartsModel implements ICaetsModel {
    @Override
    public void GetCarts(String uid, final NetListenter<CartsBean> netListenter) {
        final HashMap<String, String> map = new HashMap<>();
        map.put("uid",uid);
        Observable flowable= RetrofitUtils.getServerApi().getCart("product/getCarts",map,"android" );
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CartsBean>() {

                    @Override
                    public void accept(CartsBean getCartBean) throws Exception {
                        netListenter.onSccess(getCartBean);
                    }

                });
    }

    @Override
    public void getdelete(String uid, String pid, String token, final NetListenter<DeleteBean> onNetListner) {
        Observable<DeleteBean> delete = RetrofitUtils.getServerApi().delete(uid, pid, token);
        delete.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DeleteBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeleteBean bean) {
                        onNetListner.onSccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListner.onFailuer((Exception) e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
