package com.example.g10proyecto01;

public class Usuario {
    private String id_usuario;
    private String nom_usuario;
    private String clave;

    //Constructor
    public Usuario() {
    }

    public Usuario(String id_usuario, String nom_usuario,String clave){
        this.id_usuario = id_usuario;
        this.nom_usuario = nom_usuario;
        this.clave = clave;
    }
    public String getId_usuario(){
        return id_usuario;
    }
    public void setId_usuario(String id_usuario){
        this.id_usuario = id_usuario;
    }
    public String getNom_usuario(){
        return nom_usuario;
    }
    public void setNom_usuario(String nom_usuario){
        this.nom_usuario = nom_usuario;
    }
    public String getClave(){
        return clave;
    }
    public void setClave(String clave){
        this.clave = clave;
    }
}

