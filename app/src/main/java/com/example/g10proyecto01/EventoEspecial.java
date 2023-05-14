package com.example.g10proyecto01;

public class EventoEspecial{
    private int id_evento;
    private String nombre_evento;
    private int id_tipo_evento;
    private int id_organizador;
    private String fecha_evento;
    private int horario;
    private int id_localidad;
    public EventoEspecial(){
    }

    //Constructor

    public EventoEspecial(int id_evento, String nombre_evento, int id_tipo_evento, int id_organizador, String fecha_evento, int horario, int id_localidad) {
        this.id_evento = id_evento;
        this.nombre_evento = nombre_evento;
        this.id_tipo_evento = id_tipo_evento;
        this.id_organizador = id_organizador;
        this.fecha_evento = fecha_evento;
        this.horario = horario;
        this.id_localidad = id_localidad;
    }


    // Getter y Setter

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getNombre_evento() {
        return nombre_evento;
    }

    public void setNombre_evento(String nombre_evento) {
        this.nombre_evento = nombre_evento;
    }

    public int getId_tipo_evento() {
        return id_tipo_evento;
    }

    public void setId_tipo_evento(int id_tipo_evento) {
        this.id_tipo_evento = id_tipo_evento;
    }

    public int getId_organizador() {
        return id_organizador;
    }

    public void setId_organizador(int id_organizador) {
        this.id_organizador = id_organizador;
    }

    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public String getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(String fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }
}