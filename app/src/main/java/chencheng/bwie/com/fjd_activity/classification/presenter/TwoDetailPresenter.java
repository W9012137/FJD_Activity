package chencheng.bwie.com.fjd_activity.classification.presenter;

import chencheng.bwie.com.fjd_activity.classification.bean.AddCartBean;
import chencheng.bwie.com.fjd_activity.classification.bean.DetailBean;
import chencheng.bwie.com.fjd_activity.classification.model.IDtetailModel;
import chencheng.bwie.com.fjd_activity.classification.model.TwoDetailModel;
import chencheng.bwie.com.fjd_activity.classification.view.ITwoDetailView;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public class TwoDetailPresenter {
    private ITwoDetailView activity;
    private final IDtetailModel model;

    public TwoDetailPresenter(ITwoDetailView activity) {
        this.activity = activity;
        model = new TwoDetailModel();
    }

    public void onDestroys() {
        if (activity != null) {
            activity = null;
        }
    }

    public void getDetai(String pid) {
        model.getDetail(pid, "android", new NetListenter<DetailBean>() {
            @Override
            public void onSccess(DetailBean detailBean) {
                if (activity != null) {

                    activity.detailshow(detailBean);
                }
            }

            @Override
            public void onFailuer(Exception e) {

            }


        });
    }

    public void getadds(String uid, String pid, String token) {
        model.getAdd(uid, pid, "android", token, new NetListenter<AddCartBean>() {
            @Override
            public void onSccess(AddCartBean addCartBean) {
                if (activity != null) {
                    activity.addshow(addCartBean);
                }
            }

            @Override
            public void onFailuer(Exception e) {

            }


        });

    }

}
