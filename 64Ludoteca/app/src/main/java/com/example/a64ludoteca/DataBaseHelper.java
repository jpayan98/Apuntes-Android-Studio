package com.example.a64ludoteca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LudotecaTuNombre.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "videosTuNombre";

    public static final String COLUMN_REFERENCIA = "referencia";
    public static final String COLUMN_TITULO = "titulo";
    public static final String COLUMN_AUTOR = "autor";
    public static final String COLUMN_TEMA = "tema";
    public static final String COLUMN_DURACION = "duracion";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_REFERENCIA + " TEXT PRIMARY KEY, " +
                    COLUMN_TITULO + " TEXT, " +
                    COLUMN_AUTOR + " TEXT, " +
                    COLUMN_TEMA + " TEXT, " +
                    COLUMN_DURACION + " TEXT)";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
