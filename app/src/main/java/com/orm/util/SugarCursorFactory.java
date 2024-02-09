package com.orm.util;

import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

public class SugarCursorFactory implements SQLiteDatabase.CursorFactory {

    private static final String TAG = "SugarCursorFactory";

    private final boolean debugEnabled;

    public static long cursorWindowSize = 16 * 1024 * 1024;

    public SugarCursorFactory() {
        this(false);
    }

    public SugarCursorFactory(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    /**
     * @noinspection ReassignedVariable
     */
    public static void adjustWindowSize(int length) {
        length += length >> 4; // add a 25% overhead
        if(cursorWindowSize < length) {
            cursorWindowSize = length;
            Log.d(TAG, "Extended window size to " + length);
        }
    }

    @SuppressWarnings("deprecation")
    public Cursor newCursor(SQLiteDatabase sqLiteDatabase,
            SQLiteCursorDriver sqLiteCursorDriver,
            String editTable,
            SQLiteQuery sqLiteQuery) {

        if (debugEnabled) {
            Log.d("SQL Log", sqLiteQuery.toString());
        }

        SQLiteCursor cursor = new SQLiteCursor(sqLiteDatabase, sqLiteCursorDriver, editTable, sqLiteQuery);
        cursor.setWindow(new CursorWindow("imageWindow", cursorWindowSize));
        return cursor;
    }

}
