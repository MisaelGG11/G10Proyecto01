package com.example.g10proyecto01;

public class Materia {
    private int id_integer;
    private String cod_materia;
    private String nom_materia;
    private Escuela escuela;

    public Materia(int id_integer, String cod_materia, String nom_materia, Escuela escuela) {
        this.id_integer = id_integer;
        this.cod_materia = cod_materia;
        this.nom_materia = nom_materia;
        this.escuela = escuela;
    }

    public int getId_integer() {
        return id_integer;
    }

    public void setId_integer(int id_integer) {
        this.id_integer = id_integer;
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

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }
}
