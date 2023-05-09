package com.example.g10proyecto01;


public class LocalAdministrado {
    private int id_local_admin;
    private Localidad Edificios;
    private Localidad nombrelocalidad;
    private EmpleadoUES nombreempleado;
    private EmpleadoUES apellidoempleado;

    public LocalAdministrado(){
    }
    //Constructor

    public LocalAdministrado(int id_local_admin, Localidad edificios, Localidad nombrelocalidad, EmpleadoUES nombreempleado, EmpleadoUES apellidoempleado) {
        this.id_local_admin = id_local_admin;
        Edificios = edificios;
        this.nombrelocalidad = nombrelocalidad;
        this.nombreempleado = nombreempleado;
        this.apellidoempleado = apellidoempleado;
    }

    // Getter y Setter

    public int getId_local_admin() {
        return id_local_admin;
    }

    public void setId_local_admin(int id_local_admin) {
        this.id_local_admin = id_local_admin;
    }

    public Localidad getEdificios() {
        return Edificios;
    }

    public void setEdificios(Localidad edificios) {
        Edificios = edificios;
    }

    public Localidad getNombrelocalidad() {
        return nombrelocalidad;
    }

    public void setNombrelocalidad(Localidad nombrelocalidad) {
        this.nombrelocalidad = nombrelocalidad;
    }

    public EmpleadoUES getNombreempleado() {
        return nombreempleado;
    }

    public void setNombreempleado(EmpleadoUES nombreempleado) {
        this.nombreempleado = nombreempleado;
    }

    public EmpleadoUES getApellidoempleado() {
        return apellidoempleado;
    }

    public void setApellidoempleado(EmpleadoUES apellidoempleado) {
        this.apellidoempleado = apellidoempleado;
    }
}