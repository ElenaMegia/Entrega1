package com.example.entrega1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    private EditText text_tareas;
    private ListView lvlistview;
    public static ArrayList<String> listaMensajes
            = new ArrayList<>();
    private MensajeActivity mensajeActivity;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvMensajes = (ListView) findViewById(R.id.lvlistview);
        text_tareas = (EditText) findViewById(R.id.text_tareas);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        listaMensajes);
        lvMensajes.setAdapter(adapter);
         mensajeActivity = new MensajeActivity(this);
        Button AnadirTarea = (Button) findViewById(R.id.AnadirTarea);
        AnadirTarea.setOnClickListener(this);

        updateUI();
    }
    public void launchHechas(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, HechasActivity.class);
        startActivity(intent);
    }

    public void launchPendientes(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, PendientesActivity.class);
        startActivity(intent);
    }

    public void onClick(View view) {
         text_tareas = (EditText) findViewById(R.id.text_tareas);
        String mensaje = String.valueOf(text_tareas.getText());
       // listaMensajes.add(mensaje);
        SQLiteDatabase db = mensajeActivity.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Mensaje.TareaEntrada.TITULO_TAREA, mensaje);
        db.insertWithOnConflict(Mensaje.TareaEntrada.TABLA, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        updateUI();
    }



    public void borrarTarea(View view){
        View parent = (View) view.getParent();
         text_tareas =  parent.findViewById(R.id.titulo);
        String mensaje = String.valueOf(text_tareas.getText());
        SQLiteDatabase bd = mensajeActivity.getWritableDatabase();
        bd.delete(Mensaje.TareaEntrada.TABLA, Mensaje.TareaEntrada.TITULO_TAREA + " = ?", new String[]{mensaje});
        bd.close();
        updateUI();
    }

    private void updateUI(){
        ArrayList<String> listaTareas = new ArrayList<>();
        SQLiteDatabase bd = mensajeActivity.getReadableDatabase();
        Cursor cursor = bd.query(Mensaje.TareaEntrada.TABLA, new String[]{Mensaje.TareaEntrada._ID, Mensaje.TareaEntrada.TITULO_TAREA},
                null, null, null, null, null);
        while(cursor.moveToNext()){
            int idx = cursor.getColumnIndex(Mensaje.TareaEntrada.TITULO_TAREA);
            listaTareas.add(cursor.getString(idx));
        }
            adapter.clear();
            adapter.addAll(listaTareas);
            adapter.notifyDataSetChanged();
        cursor.close();
        bd.close();
    }

}