package chencheng.bwie.com.fjd_activity.classification.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import chencheng.bwie.com.fjd_activity.R;

/**
 * Created by
 */

public class MyGlide extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
