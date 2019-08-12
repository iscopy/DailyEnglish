package com.iscopy.dailyenglish.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iscopy.dailyenglish.R;
import com.youth.banner.loader.ImageLoader;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @Author: wangyb on 2016/8/27 14:38.
 * @描述 : Glide加载图片的封装
 */
public class GlideUtil extends ImageLoader {

    public GlideUtil() {
    }

    /**
     * 常规加载
     *
     * @param context
     * @param path
     * @param imageView
     */
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path.toString())
                .error(R.mipmap.fail_image)//图片加载失败后，显示的图片
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }


    //高斯模糊
    public void vagueImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path.toString())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // 设置高斯模糊  14":模糊度；"3":图片缩放3倍后再进行模糊，缩放3-5倍个人感觉比较好。
                .bitmapTransform(new BlurTransformation(context, 14, 3))
                .into(imageView);

    }

    /**
     * 圆角图片
     *
     * @param context
     * @param path
     * @param imageView
     * @param angle
     */
    public void filletImage(Context context, Object path, ImageView imageView, int angle) {
        Glide.with(context)
                .load(path.toString())
                //图片加载失败后，显示的图片
                .error(R.mipmap.fail_image)
                .bitmapTransform(new RoundedCornersTransformation(context, angle, 0, RoundedCornersTransformation.CornerType.ALL))
                .crossFade(1000)
                .into(imageView);
    }
    // Glide.with(this).load(url).bitmapTransform(new RoundedCornersTransformation(this, 30, 0, RoundedCornersTransformation.CornerType.BOTTOM)).crossFade(1000).into(image5);

    /**
     * 加载GIF图
     *
     * @param context
     * @param path
     * @param imageView
     */
    public void gifImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * @param context
     * @param path
     * @param imageView
     */
    public void circleImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path.toString())
                .error(R.mipmap.fail_image)//图片加载失败后，显示的图片
                .bitmapTransform(new CropCircleTransformation(context))
                .crossFade(1000)
                .into(imageView);
    }

    //常用的属性
    public void displayImageExperiment(Context context, Object path, ImageView imageView) {
        //with 可用于启动加载的顶级应用程序的RequestManager
        Glide.with(context)

                //文件路径(加载图片的网络位置，也可以是GIF，和本地视频-网络视频不行)
                //.load( Uri.fromFile( new File( filePath ) ) )   本地视频
                .load(path.toString())

                //占位图设置（加载错误或者加载失败时显示的图片）图片自己设置
                //placeholder() 和 error() 的参数都是只支持 int 和 Drawable 类型的参数，这种设计应该是考虑到使用本地图片
                .placeholder(R.drawable.ic_launcher_background)//图片加载出来前，显示的图片
                .error(R.drawable.ic_launcher_background)//图片加载失败后，显示的图片

                //缩略图，参数是flost类型，作为其倍数大小
                .thumbnail(0.2f)

                //动画开关 - crossFade() 方法强制开启 Glide 默认的图片淡出淡入动画(也可以自定义动画效果哦)
                .crossFade()//或者使用 dontAnimate() 关闭动画

                //图片大小与裁剪
                .override(100, 70)//这里的单位是px

                //图片的缓存处理  (内存缓存、磁盘缓存)
                /*
                 * DiskCacheStrategy.NONE 什么都不缓存
                 * DiskCacheStrategy.SOURCE 只缓存全尺寸图
                 * DiskCacheStrategy.RESULT 只缓存最终的加载图
                 * DiskCacheStrategy.ALL 缓存所有版本图（默认行为）
                 * */
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)

                //图片请求的优先级,如果同时加载很多图片，就可以设置优先级(分为：立即、高、正常、低、优先)
                /*
                 * IMMEDIATE,
                 * HIGH,
                 * NORMAL,
                 * LOW,
                 * priority,
                 * */
                .priority(Priority.priority)

                //设置布局，加载的图片显示布局
                .into(imageView);
    }

    public static void loaderImage(Context context, File file, ImageView iv, int placeholder, int error) {
        Glide.with(context)
                .load(file)
                .placeholder(R.mipmap.loading_image)//加载中显示的图片
                .error(R.mipmap.fail_image)//加载失败时显示的图片
                .into(iv);//URL加载方式
    }

    public static void loaderImage(Context context, String url, ImageView iv, int placeholder, int error) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.loading_image)//加载中显示的图片
                .error(R.mipmap.fail_image)//加载失败时显示的图片
                .into(iv);//URL加载方式
    }
}
