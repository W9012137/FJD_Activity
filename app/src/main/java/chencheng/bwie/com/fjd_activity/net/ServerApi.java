package chencheng.bwie.com.fjd_activity.net;

import java.util.Map;

import chencheng.bwie.com.fjd_activity.classification.bean.AddCartBean;
import chencheng.bwie.com.fjd_activity.classification.bean.CatagoryBean;
import chencheng.bwie.com.fjd_activity.classification.bean.DetailBean;
import chencheng.bwie.com.fjd_activity.classification.bean.LiatBean;
import chencheng.bwie.com.fjd_activity.classification.bean.RightBean;
import chencheng.bwie.com.fjd_activity.homepage.bean.GetAdBean;
import chencheng.bwie.com.fjd_activity.my.bean.CatsOrdersBean;
import chencheng.bwie.com.fjd_activity.my.bean.LogBean;
import chencheng.bwie.com.fjd_activity.my.bean.OrdersBean;
import chencheng.bwie.com.fjd_activity.my.bean.RegisterBean;
import chencheng.bwie.com.fjd_activity.my.bean.ResponseBody;
import chencheng.bwie.com.fjd_activity.my.bean.UpOrdersBean;
import chencheng.bwie.com.fjd_activity.my.bean.UserBean;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.CartsBean;
import chencheng.bwie.com.fjd_activity.theshoppingcart.bean.DeleteBean;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ServerApi {
    //注册
    @POST
    Observable<RegisterBean> register(@Url String url, @QueryMap Map<String, String> map);
    @POST
    //登录
    Observable<LogBean> login(@Url String url, @QueryMap Map<String, String> map);
    @GET("ad/getAd")
    Observable<GetAdBean> getAd();
    @GET("product/getCatagory")
    Observable<CatagoryBean> catagory();
    @GET("product/getProductCatagory")
    Observable<RightBean> products(@Query("cid") String cid);
    @POST
    Observable<LiatBean> listrv(@Url String url, @QueryMap Map<String ,String> map);
    //详情
    @GET("product/getProductDetail")
    Observable<DetailBean> detail (@Query("pid") String pid, @Query("source") String source);

    //查询购物车
    @POST
    Observable<CartsBean> getCart(@Url String url, @QueryMap Map<String,String> map, @Query("source") String source);
    //加入购物车
    @POST("product/addCart")

    Observable<AddCartBean> addcata(@Query("uid") String uid, @Query("pid") String pid, @Query("source") String source, @Query("token") String token);

    //加入购物车
    @POST("product/getCarts")

    Observable<CartsBean> querys(@Query("uid") String uid, @Field("source") String source, @Query("token") String token);
    //用户信息
    @POST
    Observable<UserBean> user(@Url String url, @QueryMap Map<String,String> map);
    //删除购物车
    @POST("product/deleteCart")
    Observable<DeleteBean> delete(@Query("uid") String uid, @Query("pid") String pid, @Query("token") String token);
    //创建订单

    @POST("product/createOrder")
    Observable<CatsOrdersBean> confirm(@Query("uid") String uid, @Query("price") String price, @Query("token") String token);
    //订单列表
    @POST("product/getOrders")
    Observable<OrdersBean> all(@Query("uid") String uid);

    //修改订单
    @POST("product/updateOrder")
    Observable<UpOrdersBean> modify(@Query("uid") String uid, @Query("status") String status, @Query("orderId") String orderId);
    //头像上传
    @Multipart
    @POST("file/upload")
    Call<ResponseBody> getHead(@Query("uid") String uid, @Part MultipartBody.Part body);
}