package com.example.catalogopeliculas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "usuarios.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "usuarios";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_CONTRASENA = "contrasena";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
    COLUMN_NOMBRE + " TEXT PRIMARY KEY, " +
    COLUMN_CONTRASENA + " TEXT)";

    public dbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES ('pepe','1234')");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean comprobarLogin(String nombre, String contrasena){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM usuarios WHERE nombre = ? AND contrasena = ?", new String[]{nombre,contrasena});
        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }
    }
}
