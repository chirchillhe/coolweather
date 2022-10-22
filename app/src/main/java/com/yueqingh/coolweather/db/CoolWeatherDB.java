package com.yueqingh.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yueqingh.coolweather.model.City;
import com.yueqingh.coolweather.model.Country;
import com.yueqingh.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yueqi
 * @date: 2022/10/22
 */
public class CoolWeatherDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "cool_weather";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    public static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;
    private Cursor cursorProvince;
    private Cursor cursorCity;
    private List<City> cityList;
    private List<Province> provinceList;
    private List<Country> countryList;
    private Cursor cursorCountry;

    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取CoolWeatherDB的实例
     * @param context
     * @return
     */
    public static CoolWeatherDB getInstance(Context context){
        if (coolWeatherDB == null){
            synchronized (CoolWeatherDB.class){
                if (coolWeatherDB == null){
                    coolWeatherDB = new CoolWeatherDB(context);
                }
            }
        }
        return coolWeatherDB;
    }

    /**
     * 将Province实例存储到数据库
     * @param province
     */
    public void saveProvince(Province province){
        if (province != null){
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    /**
     * 从数据库读取全国所有的省份信息
     * @return
     */
    public List<Province> loadProvinces(){
        provinceList = new ArrayList<>();
        cursorProvince = db.query("Province", null, null, null, null, null, null);
        if (cursorProvince.moveToFirst()){
            do{
                Province province = new Province();
                province.setId(cursorProvince.getInt(cursorProvince.getColumnIndexOrThrow("id")));
                province.setProvinceName(cursorProvince.getString(cursorProvince.getColumnIndexOrThrow("province_name")));
                province.setProvinceCode(cursorProvince.getString(cursorProvince.getColumnIndexOrThrow("province_code")));
                provinceList.add(province);
            }while (cursorProvince.moveToNext());
        }
        return provinceList;
    }

    /**
     * 将City实例存储到数据库
     * @param city
     */
    public void saveCity(City city){
        if (city != null){
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityName());
            values.put("city_code", city.getCityCode());
            db.insert("City",null, values);
        }
    }

    /**
     * 从数据库读取某省下的所有的城市信息
     * @param provinceId
     * @return
     */
    public List<City> loadCitied(int provinceId){
        cityList = new ArrayList<>();
        cursorCity = db.query("City", null, "province_id = ?", new String[]{String.valueOf(provinceId)}, null, null, null);
        if (cursorCity.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursorCity.getInt(cursorCity.getColumnIndexOrThrow("id")));
                city.setCityName(cursorCity.getString(cursorCity.getColumnIndexOrThrow("city_name")));
                city.setCityCode(cursorCity.getString(cursorCity.getColumnIndexOrThrow("city_code")));
                city.setProvinceId(provinceId);
                cityList.add(city);
            }while (cursorCity.moveToNext());
        }
        return cityList;
    }

    /**
     * 将Cuntry实例存储到数据库
     * @param country
     */
    public void saveCountry(Country country){
        if (country != null){
            ContentValues values = new ContentValues();
            values.put("country_name", country.getCountryName());
            values.put("country_code", country.getCountryCode());
            values.put("city_id", country.getCityId());
            db.insert("Country", null, values);
        }
    }

    /**
     * 从数据库获取某城市下所有县的信息
     * @param cityId
     * @return
     */
    public List<Country> loadCountries(int cityId){
        countryList = new ArrayList<>();
        cursorCountry = db.query("Country", null, "city_id", new String[]{String.valueOf(cityId)}, null, null, null);
        if (cursorCountry.moveToFirst()){
            do {
                Country country = new Country();
                country.setId(cursorCountry.getInt(cursorCountry.getColumnIndexOrThrow("id")));
                country.setCountryName(cursorCountry.getString(cursorCountry.getColumnIndexOrThrow("country_name")));
                country.setCountryCode(cursorCountry.getString(cursorCountry.getColumnIndexOrThrow("cuntry_code")));
                country.setCityId(cityId);
                countryList.add(country);
            }while (cursorCountry.moveToNext());
        }
        return countryList;
    }

}
