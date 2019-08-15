package com.iscopy.dailyenglish.databank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DESQLite extends SQLiteOpenHelper {
    //数据库名称(百倍佳)
    private static final String DB_NAME = "HappyG.db";
    //版本
    private static final int DB_VERSION = 1;

    public DESQLite(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 保存常用单词
         * id     用户id
         * 单词   word
         * 发音   pronunciation
         * 含义  meaning
         * 是否收藏 collection 0不收藏 1收藏
         */
        String words = "create table words(" +
                "id integer primary key," +
                "word varchar2 not null," +
                "pronunciation varchar2 not null," +
                "meaning varchar2  not null," +
                "collection integer not null)";
        db.execSQL(words);

        /**
         * 保存签到日志
         * year 年
         * month 月
         * day 日
         */
        String signin = "create table signin(" +
                "year integer not null," +
                "month integer  not null," +
                "day integer not null)";
        db.execSQL(signin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
