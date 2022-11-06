package com.example.entrega1;

public class Mensajes {
    private long id;
    private String nombre;
    private boolean Pendiente;

    public Mensajes(long id, String nombre, boolean pendiente) {
        this.id = id;
        this.nombre = nombre;
        Pendiente = pendiente;
    }

    public Mensajes(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isPendiente() {
        return Pendiente;
    }

    public void setPendiente(boolean pendiente) {
        Pendiente = pendiente;
    }

    public void terminada(){
        Pendiente= false;
    }

    @Override
    public String toString() {
        return "Mensajes{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", Pendiente=" + Pendiente +
                '}';
    }
}
