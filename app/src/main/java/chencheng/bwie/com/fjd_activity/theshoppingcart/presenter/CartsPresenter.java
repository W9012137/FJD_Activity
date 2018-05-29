package chencheng.bwie.com.fjd_activity.theshoppingcart.presenter;

import java.util.ArrayList;
import java.util.List;

import chencheng.bwie.com.fjd_activity.net.NetListenter;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.CartsBean;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.DeleteBean;
import chencheng.bwie.com.fjd_activity.theshoppingcart.model.CartsModel;
import chencheng.bwie.com.fjd_activity.theshoppingcart.model.ICaetsModel;
import chencheng.bwie.com.fjd_activity.theshoppingcart.view.ICartsView;

public class CartsPresenter {
    private ICartsView iCartsView;
    private ICaetsModel iCaetsModel;

    public CartsPresenter(ICartsView iCartsView) {
        this.iCartsView = iCartsView;
       iCaetsModel=new CartsModel();
    }
    public void Pdstry(){
        if (iCartsView!=null){
            iCartsView=null;
        }
    }

    public void Show(final String uid){
        iCaetsModel.GetCarts(uid, new NetListenter<CartsBean>() {
            @Override
            public void onSccess(CartsBean cartsBean) {
                final List<CartsBean.DataBean> data = cartsBean.getData();
                List<List<CartsBean.DataBean.ListBean>> child=new ArrayList<>();
                for (int i=0;i<data.size();i++){
                    final List<CartsBean.DataBean.ListBean> list = data.get(i).getList();
                    child.add(list);
                }
                iCartsView.showList(data,child,uid);
            }

            @Override
            public void onFailuer(Exception e) {

            }
        });

    }
    public void getdelete(String uid, String pid, String token){
        iCaetsModel.getdelete(uid, pid, token, new NetListenter<DeleteBean>() {
            @Override
            public void onSccess(DeleteBean deleteBean) {
                if (iCartsView!=null){
                    iCartsView.showDelete(deleteBean);
                }
            }

            @Override
            public void onFailuer(Exception e) {

            }


        });

    }

}
