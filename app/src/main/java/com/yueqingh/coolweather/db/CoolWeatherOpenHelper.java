package com.yueqingh.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author: yueqi
 * @date: 2022/10/22
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
    //第一步。创建sql建表语句
    /**
     * Province表建表语句
     */
    public static final String CREATE_PROVINCE = "create table Province (" +
            "id integer primary key autoincrement," +
            "province_name text," +
            "province_code text)";

    /**
     * City表建表语句
     */
    public static final String CREATE_CITY = "create table City (" +
            "id integer primary key autoincrement," +
            "province_name text," +
            "province_code text)";

    /**
     * Country表建表语句
     */
    public static final String CREATE_COUNTRY = "create table Country (" +
            "id integer primary key autoincrement," +
            "country_name text," +
            "country_code text," +
            "city_id integer)";

    /**
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public CoolWeatherOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);//创建Province表
        db.execSQL(CREATE_CITY);//创建City类
        db.execSQL(CREATE_COUNTRY);//创建Country类
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
