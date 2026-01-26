package com.example.appbibliotecajuanpayan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BibliotecaJuanPayan.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "librosJuanPayan";
    public static final String COLUMN_ISBN = "ISBN";
    public static final String COLUMN_TITULO = "titulo";
    public static final String COLUMN_AUTOR = "autor";
    public static final String COLUMN_GENERO = "genero";
    public static final String COLUMN_PRESTADO = "prestado";
    public static final String COLUMN_PORTADA = "portada";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ISBN + " TEXT PRIMARY KEY, " +
                    COLUMN_TITULO + " TEXT, " +
                    COLUMN_AUTOR + " TEXT, " +
                    COLUMN_GENERO + " TEXT, " +
                    COLUMN_PRESTADO + " INTEGER, " +
                    COLUMN_PORTADA + " TEXT)";

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

    // INSERTAR
    public boolean insertar(String isbn, String titulo, String autor, String genero, boolean prestado, String portada) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ISBN, isbn);
        values.put(COLUMN_TITULO, titulo);
        values.put(COLUMN_AUTOR, autor);
        values.put(COLUMN_GENERO, genero);
        values.put(COLUMN_PRESTADO, prestado ? 1 : 0);
        values.put(COLUMN_PORTADA, portada);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    // CONSULTAR POR ISBN
    public Libro consultar(String isbn) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ISBN + " = ?",
                new String[]{isbn}, null, null, null);

        Libro libro = null;
        if (cursor.moveToFirst()) {
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO));
            String autor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTOR));
            String genero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENERO));
            boolean prestado = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRESTADO)) == 1;
            String portada = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PORTADA));

            libro = new Libro(isbn, titulo, autor, genero, prestado, portada);
        }

        cursor.close();
        db.close();
        return libro;
    }

    // ACTUALIZAR ESTADO PRESTADO
    public boolean actualizar(String isbn, boolean prestado) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRESTADO, prestado ? 1 : 0);

        int rows = db.update(TABLE_NAME, values, COLUMN_ISBN + " = ?", new String[]{isbn});
        db.close();
        return rows > 0;
    }

    // BORRAR
    public boolean borrar(String isbn) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_NAME, COLUMN_ISBN + " = ?", new String[]{isbn});
        db.close();
        return rows > 0;
    }

    // LISTAR TODOS
    public List<Libro> listarTodos() {
        List<Libro> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String isbn = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ISBN));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO));
            String autor = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTOR));
            String genero = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENERO));
            boolean prestado = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRESTADO)) == 1;
            String portada = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PORTADA));

            lista.add(new Libro(isbn, titulo, autor, genero, prestado, portada));
        }

        cursor.close();
        db.close();
        return lista;
    }
}