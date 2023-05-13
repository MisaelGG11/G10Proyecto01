package com.example.g10proyecto01;

public class Materia {
    private int id_materia;
    private String cod_materia;
    private String nom_materia;
    private int id_escuela;

    public Materia() {
    }

    public Materia(int id_materia, String cod_materia, String nom_materia, int id_escuela) {
        this.id_materia = id_materia;
        this.cod_materia = cod_materia;
        this.nom_materia = nom_materia;
        this.id_escuela = id_escuela;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getCod_materia() {
        return cod_materia;
    }

    public void setCod_materia(String cod_materia) {
        this.cod_materia = cod_materia;
    }

    public String getNom_materia() {
        return nom_materia;
    }

    public void setNom_materia(String nom_materia) {
        this.nom_materia = nom_materia;
    }

    public int getId_escuela() {
        return id_escuela;
    }

    public void setId_escuela(int id_escuela) {
        this.id_escuela = id_escuela;
    }
}
