package com.example.entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lvMensajes = (ListView) findViewById(R.id.lvlistview);
        text_tareas = (EditText) findViewById(R.id.text_tareas);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                        listaMensajes);
        lvMensajes.setAdapter(adapter);

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

    public void onClick(View view) {
        EditText text_tareas = (EditText) findViewById(R.id.text_tareas);
        String mensaje = text_tareas.getText().toString();
        listaMensajes.add(mensaje);

        text_tareas.setText("");
    }
}