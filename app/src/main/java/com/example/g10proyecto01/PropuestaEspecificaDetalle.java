package com.example.g10proyecto01;

import androidx.annotation.NonNull;

public class PropuestaEspecificaDetalle {
    private int id_propuesta;
    private String tipo_grupo;
    private int num_grupo;
    private int cupo;
    private String nombre_localidad;
    private String dia;
    private String hora_inicio;
    private  String hora_finalizacion;

    public PropuestaEspecificaDetalle(int id_propuesta, String tipo_grupo, int num_grupo, int cupo, String nombre_localidad, String dia, String hora_inicio, String hora_finalizacion) {
        this.id_propuesta = id_propuesta;
        this.tipo_grupo = tipo_grupo;
        this.num_grupo = num_grupo;
        this.cupo = cupo;
        this.nombre_localidad = nombre_localidad;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_finalizacion = hora_finalizacion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getNum_grupo() {
        return num_grupo;
    }

    public void setNum_grupo(int num_grupo) {
        this.num_grupo = num_grupo;
    }

    public int getId_propuesta() {
        return id_propuesta;
    }

    public void setId_propuesta(int id_propuesta) {
        this.id_propuesta = id_propuesta;
    }

    public String getTipo_grupo() {
        return tipo_grupo;
    }

    public void setTipo_grupo(String tipo_grupo) {
        this.tipo_grupo = tipo_grupo;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getNombre_localidad() {
        return nombre_localidad;
    }

    public void setNombre_localidad(String nombre_localidad) {
        this.nombre_localidad = nombre_localidad;
    }

    public String getHora_inicio() {
        return hora_inicio.substring(10,19);
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_finalizacion() {
        return hora_finalizacion.substring(10,19);
    }

    public void setHora_finalizacion(String hora_finalizacion) {
        this.hora_finalizacion = hora_finalizacion;
    }

    @NonNull
    @Override
    public String toString() {
        return id_propuesta+": "+tipo_grupo+" "+ num_grupo +" cupo: "+ cupo+
                "\nLocal: " + nombre_localidad +
                "\n"+ dia +" "+ getHora_inicio() + " - " +getHora_finalizacion();
    }
}
