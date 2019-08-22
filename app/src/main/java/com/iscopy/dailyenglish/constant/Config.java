package com.iscopy.dailyenglish.constant;

/**
 * 配置 （）
 */
public class Config {

    /**
     * 是否是第一次登陆
     */
    public final static String SPLASH_ONE = "splash_one";

    /**
     * 签到
     */
    public final static String SIGN_IN = "Sign_in";

    /**
     * 我的金句名言
     */
    public final static String MY_QUOTES = "my_quotes";
    public final static int MY_QUOTES_INT = 1001;

    /**
     * 相机拍照
     */
    public static final int CAMERA_REQUEST_CODE = 1002;
    /**
     * 相册选择
     */
    public static final int PHOTOA_REQUEST_CODE = 1003;

    /**
     * 将Bitmap转换成Base64保存到SharedPreferences (头像)
     */
    public final static String HEAD = "head";

    /**
     * 首页-收藏
     */
    public static final int WORD_COLLECTION = 1004;
    /**
     * 我的-收藏
     */
    public static final int MY_COLLECTION = 1005;
    /**
     * 首页-加载数据
     */
    public static final int WORD_LOADING = 1006;
    /**
     * 首页-打卡
     */
    public static final int CLOCK_IN = 1007;
}
