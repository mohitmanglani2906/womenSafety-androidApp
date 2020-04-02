package com.example.mohit2906.womensafety;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.annotation.Target;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME ="UserContacts.db";
    public static final String TABLE_NAME = "usercintacts_table";
    public static final String COL1 = "PHONE_NO";
    public static final String COL2 = "NAME";

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (PHONE_NO TEXT PRIMARY KEY,NAME TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public boolean insertData(String mobileno,String name)
    {

        try
        {
            SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
            ContentValues contentValues =new ContentValues();
            contentValues.put(COL1,mobileno);
            contentValues.put(COL2,name);
            long result =sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

            if(result == -1) return false;
            else return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;

    }


    public boolean updateData(String old_phone,String new_phone,String name)
    {
        try
        {
            SQLiteDatabase db =  this.getWritableDatabase();
            ContentValues contentValues =new ContentValues();
            contentValues.put(COL1,new_phone);
            contentValues.put(COL2,name);
            db.update(TABLE_NAME,contentValues,"PHONE_NO = ?",new String[]{old_phone});

            return true;
        }

        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;

    }


    public boolean deleteData(String phone_no_to_be_deleted)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME,"PHONE_NO = ?",new String[]{phone_no_to_be_deleted});
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return  false;
    }

    public boolean deleteAll()
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("delete from " + TABLE_NAME);
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Cursor checkPhone()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("select PHONE_NO " + "from " + TABLE_NAME,null);
        return cursor;
    }

    public ArrayList<UserContactsAdded> getAllData()
    {
        ArrayList<UserContactsAdded> userList = null;
        try
        {
            userList = new ArrayList<>();
            SQLiteDatabase db  = this.getWritableDatabase();
            Cursor cs = db.rawQuery("select * from " + TABLE_NAME,null);

            while (cs.moveToNext())
            {
                String mobilenno = cs.getString(0);
                String name = cs.getString(1);

                UserContactsAdded uca =  new UserContactsAdded(mobilenno,name);
                userList.add(uca);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }


        return  userList;
    }


}
