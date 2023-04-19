package com.cookandroid.home;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.PrimitiveIterator;
import java.util.concurrent.locks.Condition;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;

    private Context context;

    private static final String TABLE_CONDITION = "ConditionTable";
    private static final String MONTH = "month";
    private static final String DAY = "day";
    private static final String CONDITION = "condition";
    private static final String MEDICINE = "medicine";
    private static final String MONTHDAY = "monthday";

    public MyDBHelper(Context context) {
        super(context, "HOME.db", null, DATABASE_VERSION);

        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE medi (mediId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, mediName TEXT NOT NULL, " +
                "startDate TEXT NOT NULL,  endDate TEXT NOT NULL,  timesPerDay INTEGER NOT NULL," +
                "mon INTEGER, tue INTEGER, wed INTEGER, thu INTEGER," +
                "fri INTEGER, sat INTEGER, sun INTEGER);");

        db.execSQL("CREATE TABLE time (timeId INTEGER PRIMARY KEY AUTOINCREMENT, oneTime TEXT NOT NULL, twoTime TEXT, threeTime TEXT, " +
                "fourTime TEXT, fiveTime TEXT, " +
                "FOREIGN KEY(timeId) REFERENCES medi(mediId));");

        // 컨디션과 약 DB
        db.execSQL("CREATE TABLE IF NOT EXISTS ConditionTable (id INTEGER PRIMARY KEY AUTOINCREMENT, month TEXT NOT NULL, day TEXT NOT NULL, condition TEXT NOT NULL, medicine TEXT NOT NULL, monthday TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public ArrayList<ListItem> allListItems() {
        ArrayList<ListItem> listItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor, cursor2;
        cursor = db.rawQuery("SELECT * FROM medi WHERE endDate >= date('now');", null);
        cursor2 = db.rawQuery("SELECT * FROM medi JOIN time ON medi.mediId = time.timeId WHERE endDate >= date('now');", null);

        while (cursor.moveToNext() && cursor2.moveToNext()) {
            ListItem listItem = new ListItem();
            listItem.setMediId(cursor.getInt(0));
            listItem.setMediName(cursor.getString(1));
            listItem.setStartDate(cursor.getString(2));
            listItem.setEndDate(cursor.getString(3));
            listItem.setTimesPerDay(cursor.getInt(4));
            listItem.setMon(cursor.getInt(5));
            listItem.setTue(cursor.getInt(6));
            listItem.setWed(cursor.getInt(7));
            listItem.setThu(cursor.getInt(8));
            listItem.setFri(cursor.getInt(9));
            listItem.setSat(cursor.getInt(10));
            listItem.setSun(cursor.getInt(11));
            listItem.setOneTime(cursor2.getString(13));
            listItem.setTwoTime(cursor2.getString(14));
            listItem.setThreeTime(cursor2.getString(15));
            listItem.setFourTime(cursor2.getString(16));
            listItem.setFiveTime(cursor2.getString(17));
            listItems.add(listItem);
        }

        cursor.close();
        cursor2.close();
        db.close();

        return listItems;
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM medi WHERE mediId = '" + id + "';");
        db.execSQL("DELETE FROM time WHERE timeId = '" + id + "';");
        db.close();
    }


    public void setCondition(ConditionInfo condition)
    {
        SQLiteDatabase sql = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(MONTH, condition.getMonth());
        content.put(DAY, condition.getDay());
        content.put(CONDITION, condition.getCondition());
        content.put(MEDICINE, condition.getMedicine());
        content.put(MONTHDAY, condition.getMonthDay());

        sql.insert(TABLE_CONDITION, null, content);
        sql.close();
    }


    public  ArrayList<ConditionInfo> getCondition()
    {
        SQLiteDatabase sql = this.getWritableDatabase();
        String SELECT_ALL = "SELECT * FROM " + TABLE_CONDITION;
        Cursor cursor = sql.rawQuery(SELECT_ALL,null);
        ArrayList<ConditionInfo> conditionList = new ArrayList<>();

        while (cursor.moveToNext())
        {
            ConditionInfo conditionInfo = new ConditionInfo();
            conditionInfo.setId(Integer.parseInt(cursor.getString(0)));
            conditionInfo.setMonth(cursor.getString(1));
            conditionInfo.setDay(cursor.getString(2));
            conditionInfo.setCondition(cursor.getString(3));
            conditionInfo.setMedicine(cursor.getString(4));
            conditionInfo.setMonthDay(cursor.getString(5));

            conditionList.add(conditionInfo);
        }

        return  conditionList;
    }

    public void removeCondition(ConditionInfo condition)
    {
        SQLiteDatabase sql = this.getWritableDatabase();
        String SELECT_ALL = "SELECT * FROM " + TABLE_CONDITION;
        Cursor cursor = sql.rawQuery(SELECT_ALL,null);
        ArrayList<ConditionInfo> conditionList = new ArrayList<>();

        while (cursor.moveToNext())
        {
            ConditionInfo conditionInfo = new ConditionInfo();
            conditionInfo.setMonth(cursor.getString(1));
            conditionInfo.setDay(cursor.getString(2));
            conditionInfo.setCondition(cursor.getString(3));
            conditionInfo.setMedicine(cursor.getString(4));
            conditionInfo.setMonthDay(cursor.getString(5));

            conditionList.add(conditionInfo);
        }

        ConditionInfo con = null;
        for(ConditionInfo conditionInfo : conditionList)
        {
            if(condition.getMonthDay().equals(conditionInfo.getMonthDay()))
            {
                con = conditionInfo;
            }
        }

        if(con != null) {

            sql.delete(TABLE_CONDITION, "monthday = ?", new String[]{con.getMonthDay()});
        }
    }


    public void deleteConditionTable()
    {
        SQLiteDatabase sql = this.getWritableDatabase();
        sql.execSQL("delete from " + TABLE_CONDITION);
    }


}
