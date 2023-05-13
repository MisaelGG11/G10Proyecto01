package com.example.g10proyecto01;

public class Docente {
    private int id_docente;
    private int id_empleado;
    private int nip_docente;
    private String categoria_docente;


    //Constructor
    public Docente() {
    }

    public Docente(int id_docente,int id_empleado, int nip_docente,String categoria_docente){
        this.id_docente = id_docente;
        this.id_empleado = id_empleado;
        this.nip_docente = nip_docente;
        this.categoria_docente = categoria_docente;
    }

    public int getId_docente(){
        return id_docente;
    }
    public void setId_docente(int id_docente){
        this.id_docente = id_docente;
    }
    public int getId_empleado(){
        return id_empleado;
    }
    public void setId_empleado(int id_empleado){
        this.id_empleado = id_empleado;
    }
    public int getNip_docente(){
        return nip_docente;
    }
    public void setNip_docente(int nip_docente){
        this.nip_docente = nip_docente;
    }
    public String getCategoria_docente(){
        return  categoria_docente;
    }
    public void setCategoria_docente(String categoria_docente){
        this.categoria_docente = categoria_docente;
    }
}
