package com.example.g10proyecto01;

public class PropuestaGeneral {
    private int id_propuesta;
    private String estado_propuesta;

    public PropuestaGeneral() {
    }

    public PropuestaGeneral(int id_propuesta) {
        this.id_propuesta = id_propuesta;
    }

    public PropuestaGeneral(int id_propuesta, String estado_propuesta) {
        this.id_propuesta = id_propuesta;
        this.estado_propuesta = estado_propuesta;
    }

    public PropuestaGeneral(String estado_propuesta) {
        this.estado_propuesta = estado_propuesta;
    }

    public int getId_propuesta() {
        return id_propuesta;
    }

    public void setId_propuesta(int id_propuesta) {
        this.id_propuesta = id_propuesta;
    }

    public String getEstado_propuesta() {
        return estado_propuesta;
    }

    public void setEstado_propuesta(String estado_propuesta) {
        this.estado_propuesta = estado_propuesta;
    }
}
