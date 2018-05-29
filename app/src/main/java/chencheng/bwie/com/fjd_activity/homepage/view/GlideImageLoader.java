package chencheng.bwie.com.fjd_activity.homepage.view;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by 杨杰 on 2017/12/13.
 */

 public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);
    }
}