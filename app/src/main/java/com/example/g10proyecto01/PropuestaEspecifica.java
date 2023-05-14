package com.example.g10proyecto01;

public class PropuestaEspecifica {
    private int id_especifica;
    private int id_propuesta_general;
    private int id_grupo_horario;
    private int id_localidad;
    private  String estado_especifica;

    public PropuestaEspecifica(int id_especifica) {
        this.id_especifica = id_especifica;
    }

    public PropuestaEspecifica(int id_especifica, String estado_especifica) {
        this.id_especifica = id_especifica;
        this.estado_especifica = estado_especifica;
    }

    public PropuestaEspecifica(int id_propuesta_general, int id_grupo_horario, int id_localidad) {
        this.id_propuesta_general = id_propuesta_general;
        this.id_grupo_horario = id_grupo_horario;
        this.id_localidad = id_localidad;
    }

    public PropuestaEspecifica(int id_especifica, int id_propuesta_general, int id_grupo_horario, int id_localidad, String estado_especifica) {
        this.id_especifica = id_especifica;
        this.id_propuesta_general = id_propuesta_general;
        this.id_grupo_horario = id_grupo_horario;
        this.id_localidad = id_localidad;
        this.estado_especifica = estado_especifica;
    }

    public int getId_especifica() {
        return id_especifica;
    }

    public void setId_especifica(int id_especifica) {
        this.id_especifica = id_especifica;
    }

    public int getId_propuesta_general() {
        return id_propuesta_general;
    }

    public void setId_propuesta_general(int id_propuesta_general) {
        this.id_propuesta_general = id_propuesta_general;
    }

    public int getId_grupo_horario() {
        return id_grupo_horario;
    }

    public void setId_grupo_horario(int id_grupo_horario) {
        this.id_grupo_horario = id_grupo_horario;
    }

    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public String getEstado_especifica() {
        return estado_especifica;
    }

    public void setEstado_especifica(String estado_especifica) {
        this.estado_especifica = estado_especifica;
    }
}
