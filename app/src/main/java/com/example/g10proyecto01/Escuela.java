package com.example.g10proyecto01;

public class Escuela {
    private int id_escuela;
    private String acronimo;
    private String nombre;

    public Escuela(int id_escuela, String acronimo, String nombre) {
        this.id_escuela = id_escuela;
        this.acronimo = acronimo;
        this.nombre = nombre;
    }

    public int getId_escuela() {
        return id_escuela;
    }

    public void setId_escuela(int id_escuela) {
        this.id_escuela = id_escuela;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}



