package com.example.entrega1;
import static android.provider.UserDictionary.Words._ID;


import static com.example.entrega1.Variables.BASEDEDATOS;
import static com.example.entrega1.Variables.PENDIENTE;
import static com.example.entrega1.Variables.NOMBRE;
import static com.example.entrega1.Variables.MENSAJESTAREAS;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDeDatos extends SQLiteOpenHelper {

    private static final int VERSION = 0;
    private final String[] SELECT = new String[]{_ID, NOMBRE, PENDIENTE};

    public BaseDeDatos(Context contexto){
        super(contexto, BASEDEDATOS, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + MENSAJESTAREAS +
                " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOMBRE + " TEXT," + PENDIENTE + " INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + MENSAJESTAREAS);
        onCreate(sqLiteDatabase);
    }

    public void nuevaTarea(Mensajes mensajes) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(NOMBRE, mensajes.getNombre());
        valores.put(PENDIENTE, mensajes.isPendiente());
        db.insertOrThrow(MENSAJESTAREAS, null, valores);
    }

    public ArrayList<Mensajes> getLista(Cursor cursor) {
        ArrayList<Mensajes> tareas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Mensajes mensajes = new Mensajes(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getInt(2) >= 1);
            tareas.add(mensajes);
        }
        return tareas;
    }

    public ArrayList<Mensajes> getTareas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(MENSAJESTAREAS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Mensajes> getTareasHechas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(MENSAJESTAREAS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Mensajes> getTareasPendientes() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(MENSAJESTAREAS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Mensajes> getTareas(String busqueda) {
        return null;
    }

    public void eliminarTarea(Mensajes tareaEliminada) {

    }

    public void modificarTarea(Mensajes tarea) {

    }
}