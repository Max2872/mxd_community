package com.project.mxd.mxd_community;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maohs on 2018/3/20.
 */

public class CommunityOpenHelper extends SQLiteOpenHelper {
    public CommunityOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context,name,factory,version);
    }
    //数据库第一次创建时被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE userInfo(userId INTEGER PRIMARY KEY AUTOINCREMENT,phoneNum text,passward VARCHAR(20),wallet VARCHAR(20))");

        db.execSQL("CREATE TABLE orderInfo(userId INTEGER PRIMARY KEY AUTOINCREMENT,phoneNum text,goodsImageId text,goodsName text" +
                ",goodsPrice VARCHAR(20)," + "recieverName text,recieverPhone VARCHAR(20),recieverAddress text)");
        db.execSQL("CREATE TABLE goodsInfo(userId INTEGER PRIMARY KEY AUTOINCREMENT,goodsName text,goodsPrice VARCHAR(20)" +
                ",goodNum VARCHAR(20))");
        db.execSQL("CREATE TABLE cartGoodsInfo(userId INTEGER PRIMARY KEY AUTOINCREMENT,isSelected VARCHAR(10),phoneNum text,goodsImageId text,goodsName text" +
                ",goodsPrice VARCHAR(20)," + "goodNum VARCHAR(20))");
        db.execSQL("CREATE TABLE addressInfo(userId INTEGER PRIMARY KEY AUTOINCREMENT,phoneNum text,recieverName text,recieverPhone VARCHAR(20)" +
                ",recieverAddress text)");
        db.execSQL("CREATE TABLE remindInfo(userId INTEGER PRIMARY KEY AUTOINCREMENT,phoneNum text,remindTime text,remindAdranceDay VARCHAR(10)" +
                ",remark text)");
    }

    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
