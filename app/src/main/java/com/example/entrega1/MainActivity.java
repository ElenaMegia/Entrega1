package com.example.entrega1;

import static com.example.entrega1.MainActivity.Estado.VER_PENDIENTES;
import static com.example.entrega1.MainActivity.Estado.VER_HECHAS;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();
    private EditText text_tareas;
    private ListView lvlistview;
    public static ArrayList<Mensajes> listaMensajes
            = new ArrayList<Mensajes>();
    public enum Estado {
        VER_HECHAS, VER_PENDIENTES
    }
    private Estado estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvMensajes = (ListView) findViewById(R.id.lvlistview);
        text_tareas = (EditText) findViewById(R.id.text_tareas);



        Button AnadirTarea = (Button) findViewById(R.id.AnadirTarea);
        AnadirTarea.setOnClickListener(this);
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

    @Override
    protected void onResume() {
        super.onResume();

        BaseDeDatos db = new BaseDeDatos(this);
        listaMensajes.clear();
        listaMensajes.addAll(db.getTareas());

    }

    public void onClick(View view) {

        EditText text_tareas = (EditText) findViewById(R.id.text_tareas);
        String nombre = text_tareas.getText().toString();
        if (nombre.equals("")) {
            Toast.makeText(this, R.string.text_tareas, Toast.LENGTH_SHORT).show();
            return;
        }
        Mensajes mensajes = new Mensajes(nombre);

        BaseDeDatos db = new BaseDeDatos(this);
        db.nuevaTarea(mensajes);

        if (estado == VER_PENDIENTES) {
            listaMensajes.add(mensajes);
            text_tareas.setText("");
        } else {
            text_tareas.setText("");
        }
        text_tareas.requestFocus();








    }


}
