package com.iscopy.dailyenglish.databank.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.iscopy.dailyenglish.model.Words;

import java.util.Vector;

public class WordsDao {
    /**
     * 常用单词(增)
     * @param words
     * @param db
     */
    public static synchronized void insertOrderOut(Words words, SQLiteDatabase db){
        //实例化常量值
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", words.getId());
        contentValues.put("word", words.getWord());
        contentValues.put("pronunciation", words.getPronunciation());
        contentValues.put("meaning", words.getMeaning());
        contentValues.put("collection", words.getMeaning());
        db.insert("words", null, contentValues);
    }

    /**
     * 常用单词（删）
     * @param db
     * @return
     */
    public static synchronized int deleteOrderOut(SQLiteDatabase db){
        return db.delete("words", null, null);
    }

    /**
     * 常用单词（改）
     * @param values
     * @param whereClause
     * @param whereArgs
     * @param db
     */
    public static synchronized int updateOrderOut(ContentValues values, String whereClause, String[] whereArgs, SQLiteDatabase db){
        int ret = -1;
        do {
            ret = db.update("words", values, whereClause, whereArgs);
        } while (ret < 0);
        return ret;
        /**
         *     ContentValues values = new ContentValues();
         *     values.put("username", username);
         *     values.put("password", password);
         *     long ret = -1;
         *     do {
         *         ret = db.update("user", values, "userid=?",
         *                 new String[] { userId });
         *     } while (ret < 0);
         */
    }

    /**
     * 常用单词（查）
     * @param db
     * @return
     */
    public static synchronized Vector<Words> queryOrderOut(SQLiteDatabase db,String sql){
        //定义一个 Vector<User>
        Vector<Words> vector = new Vector<Words>();
        Words words = null;

        //获取查询光标（用 query()方法将所有都查出来）
        //Cursor cursor = db.query("words", null, "id limit" + day+","+(day+5), null, null, null, null);
        Cursor cursor = db.rawQuery(sql, null);
        //移动光标到第一个
        if(cursor.moveToFirst()){
            //将数据放入 OrderOut 对象，并放入 vector 集合
            words = new Words();
            words.setId(cursor.getInt(cursor.getColumnIndex("id")));
            words.setWord(cursor.getString(cursor.getColumnIndex("word")));
            words.setPronunciation(cursor.getString(cursor.getColumnIndex("pronunciation")));
            words.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
            words.setCollection(cursor.getInt(cursor.getColumnIndex("collection")));
            vector.add(words);
            //如果有下一个
            while(cursor.moveToNext()){
                words = new Words();
                words.setId(cursor.getInt(cursor.getColumnIndex("id")));
                words.setWord(cursor.getString(cursor.getColumnIndex("word")));
                words.setPronunciation(cursor.getString(cursor.getColumnIndex("pronunciation")));
                words.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
                words.setCollection(cursor.getInt(cursor.getColumnIndex("collection")));
                vector.add(words);
            }
        }
        //关闭光标
        cursor.close();
        //返回 User 对象集合
        return vector;
    }
}
