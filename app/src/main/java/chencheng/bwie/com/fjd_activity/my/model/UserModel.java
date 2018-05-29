package chencheng.bwie.com.fjd_activity.my.model;

import java.util.HashMap;

import chencheng.bwie.com.fjd_activity.my.bean.UserBean;
import chencheng.bwie.com.fjd_activity.net.NetListenter;
import chencheng.bwie.com.fjd_activity.net.RetrofitUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserModel implements IUserModel {
    @Override
    public void GetUser(String uid, final NetListenter<UserBean> netListenter) {
        final HashMap<String , String> map = new HashMap<>();
        map.put("uid",uid);
        Observable observable= RetrofitUtils.getServerApi().user("user/getUserInfo",map);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<UserBean>() {

                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        netListenter.onSccess(userBean);
                    }


                });
    }
}
