package com.labyrinth.loopy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "userLocalData";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "userData";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our roll number column.
    private static final String ROLL_NUM = "rollNum";

    // below variable is for our course name column
    private static final String NAME_COL = "name";

    // below variable id for our course username column.
    private static final String USER_NAME = "username";

    // below variable for our course column.
    private static final String COURSE_NAME = "course";

    // below variable for our year column.
    private static final String COURSE_YEAR = "year";

    // below variable is for our course semester column.
    private static final String CURRENT_SEMESTER = "semester";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROLL_NUM + "TEXT,"
                + NAME_COL + " TEXT,"
                + USER_NAME + " TEXT,"
                + COURSE_NAME + " TEXT,"
                + COURSE_YEAR + " TEXT,"
                + CURRENT_SEMESTER + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new user to our sqlite database.
    public void addNewUser(String fullName, String userName) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, fullName);
        values.put(USER_NAME, userName);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // this method is use to add more user data to our sqlite database.
    public void addMoreUserDetails(String rollNum, String courseName, String courseYear, String currentSemester){
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ROLL_NUM, rollNum);
        values.put(COURSE_NAME, courseName);
        values.put(COURSE_YEAR, courseYear);
        values.put(CURRENT_SEMESTER, currentSemester);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
