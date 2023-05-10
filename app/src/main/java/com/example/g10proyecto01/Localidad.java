package com.example.g10proyecto01;

public class Localidad {
    private int id_localidad;
    private String edificio_localidad;
    private String nombre_localidad;
    private int capacidad_localidad;

    public Localidad(){
    }

    //Constructor
    public Localidad(int id_localidad, String edificio_localidad, String nombre_localidad, int capacidad_localidad) {
        this.id_localidad = id_localidad;
        this.edificio_localidad = edificio_localidad;
        this.nombre_localidad = nombre_localidad;
        this.capacidad_localidad = capacidad_localidad;
    }

    // Getter y Setter
    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public String getEdificio_localidad() {
        return edificio_localidad;
    }

    public void setEdificio_localidad(String edificio_localidad) {
        this.edificio_localidad = edificio_localidad;
    }

    public String getNombre_localidad() {
        return nombre_localidad;
    }

    public void setNombre_localidad(String nombre_localidad) {
        this.nombre_localidad = nombre_localidad;
    }

    public int getCapacidad_localidad() {
        return capacidad_localidad;
    }

    public void setCapacidad_localidad(int capacidad_localidad) {
        this.capacidad_localidad = capacidad_localidad;
    }
}