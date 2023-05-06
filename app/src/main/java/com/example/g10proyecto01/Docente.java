package com.example.g10proyecto01;

public class Docente {
    private String id_docente;
    private String nip_docente;
    private String categoria_docente;


    //Constructor
    public Docente() {
    }

    public Docente(String id_docente,String nip_docente,String categoria_docente){
        this.id_docente = id_docente;
        this.nip_docente = nip_docente;
        this.categoria_docente = categoria_docente;
    }

    public String getId_docente(){
        return id_docente;
    }
    public void setId_docente(String id_docente){
        this.id_docente = id_docente;
    }
    public String getNip_docente(){
        return nip_docente;
    }
    public void setNip_docente(String nip_docente){
        this.nip_docente = nip_docente;
    }
    public String getCategoria_docente(){
        return  categoria_docente;
    }
    public void setCategoria_docente(String categoria_docente){
        this.categoria_docente = categoria_docente;
    }
}
