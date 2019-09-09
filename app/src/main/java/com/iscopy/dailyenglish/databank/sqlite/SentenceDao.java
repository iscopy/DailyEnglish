package com.iscopy.dailyenglish.databank.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.iscopy.dailyenglish.model.Sentence;

import java.util.Vector;

public class SentenceDao {

    /**
     * 单词造句(增)
     * @param sentence
     * @param db
     */
    public static synchronized void insertOrderOut(Sentence sentence, SQLiteDatabase db){
        //实例化常量值
        ContentValues contentValues = new ContentValues();
        contentValues.put("word", sentence.getWord());
        contentValues.put("sentence", sentence.getSentence());
        contentValues.put("sentence2", sentence.getSentence2());
        db.insert("sentence", null, contentValues);
    }

    /**
     * 单词造句（删）
     * @param db
     * @return
     */
    public static synchronized int deleteOrderOut(SQLiteDatabase db){
        return db.delete("sentence", null, null);
    }

    /**
     * 单词造句（改）
     * @param values
     * @param whereClause
     * @param whereArgs
     * @param db
     */
    public static synchronized int updateOrderOut(ContentValues values, String whereClause, String[] whereArgs, SQLiteDatabase db){
        int ret = -1;
        do {
            ret = db.update("sentence", values, whereClause, whereArgs);
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
     * 单词造句（查）
     * @param db
     * @return
     */
    public static synchronized Vector<Sentence> queryOrderOut(SQLiteDatabase db, String sql) {
        //定义一个 Vector<User>
        Vector<Sentence> vector = new Vector<Sentence>();
        Sentence sentence = null;

        //获取查询光标（用 query()方法将所有都查出来）
        //Cursor cursor = db.query("sentence", null, "id limit" + day+","+(day+5), null, null, null, null);
        Cursor cursor = db.rawQuery(sql, null);
        //移动光标到第一个
        if(cursor.moveToFirst()){
            //将数据放入 OrderOut 对象，并放入 vector 集合
            sentence = new Sentence();
            sentence.setWord(cursor.getString(cursor.getColumnIndex("word")));
            sentence.setSentence(cursor.getString(cursor.getColumnIndex("sentence")));
            sentence.setSentence2(cursor.getString(cursor.getColumnIndex("sentence2")));
            vector.add(sentence);
            //如果有下一个
            while(cursor.moveToNext()){
                sentence = new Sentence();
                sentence.setWord(cursor.getString(cursor.getColumnIndex("word")));
                sentence.setSentence(cursor.getString(cursor.getColumnIndex("sentence")));
                sentence.setSentence2(cursor.getString(cursor.getColumnIndex("sentence2")));
                vector.add(sentence);
            }
        }
        //关闭光标
        cursor.close();
        //返回 User 对象集合
        return vector;
    }

}
