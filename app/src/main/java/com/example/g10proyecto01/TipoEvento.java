package com.example.g10proyecto01;

import androidx.annotation.NonNull;

public class TipoEvento{
    private int id_tipo_evento;
    private String nombre_tipo_evento;

    public TipoEvento(){
    }

    //Constructor
    public TipoEvento(int id_tipo_evento, String nombre_tipo_evento) {
        this.id_tipo_evento = id_tipo_evento;
        this.nombre_tipo_evento = nombre_tipo_evento;
    }

    // Getter y Setter
    public int getId_tipo_evento() {
        return id_tipo_evento;
    }

    public void setId_tipo_evento(int id_tipo_evento) {
        this.id_tipo_evento = id_tipo_evento;
    }

    public String getNombre_tipo_evento() {
        return nombre_tipo_evento;
    }

    public void setNombre_tipo_evento(String nombre_tipo_evento) {
        this.nombre_tipo_evento = nombre_tipo_evento;
    }
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}