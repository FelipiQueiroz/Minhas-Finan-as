package com.example.minhasfinancas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "financas.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_GASTOS = "gastos";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_GASTOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT, " +
                "valor REAL, " +
                "categoria TEXT, " +
                "data TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GASTOS);
        onCreate(db);
    }

    public void inserirGasto(String descricao, double valor, String categoria, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descricao", descricao);
        values.put("valor", valor);
        values.put("categoria", categoria);
        values.put("data", data);
        db.insert(TABLE_GASTOS, null, values);
        db.close();
    }

    public ArrayList<Expense> getTodosGastos() {
        ArrayList<Expense> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GASTOS, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String descricao = cursor.getString(1);
                double valor = cursor.getDouble(2);
                String categoria = cursor.getString(3);
                String data = cursor.getString(4);
                lista.add(new Expense(id, descricao, valor, categoria, data));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
}