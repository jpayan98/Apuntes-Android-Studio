package com.example.a61crearymanipularbbddsqlite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CrearBD extends SQLiteOpenHelper {

    // Nombre de la base de datos
    public static final String NOMBREBD = "bdarticulos.sdb";

    // Versión de la base de datos
    public static final int VERSION = 1;

    // Nombre de la tabla
    public static final String NOMBRE_TABLA = "articulos";

    // Atributos o campos de la tabla
    public static final String REF = "ref";
    public static final String NOMBRE = "nombre";

    // Constructor
    public CrearBD(Context context) {
        super(context, NOMBREBD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla articulos
        db.execSQL("CREATE TABLE IF NOT EXISTS articulos (" +
                "ref INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nombre TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si cambiamos la versión de la BD, podemos actualizar la estructura aquí
        db.execSQL("DROP TABLE IF EXISTS articulos");
        onCreate(db);
    }
}