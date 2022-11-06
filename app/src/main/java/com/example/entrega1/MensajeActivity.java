package com.example.entrega1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MensajeActivity extends SQLiteOpenHelper {

    public MensajeActivity(Context context){
        super(context, Mensaje.NOMBREBD, null, Mensaje.VERSIONBD);
    }


    @Override
    public void onCreate(SQLiteDatabase bd) {
        String Crear_Tabla = "CREATE TABLE " + Mensaje.TareaEntrada.TABLA + " ( " +
                Mensaje.TareaEntrada._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Mensaje.TareaEntrada.TITULO_TAREA + " TEXT NOT NULL);";

        bd.execSQL(Crear_Tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("DROP TABLE IF EXISTS " + Mensaje.TareaEntrada.TABLA);
        onCreate(bd);

    }
}