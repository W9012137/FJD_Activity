package chencheng.bwie.com.fjd_activity.net;

/**
 * Created by dell on 2018/4/10.
 */

public interface NetListenter<T> {
    //成功
    public void onSccess(T t);
    //失败
    public void onFailuer(Exception e);
}
