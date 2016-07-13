package com.example.vijay.finalproject;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Laxman Venkat Kumar on 2/19/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class PhoneDatabase {
    public final static String NOTICE_TITLE = "notice_title";
    public final static String NOTICE_DATE = "notice_date";
    public final static String NOTICE_DISCRIPTION="notice_discription";
    public final static String NOTICE_TAG="notice_tag";
    public final static String COMPANY="company_name";
    public final static String STARTDAY="startday";
    public final static String STARTMONTH="startmonth";
    public final static String STARTYEAR="startyear";
    public final static String ENDDAY="endday";
    public final static String ENDMONTH="endmonth";
    public final static String ENDYEAR="endyear";
    public final static String DATABASE_NAME="TrailApp";
    public final static int DATABASE_VERSION=1;
    public final static String[] TABLE_NAMES= {"notices","updates","jnfs","up_comming_drives","company_reg","results"};
    public static Context con;
    public static SQLiteDatabase db;

    public static final String TABLE_CREATE_NOTICE= "create table notices (notice_title varchar(100),notice_date varchar(30)primary key,notice_discription varchar(200),notice_tag varchar(50));";
    public static final String TABLE_CREATE_UPDATE= "create table updates (notice_title varchar(100),notice_date varchar(30) primary key,notice_discription varchar(200));";
    public static final String TABLE_CREATE_UPCOMMING="create table upcomming (notice_title varchar(100),notice_date varchar(30) primary key,notice_discription varchar(200));";
    public static final String TABLE_CREATE_REGISTRATION="create table companies (company_name varchar(100),startday varchar(5),startmonth varchar(5),startyear varchar(5),endday varchar(5),endmonth varchar(5),endyear varchar(5));";
    public PhoneDatabase() {

    }




    // public static final String TABLE_DROP = "DROP TABLE IF EXITS "+TABLE_NAME;

    private static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE_NOTICE);
            db.execSQL(TABLE_CREATE_UPDATE);
            db.execSQL(TABLE_CREATE_UPCOMMING);
            db.execSQL(TABLE_CREATE_REGISTRATION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
    public PhoneDatabase(Context cont){
        con=cont;
    }
    public PhoneDatabase Open(){
        db= new DBHelper(con).getWritableDatabase();
        return this;
    }
    public void Close(){
        db.close();
    }
    public int tableEntriesCount(String var)
    {
        String FetchData = "SELECT COUNT(*) FROM "+var+"";
        Cursor cursor= db. rawQuery(FetchData, null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        return Integer.parseInt(cursor.getString(0));
    }
    public void InsertRegDeatils(String Company,String startday,String startmonth,String startyear, String endday, String endmonth,String endyear){
        ContentValues contentValues= new ContentValues();
        contentValues.put(COMPANY,Company);
        contentValues.put(STARTDAY,startday);
        contentValues.put(STARTMONTH,startmonth);
        contentValues.put(STARTYEAR,startyear);
        contentValues.put(ENDDAY,endday);
        contentValues.put(ENDMONTH,endmonth);
        contentValues.put(ENDYEAR,endyear);
        db.insert("companies", null, contentValues);

    }
    public void InsertDataUpdates(String notice_title,String notice_date, String notice_discription,String tag){
        ContentValues context= new ContentValues();
        String tags=tag.toLowerCase();
        context.put(NOTICE_TITLE,notice_title);
        context.put(NOTICE_DATE,notice_date);
        context.put(NOTICE_DISCRIPTION, notice_discription);
        if(tags.equals("updates")) {
            db.insert("updates", null, context);
        }
        else{
            db.insert("upcomming", null, context);
        }
        context.put(NOTICE_TAG, tag);
        db.insert("notices", null, context);
       // Toast.makeText(con,"Successfully Updated",Toast.LENGTH_LONG).show();
    }
    public Cursor getRegDetails(){
        String FetchData= "SELECT * FROM companies";
        Cursor cursor = db.rawQuery(FetchData,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor getDataNotice(){
        String FetchData = "SELECT * FROM notices ORDER BY notice_date ";

        Cursor cursor= db. rawQuery(FetchData, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public String getLatestDate(String table){
        String FetchData="SELECT notice_date FROM "+table+" LIMIT 1";
        Cursor cursor=db.rawQuery(FetchData, null);
        if(cursor!=null){
            cursor.moveToFirst();
            String date=cursor.getString(0);
            return date;
        }

        return null;
    }
    public Cursor getDataUpdates() {
        String FetchData="SELECT * FROM updates ORDER BY notice_date";
        Cursor cursor=db.rawQuery(FetchData, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor getDataUpComming() {
        String FetchData="SELECT * FROM upcomming ORDER BY notice_date ";
        Cursor cursor=db.rawQuery(FetchData,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }

}
