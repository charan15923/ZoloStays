package com.example.saicharan.zolo;

/**
 * Created by NaNi on 04/08/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ZoloUserManager.db";

    // User table name
    private static final String TABLE_USER = "zolouser";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_PHONENUMBER="user_phonenumber";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_PHONENUMBER + " TEXT not null unique," + COLUMN_USER_EMAIL + " TEXT not null unique," + COLUMN_USER_PASSWORD + " TEXT not null" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);

    }

public void insertEx(String ok,String em){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(COLUMN_USER_NAME, "charan");
    values.put(COLUMN_USER_EMAIL, em);
    values.put(COLUMN_USER_PHONENUMBER, ok);
    values.put(COLUMN_USER_PASSWORD, "12345678");

    db.insert(TABLE_USER, null, values);
    db.close();
}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);

    }


    public long addUser(String phone,String email, String name, String pass) {
        if(!checkUserEmail(email) && !checkUserPhn(phone) && pass.length()>=8 && !name.isEmpty()) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, name);
            values.put(COLUMN_USER_EMAIL, email);
            values.put(COLUMN_USER_PHONENUMBER, phone);
            values.put(COLUMN_USER_PASSWORD, pass);

            long id=db.insert(TABLE_USER, null, values);
            db.close();
            return id;
        }
        return 404;
    }


    public boolean updateUser(long id, String phone,String email, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PHONENUMBER,phone);
        //values.put(COLUMN_USER_PASSWORD, user.getPassword());

        int i=db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        if(i==1)
            return true;
        else
            return false;
    }

    public boolean checkDashEmail(String email,int id){
        int newId=-1;
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_EMAIL + " = ?";

        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if(cursor !=null)
            if(cursor.moveToFirst())
                newId=cursor.getInt(0);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount == 1 ) {
            if(newId==id)
                return true;
        }
        if(cursorCount==0){
            return true;
        }

        return false;
    }

    public boolean checkDashPhone(String phone,int id){
        int newId=-1;
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_PHONENUMBER + " = ?";

        String[] selectionArgs = {phone};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if(cursor !=null)
            if(cursor.moveToFirst())
                newId=cursor.getInt(0);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount == 1) {
            if(newId==id)
                return true;
        }
        if(cursorCount==0){
            return true;
        }
        return false;
    }

    public int updatePass(String email,String pass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_PASSWORD, pass);
       int rt= db.update(TABLE_USER, values, COLUMN_USER_EMAIL + " = ?",
                new String[]{String.valueOf(email)});
        db.close();
        return rt;
    }

    public void deleteUser(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }


    public boolean checkUserEmail(String email) {

        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_EMAIL + " = ?";

        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount == 1) {
            return true;
        }

        return false;
    }

    public boolean checkUserPhn(String phn) {

        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_PHONENUMBER + " = ?";

        String[] selectionArgs = {phn};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount == 1) {
            return true;
        }

        return false;
    }


    public boolean checkUser(String phn, String password) {


        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_PHONENUMBER + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        String[] selectionArgs = {phn, password};


        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount == 1) {

            return true;
        }

        return false;
    }

    public int getColumnid(String phn, String password){
        int id=-1;
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_PHONENUMBER + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        Log.i("CHECK",phn+password);

        String[] selectionArgs = {phn, password};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if(cursor !=null)
            if(cursor.moveToFirst())
                    id=cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID));
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if(cursorCount == 1) {
            return id;
        }
        return -1;
    }


    public String[] getValues(String phn, int rowid){
        int id=-1;
        String name="",email="";
        String[] xs= new String[]{name,email};
        String[] columns = {
                COLUMN_USER_ID,COLUMN_USER_NAME,COLUMN_USER_PHONENUMBER,COLUMN_USER_EMAIL
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_PHONENUMBER + " = ?";

        String[] selectionArgs = {phn};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if(cursor !=null)
            if(cursor.moveToFirst()) {
                id = cursor.getInt(0);
                name=cursor.getString(1);
                email=cursor.getString(3);
                xs = new String[] {name,email};
            }
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if(cursorCount == 1 && rowid==id) {
            return xs;
        }
        return xs;
    }
}
