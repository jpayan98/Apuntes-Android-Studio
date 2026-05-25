package com.example.tiendamusica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CrearBD extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "musica.db";
    public static final int VERSION = 1;
    public static final String TABLA_USUARIOS = "usuarios";

    public CrearBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_USUARIOS + " (" +
                "usuario TEXT PRIMARY KEY, " +
                "password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);
        onCreate(db);
    }

    // Devuelve true si usuario y contraseña coinciden
    public boolean comprobarLogin(String usuario, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT password FROM " + TABLA_USUARIOS + " WHERE usuario=?",
                new String[]{usuario});
        if (c.moveToFirst()) {
            boolean ok = c.getString(0).equals(password);
            c.close();
            db.close();
            return ok;
        }
        c.close();
        db.close();
        return false;
    }

    // Devuelve true si se registró bien, false si el usuario ya existía
    public boolean registrar(String usuario, String password) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("usuario", usuario);
            values.put("password", password);
            db.insertOrThrow(TABLA_USUARIOS, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Devuelve true si el usuario existe (para saber si entrar o registrar)
    public boolean existeUsuario(String usuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT usuario FROM " + TABLA_USUARIOS + " WHERE usuario=?",
                new String[]{usuario});
        boolean existe = c.moveToFirst();
        c.close();
        db.close();
        return existe;
    }
}
