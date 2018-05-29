package chencheng.bwie.com.fjd_activity.my.presenter;

import chencheng.bwie.com.fjd_activity.my.bean.UserBean;
import chencheng.bwie.com.fjd_activity.my.model.IUserModel;
import chencheng.bwie.com.fjd_activity.my.model.UserModel;
import chencheng.bwie.com.fjd_activity.my.view.IUserView;
import chencheng.bwie.com.fjd_activity.net.NetListenter;

public class UserPresenter {
    private IUserView iUserView;
    private IUserModel iUserModel;

    public UserPresenter(IUserView iUserView) {
        this.iUserView = iUserView;
        iUserModel=new UserModel();
    }
    public void Pdstry(){
        if (iUserView!=null){
            iUserView=null;
        }
    }
    public void showu(String uid){
        iUserModel.GetUser(uid, new NetListenter<UserBean>() {
            @Override
            public void onSccess(UserBean userBean) {
                iUserView.ShowUser(userBean);
            }

            @Override
            public void onFailuer(Exception e) {

            }
        });
    }
}
