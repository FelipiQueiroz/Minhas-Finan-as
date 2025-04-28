package com.example.minhasfinancas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GastoDBHelper extends SQLiteOpenHelper {

    // Nome do banco de dados e versão
    private static final String DATABASE_NAME = "minhas_financas.db";
    private static final int DATABASE_VERSION = 1;

    // Nome da tabela e colunas
    public static final String TABLE_GASTOS = "gastos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESCRICAO = "descricao";
    public static final String COLUMN_VALOR = "valor";
    public static final String COLUMN_CATEGORIA = "categoria";
    public static final String COLUMN_DATA = "data_gasto";

    public GastoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Comando SQL para criar a tabela
        String CREATE_GASTOS_TABLE = "CREATE TABLE " + TABLE_GASTOS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DESCRICAO + " TEXT NOT NULL, " +
                COLUMN_VALOR + " REAL NOT NULL, " +
                COLUMN_CATEGORIA + " TEXT NOT NULL, " +
                COLUMN_DATA + " TEXT NOT NULL" + ")";
        db.execSQL(CREATE_GASTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualização: apaga a tabela antiga e recria
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GASTOS);
        onCreate(db);
    }
}
