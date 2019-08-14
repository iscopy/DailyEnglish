package com.iscopy.dailyenglish.databank.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iscopy.dailyenglish.model.SignIn;

import java.util.Vector;

public class SignInDao {
    /**
     * 常用单词(增)
     * @param signIn
     * @param db
     */
    public static synchronized void insertOrderOut(SignIn signIn, SQLiteDatabase db){
        //实例化常量值
        ContentValues contentValues = new ContentValues();
        contentValues.put("year", signIn.getYear());
        contentValues.put("month", signIn.getMonth());
        contentValues.put("day", signIn.getDay());
        db.insert("sign_in", null, contentValues);
    }

    /**
     * 常用单词（删）
     * @param db
     * @return
     */
    public static synchronized int deleteOrderOut(SQLiteDatabase db){
        return db.delete("sign_in", null, null);
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
            ret = db.update("sign_in", values, whereClause, whereArgs);
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
    public static synchronized Vector<SignIn> queryOrderOut(SQLiteDatabase db, String sql){
        //定义一个 Vector<User>
        Vector<SignIn> vector = new Vector<SignIn>();
        SignIn signIn = null;

        //获取查询光标（用 query()方法将所有都查出来）
        //Cursor cursor = db.query("words", null, "id limit" + day+","+(day+5), null, null, null, null);
        Cursor cursor = db.rawQuery(sql, null);
        //移动光标到第一个
        if(cursor.moveToFirst()){
            //将数据放入 OrderOut 对象，并放入 vector 集合
            signIn = new SignIn();
            signIn.setYear(cursor.getInt(cursor.getColumnIndex("year")));
            signIn.setMonth(cursor.getInt(cursor.getColumnIndex("month")));
            signIn.setDay(cursor.getInt(cursor.getColumnIndex("day")));
            vector.add(signIn);
            //如果有下一个
            while(cursor.moveToNext()){
                signIn = new SignIn();
                signIn.setYear(cursor.getInt(cursor.getColumnIndex("year")));
                signIn.setMonth(cursor.getInt(cursor.getColumnIndex("month")));
                signIn.setDay(cursor.getInt(cursor.getColumnIndex("day")));
                vector.add(signIn);
            }
        }
        //关闭光标
        cursor.close();
        //返回 User 对象集合
        return vector;
    }
}
