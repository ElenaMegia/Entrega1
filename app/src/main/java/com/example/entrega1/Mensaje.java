package com.example.entrega1;

import android.provider.BaseColumns;

public class Mensaje {
    public static final String NOMBREBD = "com.aziflaj.todolist.db";
    public static final int VERSIONBD = 1;

    public class TareaEntrada implements BaseColumns {
        public static final String TABLA = "Tareas";
        public static final String TITULO_TAREA = "Titulo";
    }
}