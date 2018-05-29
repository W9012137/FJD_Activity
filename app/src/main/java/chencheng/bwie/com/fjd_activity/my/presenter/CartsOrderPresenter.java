package chencheng.bwie.com.fjd_activity.my.presenter;

import chencheng.bwie.com.fjd_activity.my.bean.CatsOrdersBean;
import chencheng.bwie.com.fjd_activity.my.model.CartsOrderModel;
import chencheng.bwie.com.fjd_activity.my.model.ICartsOrderModel;
import chencheng.bwie.com.fjd_activity.my.view.ICartsOrderView;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public class CartsOrderPresenter {
    private ICartsOrderView iCartsOrderView;
    private ICartsOrderModel iCartsOrderModel;

    public CartsOrderPresenter(ICartsOrderView iCartsOrderView) {
        this.iCartsOrderView = iCartsOrderView;
        iCartsOrderModel=new CartsOrderModel();
    }
    public void ondestrys() {
        if (iCartsOrderView != null) {
            iCartsOrderView = null;
        }
    }
    public void getOrder(String uid, String price, String token){
        iCartsOrderModel.getOrder(uid, price, token, new NetListenter<CatsOrdersBean>() {
            @Override
            public void onSccess(CatsOrdersBean catsOrdersBean) {
                iCartsOrderView.getShow(catsOrdersBean);
            }

            @Override
            public void onFailuer(Exception e) {

            }
        });
    }
}
